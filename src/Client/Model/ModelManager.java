package Client.Model;

import Client.Mediator.Client;
import Server.Model.Room;
import Shared.SharedInterface;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class ModelManager implements Model,PropertyChangeListener
{
  private Client client;
  private Room selectedRoom;

  private PropertyChangeSupport support;
  public ModelManager() throws IOException, NotBoundException
  {
    Registry registry= LocateRegistry.getRegistry(1337);
    SharedInterface sharedInterface=(SharedInterface) registry.lookup("HotelServer");
    client=new Client(sharedInterface);
    client.addPropertyChangeListener(this);
    support=new PropertyChangeSupport(this);
  }

  @Override public Room addRoom(int roomNumber, int numberOfBeds, int size,int price,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony) throws RemoteException
  {
    return client.addRoom(roomNumber, numberOfBeds, size,price, orientation, internet, bathroom, kitchen, balcony);
  }

  @Override public ArrayList<Room> getAllRooms() throws RemoteException
  {
    return client.getAllRooms();
  }

  @Override public String updateRoom(int roomNumber, int numberOfBeds, int size,
      int price, String orientation, boolean internet, boolean bathroom,
      boolean kitchen, boolean balcony) throws RemoteException
  {
    return client.updateRoom(roomNumber, numberOfBeds, size,price, orientation, internet, bathroom, kitchen, balcony);
  }

  @Override public String deleteRoom(int roomNumber) throws RemoteException
  {
    return client.deleteRoom(roomNumber);
  }

  @Override public void saveSelectedRoom(Room room)
  {
    selectedRoom=room;
  }

  @Override public Room getSelectedRoom()
  {
    return selectedRoom;
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    support.firePropertyChange("update",null,evt.getNewValue());
  }
}
