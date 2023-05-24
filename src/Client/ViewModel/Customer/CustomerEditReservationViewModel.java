package Client.ViewModel.Customer;

import Client.Model.Model;
import Server.Model.Hotel.Reservation;
import Server.Model.MyDate;
import Server.Utility.DataBase.DatabaseConnection;
import Server.Utility.IllegalDateException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.rmi.RemoteException;
import java.time.LocalDate;

public class CustomerEditReservationViewModel
{
  private Model model;

  private SimpleObjectProperty<LocalDate> fromDate,toDate;

  private SimpleStringProperty username,roomNo;
  public CustomerEditReservationViewModel(Model model)
  {
    this.model = model;
    fromDate=new SimpleObjectProperty<>();
    toDate=new SimpleObjectProperty<>();
    username=new SimpleStringProperty();
    roomNo=new SimpleStringProperty();
  }

  public void bindFromDate(ObjectProperty<LocalDate> property){
    property.bindBidirectional(fromDate);
  }

  public void bindToDate(ObjectProperty<LocalDate> property){
    property.bindBidirectional(toDate);
  }

  public void bindUsername(StringProperty property){
    property.bindBidirectional(username);
  }

  public void bindRoomNo(StringProperty property){
    property.bindBidirectional(roomNo);
  }

  public boolean save() throws RemoteException
  {
    Reservation old=model.getSelectedReservation();
    LocalDate temp=fromDate.getValue();
    LocalDate temp2=toDate.getValue();
    MyDate from=new MyDate(temp.getDayOfMonth(), temp.getMonthValue(), temp.getYear());
    MyDate to=new MyDate(temp2.getDayOfMonth(), temp2.getMonthValue(), temp2.getYear());
    try
    {
      String state=model.updateReservation(Integer.parseInt(roomNo.getValue()), username.getValue(),from,to, old.getRoomNumber(), old.getUsername(), old.getFromDate(),old.getToDate());
      if (state.equals(DatabaseConnection.SUCCESS)){
        Alert alert=new Alert(Alert.AlertType.INFORMATION,"Edit successful", ButtonType.OK);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.showAndWait();
        return true;
      }
      if (state.equals(DatabaseConnection.ERROR)){
        Alert alert=new Alert(Alert.AlertType.ERROR,"Error happened", ButtonType.OK);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.showAndWait();
        return false;
      }
      if (state.equals(DatabaseConnection.MANDATORY)){
        Alert alert=new Alert(Alert.AlertType.ERROR,"Fill every field", ButtonType.OK);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.showAndWait();
        return false;
      }
    }
    catch (IllegalDateException e){
      Alert alert=new Alert(Alert.AlertType.ERROR,e.message(), ButtonType.OK);
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.showAndWait();
      return false;
    }
    catch (NumberFormatException e){
      Alert alert=new Alert(Alert.AlertType.ERROR,"Fill every field", ButtonType.OK);
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.showAndWait();
      return false;
    }
    return false;
  }

  public void fill(){
    Reservation temp=model.getSelectedReservation();
    username.set(temp.getUsername());
    roomNo.set(String.valueOf(temp.getRoomNumber()));
    fromDate.set(temp.getFromDate().convertToLocalDate());
    toDate.set(temp.getToDate().convertToLocalDate());
  }
  public boolean logOut()
  {
    try
    {
      model.logOut();
      model.setGuest();
      return true;
    }
    catch (RemoteException e)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText("Logging out error");
      alert.setContentText("Contact the developers of the system\nPhone number: +45 8755 4243\nPhone number: +45 8755 4222");
      alert.showAndWait();
      return false;
    }
  }
}
