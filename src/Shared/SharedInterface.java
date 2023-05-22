package Shared;

import Server.Model.*;
import Server.Model.Hotel.Review;
import Server.Model.Hotel.Users.Customer;
import Server.Model.Hotel.Users.Employee;
import Server.Model.Hotel.Reservation;
import Server.Model.Hotel.Room;
import Server.Model.Hotel.Users.Person;
import dk.via.remote.observer.RemotePropertyChangeListener;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface SharedInterface extends Remote
{
  Person logIn(Person user) throws RemoteException;
  Person logOut(Person user) throws RemoteException;
  void addPropertyChangeListener(RemotePropertyChangeListener listener) throws RemoteException;
  String addRoom(int roomNumber, int numberOfBeds, int size, int price,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony) throws RemoteException;

  String  addReservation(int roomNumber, String username, MyDate fromDate, MyDate toDate, boolean CheckedIn) throws RemoteException;
  Employee addEmployee(String firstName, String lastName, String position,
      String phoneNo, String password) throws RemoteException;
  String addReview(String username, int roomNO, MyDate fromDate, MyDate postedDate, String comment) throws RemoteException;
  ArrayList<Room> getAllRooms() throws RemoteException;
  ArrayList<Review> getAllReviews() throws RemoteException;
  ArrayList<Reservation> getAllMyReservation(String username) throws RemoteException;

  ArrayList<Room> getSimpleFilteredRoom(String room) throws RemoteException;

  ArrayList<Room> getFilteredRoom(MyDate from,MyDate to,String... attr) throws RemoteException;
  ArrayList<Customer> getFilteredCustomer(String...attr) throws RemoteException;

  ArrayList<Customer> getAllCustomers() throws RemoteException;
  ArrayList<Customer> filterCustomer(String customer) throws RemoteException;

  ArrayList<Employee> getAllEmployees() throws RemoteException;
  ArrayList<Employee> filterEmployee(String employee) throws RemoteException;
  ArrayList<Employee> getFilteredEmployee(String... attr) throws RemoteException;

  String updateRoom(int roomNumber, int numberOfBeds, int size,int price,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony) throws RemoteException;
  String updateCustomer(String username, String firstName, String lastName, String phoneNumber, String payment) throws RemoteException;

  String deleteRoom(int roomNumber) throws RemoteException;
  String deleteSelectedCustomer(String username) throws RemoteException;

  String updateEmployee(String username, String firstName, String lastName, String position, String phoneNo) throws RemoteException;

  String deleteEmployee(String userID) throws RemoteException;
  String deleteReservation(int roomNo, String username,
      MyDate fromDate) throws RemoteException;

  ArrayList<Reservation> getAllReservations() throws RemoteException;
  ArrayList<Reservation> getFilteredReservation(String state,MyDate fromDate,MyDate toDate) throws RemoteException;


  String checkIn(int roomNumber, String username, MyDate fromDate)
      throws RemoteException;
  String checkOut(int roomNumber, String username, MyDate fromDate)
      throws RemoteException;
  String updateReservation(int roomNumber, String username, MyDate fromDate, MyDate toDate,int oldRoomNo,String oldUsername,MyDate oldFromDate) throws RemoteException;

  String addCustomer(String username, String password,
      String firstName, String lastName, String phoneNo, String paymentInfo) throws RemoteException;

}