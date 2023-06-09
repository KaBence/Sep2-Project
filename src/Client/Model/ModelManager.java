package Client.Model;

import Client.Mediator.Client;
import Server.Model.*;
import Server.Model.Hotel.Review;
import Server.Model.Hotel.Users.Customer;
import Server.Model.Hotel.Users.Employee;
import Server.Model.Hotel.Reservation;
import Server.Model.Hotel.Room;
import Server.Model.Hotel.Users.Guest;
import Server.Model.Hotel.Users.Person;
import Shared.SharedInterface;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class ModelManager implements Model,PropertyChangeListener
{
  private Client client;
  private Room selectedRoom;
  private Customer selectedCustomer;
  private Employee selectedEmployee;
  private Reservation selectedReservation;
  private Person current;
  private boolean previousView;
  private ArrayList<Person> loggedUsers;

  private PropertyChangeSupport support;

  public ModelManager(SharedInterface sharedInterface) throws IOException, NotBoundException
  {
    client=new Client(sharedInterface);
    client.addPropertyChangeListener(this);
    support=new PropertyChangeSupport(this);
    loggedUsers = new ArrayList<>();
    previousView = true;
  }

  public ModelManager() throws IOException, NotBoundException
  {
    Registry registry= LocateRegistry.getRegistry(1377);
    SharedInterface sharedInterface=(SharedInterface) registry.lookup("HotelServer");
    client=new Client(sharedInterface);
    client.addPropertyChangeListener(this);
    support=new PropertyChangeSupport(this);
    loggedUsers = new ArrayList<>();
    previousView = true;
  }

  @Override public boolean getCurrent()
  {
    return current != null;
  }

  @Override public Person logIn(Person user) throws RemoteException
  {
    current = client.logIn(user);
    loggedUsers.add(current);
    return current;
  }

  @Override public Person logOut() throws RemoteException
  {
    loggedUsers.remove(current);
    Person x = current;
    current = setGuest();
    return client.logOut(x);
  }

  @Override public String addRoom(int roomNumber, int numberOfBeds, int size,int price,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony) throws RemoteException
  {
    return client.addRoom(roomNumber, numberOfBeds, size,price, orientation, internet, bathroom, kitchen, balcony);
  }

  @Override public String addReservation(int roomNumber, String username,
      MyDate fromDate, MyDate toDate, boolean CheckedIn) throws RemoteException
  {
    return client.addReservation(roomNumber, username, fromDate, toDate, CheckedIn);
  }

  @Override public Employee getNewEmployee() throws RemoteException
  {
    return client.getNewEmployee();
  }

  @Override public String addEmployee( String firstName,
      String lastName, String position, String phoneNo, String password)
      throws RemoteException
  {
    return client.addEmployee(firstName,lastName,position,phoneNo,password);
  }

  @Override public String addReview(String username, int roomNO,
      MyDate fromDate, MyDate postedDate, String comment) throws RemoteException
  {
    return client.addReview(username, roomNO, fromDate, postedDate, comment);
  }

  @Override public ArrayList<Room> getAllRooms() throws RemoteException
  {
    return client.getAllRooms();
  }

  @Override public ArrayList<Review> getAllReviews() throws RemoteException
  {
    return client.getAllReviews();
  }

  @Override public Person setGuest()
  {
    current = new Guest();
    return current;
  }

  @Override public ArrayList<Reservation> getAllMyReservation(String username)
      throws RemoteException
  {
    return client.getAllMyReservation(username);
  }

  @Override public ArrayList<Room> getSimpleFilteredRoom(String room)
      throws RemoteException
  {
    return client.getSimpleFilteredRoom(room);
  }

  @Override public ArrayList<Room> getFilteredRoom(MyDate from,MyDate to,String... attr) throws RemoteException
  {
    return client.getFilteredRooms(from,to,attr);
  }

  @Override public ArrayList<Customer> getFilteredCustomers(String... attr)
      throws RemoteException
  {
    return client.getFilteredCustomer(attr);
  }

  @Override public void setPreviousView(boolean b)
  {
    previousView = b;
  }

  @Override public boolean getPreviousView()
  {
    return previousView;
  }

  @Override public ArrayList<Customer> getAllCustomers() throws RemoteException
  {
    return client.getAllCustomers();
  }

  @Override public ArrayList<Customer> filterCustomer(String customer)
      throws RemoteException
  {
    return client.filterCustomer(customer);
  }

  @Override public ArrayList<Employee> getAllEmployees() throws RemoteException
  {
    return client.getAllEmployees();
  }

  @Override public ArrayList<Employee> filterEmployee(String employee) throws RemoteException
  {
    return client.filterEmployee(employee);
  }

  @Override public ArrayList<Employee> getFilteredEmployee(String... attr)
      throws RemoteException
  {
    return client.getFilteredEmployee(attr);
  }

  @Override public ArrayList<Reservation> getAllReservations()
      throws RemoteException
  {
    return client.getAllReservations();
  }

  @Override public ArrayList<Reservation> getFilteredReservation(String state,
      MyDate fromDate, MyDate toDate) throws RemoteException
  {
    return client.getFilteredReservation(state, fromDate, toDate);
  }

  @Override public String updateRoom(int roomNumber, int numberOfBeds, int size,
      int price, String orientation, boolean internet, boolean bathroom,
      boolean kitchen, boolean balcony) throws RemoteException
  {
    return client.updateRoom(roomNumber, numberOfBeds, size,price, orientation, internet, bathroom, kitchen, balcony);
  }

  @Override public String updateCustomer(String username, String firstName,
      String lastName, String phoneNumber, String payment)
      throws RemoteException
  {
    return client.updateCustomer(username,firstName,lastName,phoneNumber,payment);
  }

  @Override public String updateEmployee(String username, String firstName,
      String lastName, String position, String phoneNo)
      throws RemoteException
  {
    return client.updateEmployee(username, firstName, lastName,position,phoneNo);
  }

  @Override public String updateReservation(int roomNumber, String username,
      MyDate fromDate, MyDate toDate,int oldRoomNo,String oldUsername,MyDate oldFromDate,MyDate oldToDate) throws RemoteException
  {
    return client.updateReservation(roomNumber, username, fromDate, toDate,oldRoomNo,oldUsername,oldFromDate,oldToDate);
  }

  @Override public String deleteRoom(int roomNumber) throws RemoteException
  {
    return client.deleteRoom(roomNumber);
  }

  @Override public String deleteEmployee(String userID) throws RemoteException
  {
    return client.deleteEmployee(userID);
  }

  @Override public String deleteReservation(int roomNo, String username,
      MyDate fromDate) throws RemoteException
  {
    return client.deleteReservation(roomNo, username, fromDate);
  }

  @Override public void saveSelectedRoom(Room room)
  {
    selectedRoom=room;
  }

  @Override public Room getSelectedRoom()
  {
    return selectedRoom;
  }

  @Override public void saveSelectedReservation(Reservation reservation)
  {
    selectedReservation=reservation;
  }

  @Override public Reservation getSelectedReservation()
  {
    return selectedReservation;
  }

  @Override public void saveSelectedCustomer(Customer customer)
  {
    selectedCustomer=customer;
  }

  @Override public String deleteSelectedCustomer(String username)
      throws RemoteException
  {
    return client.deleteSelectedCustomer(username);
  }

  @Override public Customer getSelectedCustomer()
  {
    return selectedCustomer;
  }

  @Override public void saveSelectedEmployee(Employee employee)
  {
    this.selectedEmployee = employee;
  }

  @Override public Employee getSelectedEmployee()
  {
    return selectedEmployee;
  }
  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public String checkIn(int roomNumber, String username,
      MyDate fromDate) throws RemoteException
  {
    return client.checkIn(roomNumber,username,fromDate);
  }

  @Override public String checkOut(int roomNumber, String username,
      MyDate fromDate) throws RemoteException
  {
    return client.checkOut(roomNumber,username,fromDate);
  }

  @Override public Person getCurrentCustomer()
  {
    return current;
  }

  @Override public String addCustomer(String username, String password,
      String firstName, String lastName, String phoneNo, String paymentInfo)
      throws RemoteException
  {
    return client.addCustomer(username,password,firstName,lastName,phoneNo,paymentInfo);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    support.firePropertyChange("update",null,evt.getNewValue());
  }
}
