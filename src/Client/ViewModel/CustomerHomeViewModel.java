package Client.ViewModel;

import Client.Model.Model;
import Server.Model.Hotel.Reservation;
import Server.Model.Hotel.Review;
import Server.Model.Hotel.Room;
import Server.Model.Hotel.Users.Customer;
import Server.Model.Hotel.Users.Person;
import Server.Utility.DataBase.DatabaseConnection;
import dk.via.remote.observer.RemotePropertyChangeListener;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class CustomerHomeViewModel implements PropertyChangeListener
{
  private Model model;

  private SimpleObjectProperty<ObservableList<Room>> allRooms;
  private SimpleObjectProperty<ObservableList<Review>> allReviews;
private SimpleObjectProperty<ObservableList<Reservation>> allMyReservation;
  private SimpleStringProperty password;
  private SimpleStringProperty username;

  private ArrayList<Customer> allCustomers;
  private Person user;

  public CustomerHomeViewModel(Model model)
  {
    this.model = model;
    model.addPropertyChangeListener(this);
    this.allRooms = new SimpleObjectProperty<>();
    this.allReviews= new SimpleObjectProperty<>();
    try
    {
      allCustomers = model.getAllCustomers();
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
    this.password = new SimpleStringProperty();
    this.username = new SimpleStringProperty();
    this.allReviews = new SimpleObjectProperty<>();
    this.allMyReservation= new SimpleObjectProperty<>();
  }

  public void bindRooms(ObjectProperty<ObservableList<Room>> property)
  {
    property.bindBidirectional(allRooms);
  }

  public void bindReviews(ObjectProperty<ObservableList<Review>> property)
  {
    property.bindBidirectional(allReviews);
  }
public void bindMyReservation(ObjectProperty<ObservableList<Reservation>> property){
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

  public boolean logIn()
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
            return false;
          }
        }
        else
        {
          return false;
        }
      }
    }
    Alert doesntExist = new Alert(Alert.AlertType.ERROR);
    doesntExist.setHeaderText("User does not exist.");
    doesntExist.showAndWait();
    return false;
  }

  public void update()
  {
    ArrayList<Room> rooms;
    ArrayList<Review> reviews;
    ArrayList<Reservation> reservations;
    try
    {
      rooms = model.getAllRooms();
      reviews = model.getAllReviews();
      model.setGuest();
      reservations=model.getAllMyReservation(model.getCurrentCustomer().getUsername());
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
    allRooms.set(roomObservableList);
    if(reservations!=null)
    {
      ObservableList<Reservation> reservationObservableList = FXCollections.observableList(
          reservations);

      allMyReservation.set(reservationObservableList);
    }

  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      update();
      System.out.println("Test");
    });
  }
}
