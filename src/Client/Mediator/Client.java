package Client.Mediator;

import Server.Model.*;
import Server.Model.Hotel.Review;
import Server.Model.Hotel.Users.Customer;
import Server.Model.Hotel.Users.Employee;
import Server.Model.Hotel.Reservation;
import Server.Model.Hotel.Room;
import Server.Model.Hotel.Users.Person;
import Shared.SharedInterface;
import dk.via.remote.observer.RemotePropertyChangeEvent;
import dk.via.remote.observer.RemotePropertyChangeListener;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Client extends UnicastRemoteObject implements
    RemotePropertyChangeListener
{
  private SharedInterface sharedInterface;
  private PropertyChangeSupport support;
  public Client(SharedInterface sharedInterface) throws RemoteException{
    support=new PropertyChangeSupport(this);
    this.sharedInterface=sharedInterface;
    this.sharedInterface.addPropertyChangeListener(this);
  }

  @Override public void propertyChange(
      RemotePropertyChangeEvent remotePropertyChangeEvent)
      throws RemoteException
  {
    support.firePropertyChange("update",null,remotePropertyChangeEvent.getNewValue());
  }

  public Person logIn(Person user) throws RemoteException
  {
    return sharedInterface.logIn(user);
  }

  public Person logOut(Person user) throws RemoteException
  {
    return sharedInterface.logOut(user);
  }

  public String addRoom(int roomNumber, int numberOfBeds, int size, int price,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony) throws RemoteException
  {
    return sharedInterface.addRoom(roomNumber, numberOfBeds, size, price, orientation, internet, bathroom, kitchen, balcony);
  }
  public String  addReservation(int roomNumber, String username, MyDate fromDate, MyDate toDate, boolean CheckedIn) throws RemoteException{
    return sharedInterface.addReservation(roomNumber, username, fromDate, toDate, CheckedIn);
  }
public Employee addEmployee(String firstName, String lastName, String position,
    String phoneNo, String password) throws RemoteException
{
    return sharedInterface.addEmployee(firstName, lastName, position,phoneNo,password);
}
public String addReview(String username, int roomNO, MyDate fromDate, MyDate postedDate, String comment)
    throws RemoteException
{
    return  sharedInterface.addReview(username, roomNO, fromDate, postedDate, comment);
};

  public String addCustomer(String username, String password,
      String firstName, String lastName, String phoneNo, String paymentInfo)
      throws RemoteException
  {
    return sharedInterface.addCustomer(username,password,firstName,lastName,phoneNo,paymentInfo);
  }
  public ArrayList<Room> getAllRooms() throws RemoteException
  {
    return sharedInterface.getAllRooms();
  }
  public ArrayList<Review> getAllReviews() throws RemoteException{
    return sharedInterface.getAllReviews();
  }
public ArrayList<Reservation> getAllMyReservation(String username) throws RemoteException{
    return sharedInterface.getAllMyReservation(username);
}
  public ArrayList<Room> getSimpleFilteredRoom(String room) throws RemoteException
  {
    return sharedInterface.getSimpleFilteredRoom(room);
  }

  public ArrayList<Room> getFilteredRooms(MyDate from,MyDate to,String... attr) throws RemoteException
  {
    return sharedInterface.getFilteredRoom(from,to,attr);
  }
  public ArrayList<Customer> getFilteredCustomer(String... attr) throws RemoteException{
    return sharedInterface.getFilteredCustomer(attr);
  }

  public ArrayList<Customer> getAllCustomers()throws RemoteException{
    return sharedInterface.getAllCustomers();
  }

  public ArrayList<Customer> filterCustomer(String employee) throws RemoteException
  {
    return sharedInterface.filterCustomer(employee);
  }
  public ArrayList<Employee> getAllEmployees() throws RemoteException
  {
    return sharedInterface.getAllEmployees();
  }

  public ArrayList<Employee> filterEmployee(String employee) throws RemoteException
  {
    return sharedInterface.filterEmployee(employee);
  }

  public ArrayList<Employee> getFilteredEmployee(String... attr) throws RemoteException
  {
    return sharedInterface.getFilteredEmployee(attr);
  }

  public String updateRoom(int roomNumber, int numberOfBeds, int size,int price,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony) throws RemoteException
  {
    return sharedInterface.updateRoom(roomNumber, numberOfBeds, size, price, orientation, internet, bathroom, kitchen, balcony);
  }

  public String updateCustomer(String username,String firstName, String lastName, String phoneNumber, String payment) throws RemoteException{
    return sharedInterface.updateCustomer(username,firstName,lastName,phoneNumber,payment);
  }
  public String updateEmployee( String username, String firstName, String lastName, String position, String phoneNo)
      throws RemoteException
  {
    return sharedInterface.updateEmployee(username, firstName,lastName,position,phoneNo);
  }

  public String deleteRoom(int roomNumber) throws RemoteException
  {
    return sharedInterface.deleteRoom(roomNumber);
  }

  public String deleteSelectedCustomer(String username) throws RemoteException{
    return sharedInterface.deleteSelectedCustomer(username);
  }
  public String deleteReservation(int roomNo, String username,
      MyDate fromDate) throws RemoteException
  {
    return sharedInterface.deleteReservation(roomNo,username,fromDate);
  }

  public String deleteEmployee(String userID) throws RemoteException
  {
    return sharedInterface.deleteEmployee(userID);
  }

  public ArrayList<Reservation> getAllReservations() throws RemoteException
  {
    return sharedInterface.getAllReservations();
  }

  public ArrayList<Reservation> getFilteredReservation(String state,MyDate fromDate,MyDate toDate) throws RemoteException
  {
    return sharedInterface.getFilteredReservation(state, fromDate, toDate);
  }

  public String updateReservation(int roomNumber, String username, MyDate fromDate, MyDate toDate,int oldRoomNo,String oldUsername,MyDate oldFromDate)
      throws RemoteException
  {
    return sharedInterface.updateReservation(roomNumber, username, fromDate, toDate,oldRoomNo,oldUsername,oldFromDate);
  }

  public void addPropertyChangeListener(PropertyChangeListener listener){
    support.addPropertyChangeListener(listener);
  }


  public String checkIn(int roomNumber, String username,
      MyDate fromDate) throws RemoteException
  {
    return sharedInterface.checkIn(roomNumber,username,fromDate);
  }
  public String checkOut(int roomNumber, String username,
      MyDate fromDate) throws RemoteException
  {
    return sharedInterface.checkOut(roomNumber,username,fromDate);
  }
}
