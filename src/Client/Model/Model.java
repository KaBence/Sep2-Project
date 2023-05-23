package Client.Model;

import Server.Model.*;
import Server.Model.Hotel.Review;
import Server.Model.Hotel.Users.Customer;
import Server.Model.Hotel.Users.Employee;
import Server.Model.Hotel.Reservation;
import Server.Model.Hotel.Room;
import Server.Model.Hotel.Users.Person;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

public interface Model
{
  boolean getCurrent();
  Person setGuest();
  Person logIn(Person user) throws RemoteException;
  Person logOut() throws RemoteException;
  String addRoom(int roomNumber, int numberOfBeds, int size,int price,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony) throws RemoteException;
  String  addReservation(int roomNumber, String username, MyDate fromDate, MyDate toDate, boolean CheckedIn) throws RemoteException;
  String  addEmployee(String firstName, String lastName, String position,
      String phoneNo, String password) throws RemoteException;
  String addReview(String username, int roomNO, MyDate fromDate, MyDate postedDate, String comment) throws RemoteException;
  ArrayList<Room> getAllRooms() throws RemoteException;
  ArrayList<Review> getAllReviews() throws RemoteException;
  ArrayList<Reservation> getAllMyReservation(String username) throws RemoteException;

  ArrayList<Room> getSimpleFilteredRoom(String room) throws RemoteException;

  ArrayList<Room> getFilteredRoom(MyDate from,MyDate to,String... attr) throws RemoteException;
  ArrayList<Customer> getFilteredCustomers(String...attr) throws RemoteException;

  ArrayList<Customer> getAllCustomers() throws RemoteException;
  ArrayList<Customer> filterCustomer(String customer) throws RemoteException;

  ArrayList<Employee> getAllEmployees() throws RemoteException;
  ArrayList<Employee> filterEmployee(String employee) throws RemoteException;
  ArrayList<Employee> getFilteredEmployee(String... attr) throws RemoteException;

  ArrayList<Reservation> getAllReservations() throws RemoteException;

  ArrayList<Reservation> getFilteredReservation(String state,MyDate fromDate,MyDate toDate)
      throws RemoteException;


  String updateRoom(int roomNumber, int numberOfBeds, int size,int price,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony) throws RemoteException;
  String updateCustomer(String username, String firstName, String lastName, String phoneNumber, String payment) throws RemoteException;

  String updateEmployee( String username, String firstName, String lastName, String position, String phoneNo) throws RemoteException;

  String updateReservation(int roomNumber, String username, MyDate fromDate, MyDate toDate,int oldRoomNo,String oldUsername,MyDate oldFromDate,MyDate oldToDate) throws RemoteException;
  String deleteRoom(int roomNumber) throws RemoteException;
  String deleteEmployee(String userID) throws RemoteException;
  String deleteReservation(int roomNo,String username, MyDate fromDate)throws RemoteException;

  void saveSelectedRoom(Room room);

  Room getSelectedRoom();

  void saveSelectedReservation(Reservation reservation);

  Reservation getSelectedReservation();

  void saveSelectedCustomer(Customer customer);
  String deleteSelectedCustomer(String username) throws RemoteException;
  void saveSelectedEmployee(Employee employee);
  Employee getSelectedEmployee();

  Customer getSelectedCustomer();

  void addPropertyChangeListener(PropertyChangeListener listener);

  String checkIn(int roomNumber, String username,
      MyDate fromDate) throws RemoteException;
  String checkOut(int roomNumber, String username,
      MyDate fromDate) throws RemoteException;
Person getCurrentCustomer();

  String addCustomer(String username, String password,
      String firstName, String lastName, String phoneNO, String paymentInfo) throws RemoteException;
}
