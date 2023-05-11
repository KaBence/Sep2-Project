package Client.Model;

import Server.Model.Customer;
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

  ArrayList<Customer> getAllCustomers() throws RemoteException;

  String updateRoom(int roomNumber, int numberOfBeds, int size,int price,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony) throws RemoteException;

  String deleteRoom(int roomNumber) throws RemoteException;

  void saveSelectedRoom(Room room);

  Room getSelectedRoom();

  void saveSelectedCustomer(Customer customer);

  Customer getSelectedCustomer();

  void addPropertyChangeListener(PropertyChangeListener listener);

}
