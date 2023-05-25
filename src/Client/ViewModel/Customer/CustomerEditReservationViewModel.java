package Client.ViewModel.Customer;

import Client.Model.Model;
import Client.Model.ModelCustomerSide;
import Client.Utility.Alerts;
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
  private ModelCustomerSide model;

  private SimpleObjectProperty<LocalDate> fromDate, toDate;

  private SimpleStringProperty username, roomNo;

  public CustomerEditReservationViewModel(Model model)
  {
    this.model = model;
    fromDate = new SimpleObjectProperty<>();
    toDate = new SimpleObjectProperty<>();
    username = new SimpleStringProperty();
    roomNo = new SimpleStringProperty();
  }

  public void bindFromDate(ObjectProperty<LocalDate> property)
  {
    property.bindBidirectional(fromDate);
  }

  public void bindToDate(ObjectProperty<LocalDate> property)
  {
    property.bindBidirectional(toDate);
  }

  public void bindUsername(StringProperty property)
  {
    property.bindBidirectional(username);
  }

  public void bindRoomNo(StringProperty property)
  {
    property.bindBidirectional(roomNo);
  }
  public void fill()
  {
    Reservation temp = model.getSelectedReservation();
    username.set(temp.getUsername());
    roomNo.set(String.valueOf(temp.getRoomNumber()));
    fromDate.set(temp.getFromDate().convertToLocalDate());
    toDate.set(temp.getToDate().convertToLocalDate());
  }

  public void previousScene(boolean b)
  {
    model.setPreviousView(b);
  }

  public Alerts save()
  {
    Reservation old = model.getSelectedReservation();
    LocalDate temp = fromDate.getValue();
    LocalDate temp2 = toDate.getValue();
    MyDate from = new MyDate(temp.getDayOfMonth(), temp.getMonthValue(), temp.getYear());
    MyDate to = new MyDate(temp2.getDayOfMonth(), temp2.getMonthValue(), temp2.getYear());
    try
    {
      String state = model.updateReservation(Integer.parseInt(roomNo.getValue()), username.getValue(), from, to,
          old.getRoomNumber(), old.getUsername(), old.getFromDate(), old.getToDate());
      if (state.equals(DatabaseConnection.SUCCESS))
      {
        return new Alerts(Alert.AlertType.INFORMATION, "Success", "Edit successful");
      }
      if (state.equals(DatabaseConnection.MANDATORY))
      {
        return new Alerts(Alert.AlertType.ERROR, "Error", "Fill every field");
      }
      else
      {
        return new Alerts(Alert.AlertType.ERROR, "Error", "Sorry. Some Error Occurred :(");
      }
    }
    catch (NumberFormatException | IllegalDateException | RemoteException e)
    {
      return new Alerts(Alert.AlertType.ERROR, "Error", "Sorry. Some Error Occurred :(");
    }
  }
}
