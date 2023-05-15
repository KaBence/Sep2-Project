package Server.Mediator;

import Server.Model.Customer;
import Server.Model.Employee;
import Server.Model.Model;
import Server.Model.Room;
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

  @Override public void addPropertyChangeListener(
      RemotePropertyChangeListener listener) throws RemoteException
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public Room addRoom(int roomNumber, int numberOfBeds, int size,
      int price, String orientation, boolean internet, boolean bathroom,
      boolean kitchen, boolean balcony) throws RemoteException
  {
    support.firePropertyChange("add", null, "123");
    return model.addRoom(roomNumber, numberOfBeds, size, price, orientation,
        internet, bathroom, kitchen, balcony);
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





}
