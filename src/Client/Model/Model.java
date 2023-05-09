package Client.Model;

import Server.Model.Room;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Model
{
  Room addRoom(int roomNumber, int numberOfBeds, int size,int price,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony) throws RemoteException;
  ArrayList<Room> getAllRooms() throws RemoteException;

  void updateRoom(int roomNumber, int numberOfBeds, int size,int price,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony) throws RemoteException;

  void deleteRoom(int roomNumber) throws RemoteException;

  void saveSelectedRoom(Room room);

  Room getSelectedRoom();

  void addPropertyChangeListener(PropertyChangeListener listener);

}
