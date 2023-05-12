package Client.Model;

import Server.Model.Customer;
import Server.Model.Employee;
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

  ArrayList<Employee> getAllEmployees() throws RemoteException;
  ArrayList<Employee> filterEmployee(String employee) throws RemoteException;

  String updateRoom(int roomNumber, int numberOfBeds, int size,int price,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony) throws RemoteException;
  String updateCustomer(String username, String firstName, String lastName, String phoneNumber, String payment) throws RemoteException;

  String updateEmployee( String firstName, String lastName, String position, String phoneNo) throws RemoteException;
  String deleteRoom(int roomNumber) throws RemoteException;
  String deleteEmployee(String userID) throws RemoteException;

  void saveSelectedRoom(Room room);

  Room getSelectedRoom();

  void saveSelectedCustomer(Customer customer);
  String deleteSelectedCustomer(String username) throws RemoteException;
  void saveSelectedEmployee(Employee employee);
  Employee getSelectedEmployee();

  Customer getSelectedCustomer();

  void addPropertyChangeListener(PropertyChangeListener listener);

}
