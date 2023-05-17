package Server.Model;

import Server.Model.Hotel.Users.Customer;
import Server.Model.Hotel.Users.Employee;
import Server.Model.Hotel.Reservation;
import Server.Model.Hotel.Room;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
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

  String updateEmployee( String username, String firstName, String lastName, String position, String phoneNo) throws
      RemoteException;
  String deleteRoom(int roomNumber);
  String deleteSelectedCustomer(String username);
  String deleteEmployee(String userID);
  String deleteCustomer(int roomNo, String username,
      MyDate fromDate);

  ArrayList<Room> getAllRooms();

  ArrayList<Room> getSimpleFilteredRooms(String room);

  ArrayList<Room> getFilteredRooms(String... attr);
  ArrayList<Customer> getFilteredCustomers(String...attr);

  ArrayList<Customer> getAllCustomers();
  ArrayList<Customer> filterCustomer(String customer);
  ArrayList<Employee> getAllEmployees();
  ArrayList<Employee> filterEmployee(String employee);
  ArrayList<Employee> getFilteredEmployee(String... attr);

  ArrayList<Reservation> getAllReservations();
  ArrayList<Reservation> getFilteredReservations(String state,MyDate fromDate,MyDate toDate);

  String updateReservation(int roomNumber, String username, MyDate fromDate, MyDate toDate);
}
