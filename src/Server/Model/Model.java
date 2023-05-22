package Server.Model;

import Server.Model.Hotel.Review;
import Server.Model.Hotel.Users.Customer;
import Server.Model.Hotel.Users.Employee;
import Server.Model.Hotel.Reservation;
import Server.Model.Hotel.Room;
import Server.Model.Hotel.Users.Person;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Model
{
  void addListener(PropertyChangeListener listener);
  Person logIn(Person user);
  Person logOut(Person user);
  ArrayList<Person> getAllRegisteredUsers();

  String addRoom(int roomNumber, int numberOfBeds, int size, int price,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony);
  String  addReservation(int roomNumber, String username,
      MyDate fromDate, MyDate toDate, boolean CheckedIn);
  Employee addEmployee(String firstName, String lastName,
      String position, String phoneNo, String password);

  String addReview(String username, int roomNO,
      MyDate fromDate, MyDate postedDate, String comment);

  String updateRoom(int roomNumber, int numberOfBeds, int size,int price,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony);
  String updateCustomer(String username, String firstName, String lastName, String phoneNumber, String payment);

  String updateEmployee( String username, String firstName, String lastName, String position, String phoneNo) throws
      RemoteException;
  String deleteRoom(int roomNumber);
  String deleteSelectedCustomer(String username);
  String deleteEmployee(String userID);
  String deleteReservation(int roomNo, String username,
      MyDate fromDate);

  ArrayList<Room> getAllRooms();
  ArrayList<Review> getAllReviews();

  ArrayList<Room> getSimpleFilteredRooms(String room);



  ArrayList<Room> getFilteredRooms(MyDate from,MyDate to,String... attr);
  ArrayList<Customer> getFilteredCustomers(String...attr);

  ArrayList<Customer> getAllCustomers();
  ArrayList<Customer> getCustomersFromDatabase();
  ArrayList<Customer> filterCustomer(String customer);
  ArrayList<Employee> getAllEmployees();
  ArrayList<Employee> getEmployeesFromDatabase();
  ArrayList<Employee> filterEmployee(String employee);
  ArrayList<Employee> getFilteredEmployee(String... attr);

  ArrayList<Reservation> getAllReservations();
  ArrayList<Reservation> getFilteredReservations(String state,MyDate fromDate,MyDate toDate);



  String checkIn(int roomNumber, String username, MyDate fromDate);
  String checkOut(int roomNumber, String username, MyDate fromDate);
  String updateReservation(int roomNumber, String username, MyDate fromDate, MyDate toDate,int oldRoomNo,String oldUsername,MyDate oldFromDate);
  String addCustomer(String username, String password,
      String firstName, String lastName, String phoneNo, String paymentInfo);

}
