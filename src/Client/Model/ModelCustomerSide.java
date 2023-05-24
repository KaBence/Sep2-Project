package Client.Model;

import Server.Model.Hotel.Reservation;
import Server.Model.Hotel.Review;
import Server.Model.Hotel.Room;
import Server.Model.Hotel.Users.Person;
import Server.Model.MyDate;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ModelCustomerSide
{
  Person logIn(Person user) throws RemoteException;
  Person logOut() throws RemoteException;
  ArrayList<Reservation> getAllMyReservation(String username) throws
      RemoteException;

  ArrayList<Review> getAllReviews() throws RemoteException;

  String addReview(String username, int roomNO, MyDate fromDate, MyDate postedDate, String comment) throws RemoteException;
  String addReservation(int roomNumber, String username, MyDate fromDate, MyDate toDate, boolean CheckedIn) throws RemoteException;
  String addCustomer(String username, String password, String firstName, String lastName, String phoneNO, String paymentInfo) throws RemoteException;


  void saveSelectedReservation(Reservation reservation);
  void saveSelectedRoom(Room room);

  Room getSelectedRoom();
  Reservation getSelectedReservation();
  Person getCurrentCustomer();





  void setPreviousView(boolean b);
  boolean getPreviousView();

}
