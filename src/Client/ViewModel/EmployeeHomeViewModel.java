package Client.ViewModel;

import Client.Model.Model;
import Server.Model.Room;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class EmployeeHomeViewModel implements PropertyChangeListener
{
  private Model model;
  private SimpleObjectProperty<ObservableList<Room>> rooms;
  private PropertyChangeSupport support;

  public EmployeeHomeViewModel(Model model)
  {
    this.model = model;
    this.rooms = new SimpleObjectProperty<>();
    this.support = new PropertyChangeSupport(this);
  }

  public void bindRoomList(ObjectProperty<ObservableList<Room>> property)
  {
    property.bindBidirectional(rooms);
  }

  public void update(){
    ArrayList<Room> allRooms;
    try
    {
      allRooms = model.getAllRooms();
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
    ObservableList<Room> observableList = FXCollections.observableList(
        allRooms);
    rooms.set(observableList);
  }

  public void saveRoom(Room room){
    model.saveSelectedRoom(room);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {

    });
  }
}
