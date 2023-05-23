package Client.ViewModel;

import Client.Model.Model;
import Server.Model.Hotel.Reservation;
import Server.Model.Hotel.Review;
import Server.Model.Hotel.Room;
import Server.Model.MyDate;
import Server.Utility.DataBase.DatabaseConnection;
import Server.Utility.IllegalDateException;
import Server.Model.Hotel.Users.Customer;
import Server.Model.Hotel.Users.Person;
import Server.Utility.DataBase.DatabaseConnection;
import dk.via.remote.observer.RemotePropertyChangeListener;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CustomerHomeViewModel implements PropertyChangeListener
{
  private Model model;

  private SimpleObjectProperty<ObservableList<Room>> newReservations;
  private SimpleObjectProperty<ObservableList<Review>> allReviews;
  private SimpleObjectProperty<ObservableList<Reservation>> allMyReservation;
  private SimpleObjectProperty<LocalDate> fromDateNewReservation, toDateNewReservation;
  private SimpleBooleanProperty reserveBalcony, reserveKitchen, reserveInternet, reserveBathroom;
  private SimpleObjectProperty<Integer> reservePricePerNight;
  private SimpleStringProperty reserveNoBeds, reserveRoomNo, hiddenFieldRoomNo;
  private SimpleStringProperty password;
  private SimpleStringProperty username, firstName, lastName;

  private ArrayList<Customer> allCustomers;
  private Person user;

  public CustomerHomeViewModel(Model model)
  {
    this.model = model;
    model.addPropertyChangeListener(this);
    this.newReservations = new SimpleObjectProperty<>();

    hiddenFieldRoomNo = new SimpleStringProperty();
    fromDateNewReservation = new SimpleObjectProperty<>();
    toDateNewReservation = new SimpleObjectProperty<>();
    reserveBalcony = new SimpleBooleanProperty();
    reserveBathroom = new SimpleBooleanProperty();
    reserveInternet = new SimpleBooleanProperty();
    reserveKitchen = new SimpleBooleanProperty();
    reserveNoBeds = new SimpleStringProperty();
    reserveRoomNo = new SimpleStringProperty();
    reservePricePerNight = new SimpleObjectProperty<>();
    firstName = new SimpleStringProperty();
    lastName = new SimpleStringProperty();

    model.setGuest();

    this.allReviews = new SimpleObjectProperty<>();
    try
    {
      allCustomers = model.getAllCustomers();
    }
    catch (RemoteException e)
    {
      allCustomers = new ArrayList<>();
    }
    this.password = new SimpleStringProperty();
    this.username = new SimpleStringProperty();
    this.allReviews = new SimpleObjectProperty<>();
    this.allMyReservation = new SimpleObjectProperty<>();
  }

  public void bindRooms(ObjectProperty<ObservableList<Room>> property)
  {
    property.bindBidirectional(newReservations);
  }

  public void bindReviews(ObjectProperty<ObservableList<Review>> property)
  {
    property.bindBidirectional(allReviews);
  }

  public void bindMyReservation(
      ObjectProperty<ObservableList<Reservation>> property)
  {
    property.bindBidirectional(allMyReservation);
  }

  public void bindUsername(StringProperty property)
  {
    property.bindBidirectional(username);
  }

  public void bindPassword(StringProperty property)
  {
    property.bindBidirectional(password);
  }

  public void bindFirstName(StringProperty property)
  {
    property.bind(firstName);
  }

  public void bindLastName(StringProperty property)
  {
    property.bind(lastName);
  }

  public Boolean logIn()
  {
    for (int i = 0; i < allCustomers.size(); i++)
    {
      if (allCustomers.get(i).getUsername().equals(username.getValue()))
      {
        if (allCustomers.get(i).getPassword().equals(password.getValue()))
        {
          user = allCustomers.get(i);
          try
          {
            model.logIn(user);
            return true;
          }
          catch (Exception e)
          {
            e.printStackTrace();
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("Something went wrong");
            error.setContentText(
                "Contact the developers of the system\nPhone number: +45 8755 4243\nPhone number: +45 8755 4222");
            error.showAndWait();
            return false;
          }
        }
        else
        {
          Alert invalidPassword = new Alert(Alert.AlertType.ERROR);
          invalidPassword.setHeaderText("Invalid password try one more time");
          invalidPassword.showAndWait();
          return false;
        }
      }
    }
    Alert doesntExist = new Alert(Alert.AlertType.ERROR);
    doesntExist.setHeaderText("User does not exist.");
    doesntExist.showAndWait();
    return false;
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
      alert.setContentText(
          "Contact the developers of the system\nPhone number: +45 8755 4243\nPhone number: +45 8755 4222");
      alert.showAndWait();
      return false;
    }
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
    firstName.set(model.getCurrentCustomer().getFirstName());
    lastName.set(model.getCurrentCustomer().getLastName());

    ArrayList<Room> rooms;
    ArrayList<Review> reviews;
    ArrayList<Reservation> reservations;
    try
    {
      rooms = model.getAllRooms();
      reviews = model.getAllReviews();

      reservations = model.getAllMyReservation(
          model.getCurrentCustomer().getUsername());
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
    ObservableList<Room> roomObservableList = FXCollections.observableList(
        rooms);
    ObservableList<Review> reviewObservableList = FXCollections.observableList(
        reviews);
    allReviews.set(reviewObservableList);
    if (reservations != null)
    {
      ObservableList<Reservation> reservationObservableList = FXCollections.observableList(
          reservations);

      allMyReservation.set(reservationObservableList);
    }
    newReservations.set(roomObservableList);
  }

  public void saveRoom(Room room)
  {
    model.saveSelectedRoom(room);
  }

  public void saveReservation(Reservation reservation)
  {
    model.saveSelectedReservation(reservation);
  }

  public boolean getSelectedReservation()
  {
    if (model.getSelectedReservation()==null)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "Please select a reservation", ButtonType.OK);
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.showAndWait();
      return false;
    }
    return true;
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
    });
  }

  public boolean addReservation() throws RemoteException
  {
    try
    {
      String state = model.addReservation(
          Integer.parseInt(hiddenFieldRoomNo.getValue()),
          model.getCurrentCustomer().getUsername(),
          MyDate.LocalDateToMyDate(fromDateNewReservation.getValue()),
          MyDate.LocalDateToMyDate(toDateNewReservation.getValue()), false);
      if (state.equals(DatabaseConnection.SUCCESS))
      {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
            "Successfully added a new reservation", ButtonType.OK);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.showAndWait();
        return true;
      }
      if (state.equals(DatabaseConnection.ERROR))
      {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Error occurred",
            ButtonType.OK);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.showAndWait();
        return false;
      }
    }
    catch (NumberFormatException e)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "Please select a room to reserve", ButtonType.OK);
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

  public void bindHiddenText(StringProperty property)
  {
    property.bindBidirectional(hiddenFieldRoomNo);
  }

  public void bindFromDateNewReservation(ObjectProperty<LocalDate> property)
  {
    property.bindBidirectional(fromDateNewReservation);
  }

  public void bindToDateNewReservation(ObjectProperty<LocalDate> property)
  {
    property.bindBidirectional(toDateNewReservation);
  }

  public void bindReserveKitchen(BooleanProperty property)
  {
    property.bindBidirectional(reserveKitchen);
  }

  public void bindReserveBalcony(BooleanProperty property)
  {
    property.bindBidirectional(reserveBalcony);
  }

  public void bindReserveInternet(BooleanProperty property)
  {
    property.bindBidirectional(reserveInternet);
  }

  public void bindReserveBathroom(BooleanProperty property)
  {
    property.bindBidirectional(reserveBathroom);
  }

  public void bindReservePrice(ObjectProperty<Integer> property)
  {
    property.bindBidirectional(reservePricePerNight);
  }

  public void bindReserveRoomNo(StringProperty property)
  {
    property.bindBidirectional(reserveRoomNo);
  }

  public void bindReserveNoBeds(StringProperty property)
  {
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

    if (toDateNewReservation.getValue() != null
        && fromDateNewReservation.getValue() != null)
    {
      if (MyDate.LocalDateToMyDate(toDateNewReservation.getValue()).isBefore(
          MyDate.LocalDateToMyDate(fromDateNewReservation.getValue())))
      {
        Alert alert = new Alert(Alert.AlertType.ERROR,
            "The finish date is before from date", ButtonType.OK);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.showAndWait();
        return;
      }
      roomObservableList = FXCollections.observableList(model.getFilteredRoom(
          MyDate.LocalDateToMyDate(fromDateNewReservation.getValue()),
          MyDate.LocalDateToMyDate(toDateNewReservation.getValue()), temp));
    }
    else
      roomObservableList = FXCollections.observableList(
          model.getFilteredRoom(null, null, temp));
    newReservations.set(roomObservableList);
  }

  public String cancelReservation(int roomNo, String username, MyDate fromDate)
      throws RemoteException
  {
    return model.deleteReservation(roomNo, username, fromDate);
  }
}
