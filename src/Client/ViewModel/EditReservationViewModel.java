package Client.ViewModel;

import Client.Model.Model;
import Server.Model.MyDate;
import Server.Model.Reservation;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.rmi.RemoteException;
import java.time.LocalDate;

public class EditReservationViewModel
{
  private Model model;

  private SimpleObjectProperty<LocalDate> fromDate,toDate;

  private SimpleStringProperty username,roomNo;
  public EditReservationViewModel(Model model)
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

  public void save() throws RemoteException
  {
    LocalDate temp=fromDate.getValue();
    LocalDate temp2=toDate.getValue();
    MyDate from=new MyDate(temp.getDayOfMonth(), temp.getMonthValue(), temp.getYear());
    MyDate to=new MyDate(temp2.getDayOfMonth(), temp2.getMonthValue(), temp2.getYear());
    model.updateReservation(Integer.parseInt(roomNo.getValue()), username.getValue(),from,to);
  }

  public void fill(){
    Reservation temp=model.getSelectedReservation();
    username.set(temp.getUsername());
    roomNo.set(String.valueOf(temp.getRoomNumber()));
    fromDate.set(temp.getFromDate().convertToLocalDate());
    toDate.set(temp.getToDate().convertToLocalDate());
  }
}
