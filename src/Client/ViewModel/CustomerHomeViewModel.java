package Client.ViewModel;

import Client.Model.Model;
import Server.Model.Hotel.Reservation;
import Server.Model.Hotel.Review;
import Server.Model.Hotel.Room;
import dk.via.remote.observer.RemotePropertyChangeListener;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
  public CustomerHomeViewModel(Model model)
  {
    this.model = model;
    model.addPropertyChangeListener(this);
    this.allRooms = new SimpleObjectProperty<>();
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
  public void update()
  {
    ArrayList<Room> rooms;
    ArrayList<Review> reviews;
    ArrayList<Reservation> reservations;
    try
    {
      rooms = model.getAllRooms();
      reviews = model.getAllReviews();

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
