package Client.Mediator;

import Server.Model.Room;
import Shared.SharedInterface;
import dk.via.remote.observer.RemotePropertyChangeEvent;
import dk.via.remote.observer.RemotePropertyChangeListener;

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
  }

  @Override public void propertyChange(
      RemotePropertyChangeEvent remotePropertyChangeEvent)
      throws RemoteException
  {

  }

  public void addRoom(int roomNumber, int numberOfBeds, int size, int price,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony) throws RemoteException
  {
    sharedInterface.addRoom(roomNumber, numberOfBeds, size, price, orientation, internet, bathroom, kitchen, balcony);
  }

  public ArrayList<Room> getAllRooms() throws RemoteException
  {
    return sharedInterface.getAllRooms();
  }
}
