package Server.Model;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public interface Model
{
  void addListener(PropertyChangeListener listener);

  Room addRoom(int roomNumber, int numberOfBeds, int size, int price,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony);

  String updateRoom(int roomNumber, int numberOfBeds, int size,int price,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony);

  String deleteRoom(int roomNumber);

  ArrayList<Room> getAllRooms();

  ArrayList<Customer> getAllCustomers();
  ArrayList<Employee> getAllEmployees();
}
