package Client.ViewModel;

import Client.Model.Model;
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

public class CustomerHomeViewModel  implements PropertyChangeListener
{
  private Model model;

  private SimpleObjectProperty<ObservableList<Room>> allRooms;
  private SimpleStringProperty password;
  private SimpleStringProperty username;

  private ArrayList<Customer> allCustomers;
  private Person user;

private SimpleObjectProperty<ObservableList<Review>> allReviews;
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
      allCustomers = new ArrayList<>();
    }
    this.password = new SimpleStringProperty();
    this.username = new SimpleStringProperty();
  }

  public void bindRooms(ObjectProperty<ObservableList<Room>> property)
  {
    property.bindBidirectional(allRooms);
  }
public void bindReviews(ObjectProperty<ObservableList<Review>> property){
    property.bindBidirectional(allReviews);
}

  public void bindUsername(StringProperty property)
  {
    property.bindBidirectional(username);
  }

  public void bindPassword(StringProperty property)
  {
    property.bindBidirectional(password);
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
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("Something went wrong");
            error.setContentText("Contact the developers of the system\nPhone number: +45 8755 4243\nPhone number: +45 8755 4222");
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

  public void update()
  {
    ArrayList<Room> rooms;
    ArrayList<Review> reviews;
    try
    {
      rooms = model.getAllRooms();
      reviews= model.getAllReviews();
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
    ObservableList<Room> roomObservableList = FXCollections.observableList(rooms);
    ObservableList<Review> reviewObservableList= FXCollections.observableList(reviews);
    allReviews.set(reviewObservableList);
    allRooms.set(roomObservableList);
  }


  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      update();
    });
  }
}
