package Client.ViewModel;

import Client.Model.Model;
import Server.Model.Hotel.Room;
import Server.Model.MyDate;
import Server.Utility.DataBase.DatabaseConnection;
import Server.Utility.IllegalDateException;
import dk.via.remote.observer.RemotePropertyChangeListener;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CustomerHomeViewModel  implements PropertyChangeListener
{
  private Model model;

  private SimpleObjectProperty<ObservableList<Room>> newReservations;

  private SimpleObjectProperty<LocalDate> fromDateNewReservation, toDateNewReservation;
  private SimpleBooleanProperty reserveBalcony,reserveKitchen,reserveInternet,reserveBathroom;
  private SimpleObjectProperty<Integer> reservePricePerNight;
  private SimpleStringProperty reserveNoBeds,reserveRoomNo,hiddenFieldRoomNo;

  public CustomerHomeViewModel(Model model)
  {
    this.model = model;
    model.addPropertyChangeListener(this);
    this.newReservations = new SimpleObjectProperty<>();

    hiddenFieldRoomNo=new SimpleStringProperty();
    fromDateNewReservation=new SimpleObjectProperty<>();
    toDateNewReservation=new SimpleObjectProperty<>();
    reserveBalcony=new SimpleBooleanProperty();
    reserveBathroom=new SimpleBooleanProperty();
    reserveInternet=new SimpleBooleanProperty();
    reserveKitchen=new SimpleBooleanProperty();
    reserveNoBeds=new SimpleStringProperty();
    reserveRoomNo=new SimpleStringProperty();
    reservePricePerNight=new SimpleObjectProperty<>();
  }

  public void bindRooms(ObjectProperty<ObservableList<Room>> property)
  {
    property.bindBidirectional(newReservations);
  }

  public void update()
  {
    hiddenFieldRoomNo.set("");
    reserveNoBeds.set("");
    reserveRoomNo.set("");
    reserveBalcony.set(false);
    reserveBathroom.set(false);
    reserveKitchen.set(false);
    reserveInternet.set(false);
    fromDateNewReservation.set(null);
    toDateNewReservation.set(null);
    reservePricePerNight.set(0);


    ArrayList<Room> rooms;
    try
    {
      rooms = model.getAllRooms();
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
    ObservableList<Room> roomObservableList = FXCollections.observableList(rooms);
    newReservations.set(roomObservableList);
  }

  public void saveRoom(Room room)
  {
    model.saveSelectedRoom(room);
  }

  public void fillHiddenField()
  {
    Room room = model.getSelectedRoom();
    hiddenFieldRoomNo.set(String.valueOf(room.getRoomNo()));
  }


  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      update();
      System.out.println("Test");
    });
  }

  public boolean addReservation() throws RemoteException
  {
    try
    {
      String state= model.addReservation(Integer.parseInt(hiddenFieldRoomNo.getValue()), "john@hotmail.com", MyDate.LocalDateToMyDate(fromDateNewReservation.getValue()), MyDate.LocalDateToMyDate(toDateNewReservation.getValue()), false);
      if (state.equals(DatabaseConnection.SUCCESS)){
        Alert alert=new Alert(Alert.AlertType.INFORMATION,"Successfully added a new reservation",
            ButtonType.OK);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.showAndWait();
        return true;
      }
      if (state.equals(DatabaseConnection.ERROR)){
        Alert alert=new Alert(Alert.AlertType.ERROR,"Error occurred",ButtonType.OK);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.showAndWait();
        return false;
      }
    }
    catch (NumberFormatException e)
    {
      Alert alert=new Alert(Alert.AlertType.ERROR,"Please select a room to reserve",ButtonType.OK);
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.showAndWait();
    }
    catch (IllegalDateException e)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR, e.message(),
          ButtonType.OK);
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.showAndWait();
    }
    return false;
  }

  public void bindHiddenText(StringProperty property){
    property.bindBidirectional(hiddenFieldRoomNo);
  }

  public void bindFromDateNewReservation(ObjectProperty<LocalDate> property){
    property.bindBidirectional(fromDateNewReservation);
  }

  public void bindToDateNewReservation(ObjectProperty<LocalDate> property){
    property.bindBidirectional(toDateNewReservation);
  }

  public void bindReserveKitchen(BooleanProperty property){
    property.bindBidirectional(reserveKitchen);
  }

  public void bindReserveBalcony(BooleanProperty property){
    property.bindBidirectional(reserveBalcony);
  }

  public void bindReserveInternet(BooleanProperty property){
    property.bindBidirectional(reserveInternet);
  }

  public void bindReserveBathroom(BooleanProperty property){
    property.bindBidirectional(reserveBathroom);
  }

  public void bindReservePrice(ObjectProperty<Integer> property){
    property.bindBidirectional(reservePricePerNight);
  }

  public void bindReserveRoomNo(StringProperty property){
    property.bindBidirectional(reserveRoomNo);
  }

  public void bindReserveNoBeds(StringProperty property){
    property.bindBidirectional(reserveNoBeds);
  }

  public void filterNewReservation() throws RemoteException
  {
    String[] temp = new String[7];
    int counter = 0;
    if (reserveBalcony.getValue())
    {
      temp[counter] = "balcony, ";
      counter++;
    }
    if (reserveKitchen.getValue())
    {
      temp[counter] = "kichenet, ";
      counter++;
    }
    if (reserveInternet.getValue())
    {
      temp[counter] = "internet, ";
      counter++;
    }
    if (reserveBathroom.getValue())
    {
      temp[counter] = "bathroom, ";
      counter++;
    }

    if (reservePricePerNight.getValue() != 0)
    {
      temp[counter] = "Price, " + reservePricePerNight.getValue();
      counter++;
    }

    if (!reserveRoomNo.getValue().equals(""))
    {
      temp[counter] = "RoomNo: " + reserveRoomNo.getValue() + ", ";
      counter++;
    }

    if (!reserveNoBeds.getValue().equals(""))
    {
      temp[counter] = "NoBeds: " + reserveNoBeds.getValue() + ", ";
      counter++;
    }
    ObservableList<Room> roomObservableList;

    if (toDateNewReservation.getValue()!=null&&fromDateNewReservation.getValue()!=null){
      if(MyDate.LocalDateToMyDate(toDateNewReservation.getValue()).isBefore(MyDate.LocalDateToMyDate(fromDateNewReservation.getValue()))){
        Alert alert=new Alert(Alert.AlertType.ERROR,"The finish date is before from date",ButtonType.OK);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.showAndWait();
        return;
      }
      roomObservableList = FXCollections.observableList(model.getFilteredRoom(MyDate.LocalDateToMyDate(fromDateNewReservation.getValue()),MyDate.LocalDateToMyDate(toDateNewReservation.getValue()),temp));
    }
    else
      roomObservableList = FXCollections.observableList(model.getFilteredRoom(null,null,temp));
    newReservations.set(roomObservableList);
  }
}
