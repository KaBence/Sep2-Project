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
  String updateCustomer(String username, String firstName, String lastName, String phoneNumber, String payment);

  String deleteRoom(int roomNumber);
  String deleteSelectedCustomer(String username);

  ArrayList<Room> getAllRooms();

  ArrayList<Room> getFilteredRooms(String room);

  ArrayList<Customer> getAllCustomers();
  ArrayList<Employee> getAllEmployees();
}
