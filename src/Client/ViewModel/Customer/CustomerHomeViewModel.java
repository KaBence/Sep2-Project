package Client.ViewModel.Customer;

import Client.Model.Model;
import Client.Utility.Alerts;
import Server.Model.Hotel.Reservation;
import Server.Model.Hotel.Review;
import Server.Model.Hotel.Room;
import Server.Model.Hotel.Users.Customer;
import Server.Model.Hotel.Users.Guest;
import Server.Model.Hotel.Users.Person;
import Server.Model.MyDate;
import Server.Utility.DataBase.DatabaseConnection;
import Server.Utility.IllegalDateException;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    this.model.addPropertyChangeListener(this);
    this.newReservations = new SimpleObjectProperty<>();

    this.hiddenFieldRoomNo = new SimpleStringProperty();
    this.fromDateNewReservation = new SimpleObjectProperty<>();
    this.toDateNewReservation = new SimpleObjectProperty<>();
    this.reserveBalcony = new SimpleBooleanProperty();
    this.reserveBathroom = new SimpleBooleanProperty();
    this.reserveInternet = new SimpleBooleanProperty();
    this.reserveKitchen = new SimpleBooleanProperty();
    this.reserveNoBeds = new SimpleStringProperty();
    this.reserveRoomNo = new SimpleStringProperty();
    this.reservePricePerNight = new SimpleObjectProperty<>();
    this.firstName = new SimpleStringProperty();
    this.lastName = new SimpleStringProperty();

    this.model.setGuest();

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
    saveReservation(null);

    ArrayList<Room> rooms;
    ArrayList<Review> reviews;
    ArrayList<Reservation> reservations;
    try
    {
      rooms = model.getAllRooms();
      reviews = model.getAllReviews();
      reservations = model.getAllMyReservation(model.getCurrentCustomer().getUsername());
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
    ObservableList<Room> roomObservableList = FXCollections.observableList(rooms);
    ObservableList<Review> reviewObservableList = FXCollections.observableList(reviews);
    allReviews.set(reviewObservableList);
    if (reservations != null)
    {
      ObservableList<Reservation> reservationObservableList = FXCollections.observableList(reservations);
      allMyReservation.set(reservationObservableList);
    }
    newReservations.set(roomObservableList);
  }

  public void fillHiddenField()
  {
    Room room = model.getSelectedRoom();
    hiddenFieldRoomNo.set(String.valueOf(room.getRoomNo()));
  }

  public Alerts logIn()
  {
    if (model.getCurrentCustomer().equals(new Guest()))
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
              return new Alerts(Alert.AlertType.NONE,"","");
            }
            catch (Exception e)
            {
              return new Alerts(Alert.AlertType.ERROR,"Logging in Error",user.getUsername()+" is currently logged in on another device");
            }
          }
          else
          {
            return new Alerts(Alert.AlertType.ERROR,"Invalid password","Invalid password try one more time");
          }
        }
      }
      return new Alerts(Alert.AlertType.ERROR,"Error","User does not exist");
    }
    else
    {
      return new Alerts(Alert.AlertType.NONE,"","");
    }
  }

  public Alerts logOut()
  {
    try
    {
      model.logOut();
      model.setGuest();
      return new Alerts(Alert.AlertType.NONE,"","");
    }
    catch (RemoteException e)
    {
      return new Alerts(Alert.AlertType.ERROR,"Logging out error","Contact the developers of the system\nPhone number: +45 8755 4243\nPhone number: +45 8755 4222");
    }
  }

  public boolean previousView()
  {
    return model.getPreviousView();
  }

  public void saveRoom(Room room)
  {
    model.saveSelectedRoom(room);
  }

  public void saveReservation(Reservation reservation)
  {
    model.saveSelectedReservation(reservation);
  }

  public Alerts getSelectedReservation()
  {
    Reservation x = model.getSelectedReservation();
    if (x == null)
    {
      return new Alerts(Alert.AlertType.ERROR,"Error","Please select a reservation");
    }
    if (MyDate.today().isBefore(x.getToDate()))
    {
      return new Alerts(Alert.AlertType.ERROR,"Error","You cannot leave a review before you had enough time to enjoy your stay.\nYou can leave a review after you finish your stay");
    }
    return new Alerts(Alert.AlertType.NONE,"","");
  }

  public Alerts reservationEditCheckers()
  {
    Reservation temp = model.getSelectedReservation();
    if (temp == null)
    {
      return new Alerts(Alert.AlertType.ERROR,"Error","Please, select a reservation to edit first");
    }
    else if (temp.getState().equals("Booked"))
    {
      return new Alerts(Alert.AlertType.ERROR,"Error","Sorry, you cannot edit reservation after checking in");
    }
    else if (temp.getState().equals("Reserved"))
    {
      return new Alerts(Alert.AlertType.NONE,"","");
    }
    else
    {
      return new Alerts(Alert.AlertType.ERROR,"Error","Sorry, you cannot edit past reservations");
    }
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

  public Boolean addReservation() throws RemoteException
  {
    if (user == null)
    {
      Alert notLoggedIn = new Alert(Alert.AlertType.INFORMATION);
      notLoggedIn.setHeaderText("Log in required");
      notLoggedIn.setContentText("Please log in into the system or create new account");
      notLoggedIn.showAndWait();
      return null;
    }
    else
    {
      try
      {
        if (fromDateNewReservation.getValue() == null || toDateNewReservation.getValue() == null)
          throw new IllegalDateException(8);
        if (MyDate.LocalDateToMyDate(fromDateNewReservation.getValue()).isBefore(MyDate.today())||MyDate.LocalDateToMyDate(toDateNewReservation.getValue()).isBefore(MyDate.today()))
          throw new IllegalDateException(9);
        String state = model.addReservation(Integer.parseInt(hiddenFieldRoomNo.getValue()),
            model.getCurrentCustomer().getUsername(), MyDate.LocalDateToMyDate(fromDateNewReservation.getValue()),
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
        Alert alert = new Alert(Alert.AlertType.ERROR, e.message(), ButtonType.OK);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.showAndWait();
      }
      return false;
    }
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

    if (toDateNewReservation.getValue() != null && fromDateNewReservation.getValue() != null)
    {
      if (MyDate.LocalDateToMyDate(toDateNewReservation.getValue()).isBefore(MyDate.LocalDateToMyDate(fromDateNewReservation.getValue())))
      {
        throw new IllegalDateException(10);
      }
      roomObservableList = FXCollections.observableList(model.getFilteredRoom(MyDate.LocalDateToMyDate(fromDateNewReservation.getValue()), MyDate.LocalDateToMyDate(toDateNewReservation.getValue()), temp));
    }
    else
    {
      roomObservableList = FXCollections.observableList(model.getFilteredRoom(null, null, temp));
    }
    newReservations.set(roomObservableList);
  }

  public Alerts addReservation()
  {
    if (user == null)
    {
      return null;
    }
    else
    {
      try
      {
        if (fromDateNewReservation.getValue() == null || toDateNewReservation.getValue() == null)
        {
          throw new IllegalDateException(8);
        }
        String state = model.addReservation(Integer.parseInt(hiddenFieldRoomNo.getValue()), model.getCurrentCustomer().getUsername(), MyDate.LocalDateToMyDate(fromDateNewReservation.getValue()), MyDate.LocalDateToMyDate(toDateNewReservation.getValue()), false);
        if (state.equals(DatabaseConnection.SUCCESS))
        {
          return new Alerts(Alert.AlertType.INFORMATION,"Success","Successfully added a new reservation");
        }
        else
        {
          return new Alerts(Alert.AlertType.ERROR,"Error","Error occurred");
        }
      }
      catch (NumberFormatException e)
      {
        return new Alerts(Alert.AlertType.ERROR,"Error","Please select a room to reserve");
      }
      catch (IllegalDateException e)
      {
        return new Alerts(Alert.AlertType.ERROR,"Date error",e.message());
      }
      catch (RemoteException e)
      {
        return new Alerts(Alert.AlertType.ERROR,"Error","Error occurred");
      }
    }
  }

  public Alerts cancelReservation()
  {
    Reservation x = model.getSelectedReservation();
    if (x == null)
    {
      return new Alerts(Alert.AlertType.ERROR,"Error","Select a reservation first");
    }
    else if (x.isCheckedIn())
    {
      return new Alerts(Alert.AlertType.ERROR,"Error","You cannot cancel active booking");
    }
    else if (!x.isCheckedIn())
    {
      try
      {
        if (model.deleteReservation(x.getRoomNumber(),x.getUsername(),x.getFromDate()).equals(DatabaseConnection.SUCCESS))
        {
          return new Alerts(Alert.AlertType.INFORMATION,"Success","The reservation has been canceled.");
        }
        else
        {
          return new Alerts(Alert.AlertType.ERROR,"Error","You cannot cancel this reservation");
        }
      }
      catch (RemoteException e)
      {
        return new Alerts(Alert.AlertType.ERROR,"Error","Error occurred");
      }
    }
    return new Alerts(Alert.AlertType.ERROR,"Error","Error occurred");
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      update();
    });
  }
}
