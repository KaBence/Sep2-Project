package Client.Model;

import Client.Mediator.Client;
import Server.Model.Customer;
import Server.Model.Employee;
import Server.Model.Room;
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

  private PropertyChangeSupport support;

  public ModelManager(SharedInterface sharedInterface) throws IOException, NotBoundException
  {
    client=new Client(sharedInterface);
    client.addPropertyChangeListener(this);
    support=new PropertyChangeSupport(this);
  }

  public ModelManager() throws IOException, NotBoundException
  {
    Registry registry= LocateRegistry.getRegistry(1337);
    SharedInterface sharedInterface=(SharedInterface) registry.lookup("HotelServer");
    client=new Client(sharedInterface);
    client.addPropertyChangeListener(this);
    support=new PropertyChangeSupport(this);
  }

  @Override public Room addRoom(int roomNumber, int numberOfBeds, int size,int price,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony) throws RemoteException
  {
    return client.addRoom(roomNumber, numberOfBeds, size,price, orientation, internet, bathroom, kitchen, balcony);
  }

  @Override public ArrayList<Room> getAllRooms() throws RemoteException
  {
    return client.getAllRooms();
  }

  @Override public ArrayList<Room> getFilteredRoom(String... attr) throws RemoteException
  {
    return client.getFilteredRooms(attr);
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

  @Override public String deleteRoom(int roomNumber) throws RemoteException
  {
    return client.deleteRoom(roomNumber);
  }

  @Override public String deleteEmployee(String userID) throws RemoteException
  {
    return client.deleteEmployee(userID);
  }

  @Override public void saveSelectedRoom(Room room)
  {
    selectedRoom=room;
  }

  @Override public Room getSelectedRoom()
  {
    return selectedRoom;
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

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    support.firePropertyChange("update",null,evt.getNewValue());
  }
}
