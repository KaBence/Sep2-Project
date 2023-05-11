package Client.Mediator;

import Server.Model.Room;
import Shared.SharedInterface;
import dk.via.remote.observer.RemotePropertyChangeEvent;
import dk.via.remote.observer.RemotePropertyChangeListener;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Client extends UnicastRemoteObject implements
    RemotePropertyChangeListener
{
  private SharedInterface sharedInterface;
  private PropertyChangeSupport support;
  public Client(SharedInterface sharedInterface) throws RemoteException{
    support=new PropertyChangeSupport(this);
    this.sharedInterface=sharedInterface;
    this.sharedInterface.addPropertyChangeListener(this);
  }

  @Override public void propertyChange(
      RemotePropertyChangeEvent remotePropertyChangeEvent)
      throws RemoteException
  {
    support.firePropertyChange("update",null,remotePropertyChangeEvent.getNewValue());
  }

  public Room addRoom(int roomNumber, int numberOfBeds, int size, int price,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony) throws RemoteException
  {
    return sharedInterface.addRoom(roomNumber, numberOfBeds, size, price, orientation, internet, bathroom, kitchen, balcony);
  }

  public ArrayList<Room> getAllRooms() throws RemoteException
  {
    return sharedInterface.getAllRooms();
  }

  public void updateRoom(int roomNumber, int numberOfBeds, int size,int price,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony) throws RemoteException
  {
    sharedInterface.updateRoom(roomNumber, numberOfBeds, size, price, orientation, internet, bathroom, kitchen, balcony);
  }

  public String deleteRoom(int roomNumber) throws RemoteException
  {
    return sharedInterface.deleteRoom(roomNumber);
  }

  public void addPropertyChangeListener(PropertyChangeListener listener){
    support.addPropertyChangeListener(listener);
  }
}
