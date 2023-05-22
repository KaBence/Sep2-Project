package Server.Mediator;

import Server.Model.*;
import Server.Model.Hotel.Users.Customer;
import Server.Model.Hotel.Users.Employee;
import Server.Model.Hotel.Reservation;
import Server.Model.Hotel.Room;
import Server.Model.Hotel.Users.Person;
import Shared.SharedInterface;
import dk.via.remote.observer.RemotePropertyChangeListener;
import dk.via.remote.observer.RemotePropertyChangeSupport;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server extends UnicastRemoteObject implements SharedInterface
{
  private Model model;

  private RemotePropertyChangeSupport support;

  public Server(Model model) throws RemoteException
  {
    this.model = model;
    support = new RemotePropertyChangeSupport();
  }

  @Override public Person logIn(Person user) throws RemoteException
  {
    support.firePropertyChange("logIn",null,"123");
    return model.logIn(user);
  }

  @Override public Person logOut(Person user) throws RemoteException
  {
    support.firePropertyChange("logOut",null,"123");
    return model.logOut(user);
  }

  @Override public void addPropertyChangeListener(
      RemotePropertyChangeListener listener) throws RemoteException
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public String addRoom(int roomNumber, int numberOfBeds, int size,
      int price, String orientation, boolean internet, boolean bathroom,
      boolean kitchen, boolean balcony) throws RemoteException
  {
    support.firePropertyChange("add", null, "123");
    return model.addRoom(roomNumber, numberOfBeds, size, price, orientation,
        internet, bathroom, kitchen, balcony);
  }

  @Override public String  addReservation(int roomNumber, String username,
      MyDate fromDate, MyDate toDate, boolean CheckedIn) throws RemoteException
  {
    support.firePropertyChange("addReservation", null, "123");
    return  model.addReservation(roomNumber,username,fromDate,toDate,CheckedIn);

  }

  @Override public Employee addEmployee(String firstName, String lastName,
      String position, String phoneNo, String password) throws RemoteException
  {
    support.firePropertyChange("addEmployee", null, "123");
    return model.addEmployee(firstName,lastName,position,phoneNo,password);
  }

  @Override public ArrayList<Room> getAllRooms()
  {
    return model.getAllRooms();
  }

  @Override public ArrayList<Room> getSimpleFilteredRoom(String room)
      throws RemoteException
  {
    return model.getSimpleFilteredRooms(room);
  }

  @Override public ArrayList<Room> getFilteredRoom(String... attr)
      throws RemoteException
  {
    return model.getFilteredRooms(attr);
  }

  @Override public ArrayList<Customer> getFilteredCustomer(String... attr)
      throws RemoteException
  {
    return model.getFilteredCustomers(attr);
  }

  @Override public ArrayList<Customer> getAllCustomers() throws RemoteException
  {
    return model.getAllCustomers();
  }
  @Override public ArrayList<Customer> filterCustomer(String customer) throws RemoteException
  {
    return model.filterCustomer(customer);
  }

  @Override public ArrayList<Employee> getAllEmployees() throws RemoteException
  {
    return model.getAllEmployees();
  }

  @Override public ArrayList<Employee> filterEmployee(String employee) throws RemoteException
  {
    return model.filterEmployee(employee);
  }

  @Override public ArrayList<Employee> getFilteredEmployee(String... attr)
      throws RemoteException
  {
    return model.getFilteredEmployee(attr);
  }

  @Override public String updateRoom(int roomNumber, int numberOfBeds, int size,
      int price, String orientation, boolean internet, boolean bathroom,
      boolean kitchen, boolean balcony) throws RemoteException
  {
    support.firePropertyChange("update", null, "123");
    return model.updateRoom(roomNumber, numberOfBeds, size, price, orientation,
        internet, bathroom, kitchen, balcony);
  }

  @Override public String updateCustomer(String username, String firstName,
      String lastName, String phoneNumber, String payment)
      throws RemoteException
  {
    support.firePropertyChange("update",null,"123");
    return  model.updateCustomer(username,firstName,lastName,phoneNumber,payment);
  }

  @Override public String updateEmployee(String username, String firstName, String lastName,
      String position, String phoneNo) throws RemoteException
  {
    support.firePropertyChange("update", null, "123");
    return model.updateEmployee(username, firstName,lastName,position,phoneNo);
  }


  @Override public String deleteRoom(int roomNumber) throws RemoteException
  {
    support.firePropertyChange("delete", null, "123");
    return model.deleteRoom(roomNumber);
  }

  @Override public String deleteSelectedCustomer(String username)
      throws RemoteException
  {
    support.firePropertyChange("delete", null, "123");
    return model.deleteSelectedCustomer(username);
  }

  @Override public String deleteEmployee(String userID) throws RemoteException
  {
    support.firePropertyChange("delete", null,"123");
    return model.deleteEmployee(userID);
  }

  @Override public String deleteReservation(int roomNo, String username,
      MyDate fromDate) throws RemoteException
  {
    support.firePropertyChange("delete",null,"123");
    return model.deleteReservation(roomNo,username,fromDate);
  }

  @Override public ArrayList<Reservation> getAllReservations()
      throws RemoteException
  {
    return model.getAllReservations();
  }

  @Override public ArrayList<Reservation> getFilteredReservation(String state,MyDate fromDate,MyDate toDate)
      throws RemoteException
  {
    return model.getFilteredReservations(state,fromDate,toDate);
  }

  @Override public String updateReservation(int roomNumber, String username,
      MyDate fromDate, MyDate toDate,int oldRoomNo,String oldUsername,MyDate oldFromDate) throws RemoteException
  {
    return model.updateReservation(roomNumber, username, fromDate, toDate,oldRoomNo,oldUsername,oldFromDate);
  }

  @Override public String addCustomer(String username, String password,
      String firstName, String lastName, String phoneNo, String paymentInfo)
      throws RemoteException
  {
    return model.addCustomer(username,password,firstName,lastName,phoneNo,paymentInfo);
  }

  @Override public String checkIn(int roomNumber, String username, MyDate fromDate)
      throws RemoteException
  {
    support.firePropertyChange("update", null, "123");
    System.out.println("server");
    return model.checkIn(roomNumber,username,fromDate);
  }

  @Override public String checkOut(int roomNumber, String username, MyDate fromDate)
      throws RemoteException
  {
    support.firePropertyChange("update", null, "123");
    return model.checkOut(roomNumber,username,fromDate);
  }

}
