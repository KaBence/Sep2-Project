package Client.Model;

import Server.Model.Hotel.Reservation;
import Server.Model.Hotel.Review;
import Server.Model.Hotel.Room;
import Server.Model.Hotel.Users.Customer;
import Server.Model.Hotel.Users.Person;
import Server.Model.MyDate;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ModelCustomerSide extends PropertyChangeListener
{
  Person logIn(Person user) throws RemoteException;
  Person logOut() throws RemoteException;
  ArrayList<Reservation> getAllMyReservation(String username) throws
      RemoteException;

  ArrayList<Review> getAllReviews() throws RemoteException;
  ArrayList<Customer> getAllCustomers() throws RemoteException;

  String addReview(String username, int roomNO, MyDate fromDate, MyDate postedDate, String comment) throws RemoteException;
  String addReservation(int roomNumber, String username, MyDate fromDate, MyDate toDate, boolean CheckedIn) throws RemoteException;
  String addCustomer(String username, String password, String firstName, String lastName, String phoneNO, String paymentInfo) throws RemoteException;

  String updateReservation(int roomNumber, String username, MyDate fromDate, MyDate toDate,int oldRoomNo,String oldUsername,MyDate oldFromDate,MyDate oldToDate) throws RemoteException;

  void saveSelectedReservation(Reservation reservation);
  void saveSelectedRoom(Room room);

  Room getSelectedRoom();
  ArrayList<Room> getAllRooms() throws RemoteException;
  ArrayList<Room> getFilteredRoom(MyDate from,MyDate to,String... attr) throws RemoteException;
  Reservation getSelectedReservation();
  String deleteReservation(int roomNo,String username, MyDate fromDate)throws RemoteException;
  Person getCurrentCustomer();
  Person setGuest();


  void setPreviousView(boolean b);
  boolean getPreviousView();

  void addPropertyChangeListener(PropertyChangeListener listener);
}
