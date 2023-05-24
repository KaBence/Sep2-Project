package Client.Model;

import Server.Model.Hotel.Reservation;
import Server.Model.Hotel.Room;
import Server.Model.Hotel.Users.Customer;
import Server.Model.Hotel.Users.Employee;
import Server.Model.Hotel.Users.Person;
import Server.Model.MyDate;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ModelEmployeeSide
{
  Person setGuest();
  Person logIn(Person user) throws RemoteException;
  Person logOut() throws RemoteException;

  String addRoom(int roomNumber, int numberOfBeds, int size,int price,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony) throws RemoteException;
  String  addEmployee(String firstName, String lastName, String position,
      String phoneNo, String password) throws RemoteException;
  String addCustomer(String username, String password, String firstName, String lastName, String phoneNO, String paymentInfo) throws RemoteException;
  String addReservation(int roomNumber, String username, MyDate fromDate, MyDate toDate, boolean CheckedIn) throws RemoteException;



  ArrayList<Room> getAllRooms() throws RemoteException;
  ArrayList<Room> getSimpleFilteredRoom(String room) throws RemoteException;
  ArrayList<Room> getFilteredRoom(MyDate from,MyDate to,String... attr) throws RemoteException;



  ArrayList<Employee> getAllEmployees() throws RemoteException;
  ArrayList<Employee> getFilteredEmployee(String... attr) throws RemoteException;

  ArrayList<Customer> getAllCustomers() throws RemoteException;
  ArrayList<Customer> getFilteredCustomers(String...attr) throws RemoteException;


  ArrayList<Reservation> getAllReservations() throws RemoteException;
  ArrayList<Reservation> getFilteredReservation(String state,MyDate fromDate,MyDate toDate) throws RemoteException;

  Employee getSelectedEmployee();
  Customer getSelectedCustomer();
  Room getSelectedRoom();
  Reservation getSelectedReservation();
  Person getCurrentCustomer();
  Employee getNewEmployee() throws RemoteException;





  void saveSelectedEmployee(Employee employee);
  void saveSelectedCustomer(Customer customer);
  void saveSelectedReservation(Reservation reservation);
  void saveSelectedRoom(Room room);




  String deleteSelectedCustomer(String username) throws RemoteException;





  String updateRoom(int roomNumber, int numberOfBeds, int size,int price,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony) throws RemoteException;

  String deleteEmployee(String userID) throws RemoteException;
  String deleteRoom(int roomNumber) throws RemoteException;

  String updateEmployee( String username, String firstName, String lastName, String position, String phoneNo) throws RemoteException;

  String updateCustomer(String username, String firstName, String lastName, String phoneNumber, String payment) throws RemoteException;

  String updateReservation(int roomNumber, String username, MyDate fromDate, MyDate toDate,int oldRoomNo,String oldUsername,MyDate oldFromDate,MyDate oldToDate) throws RemoteException;

  String deleteReservation(int roomNo,String username, MyDate fromDate)throws RemoteException;

  ArrayList<Customer> filterCustomer(String customer) throws RemoteException;

  ArrayList<Employee> filterEmployee(String employee) throws RemoteException;

  String checkIn(int roomNumber, String username, MyDate fromDate) throws RemoteException;
  String checkOut(int roomNumber, String username, MyDate fromDate) throws RemoteException;


}
