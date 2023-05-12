package Client.Mediator;

import Server.Model.Customer;
import Server.Model.Employee;
import Server.Model.Room;
import Shared.SharedInterface;
import dk.via.remote.observer.RemotePropertyChangeEvent;
import dk.via.remote.observer.RemotePropertyChangeListener;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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

  public Room addRoom(int roomNumber, int numberOfBeds, int size, int price,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony) throws RemoteException
  {
    return sharedInterface.addRoom(roomNumber, numberOfBeds, size, price, orientation, internet, bathroom, kitchen, balcony);
  }

  public ArrayList<Room> getAllRooms() throws RemoteException
  {
    return sharedInterface.getAllRooms();
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

  public String updateRoom(int roomNumber, int numberOfBeds, int size,int price,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony) throws RemoteException
  {
    return sharedInterface.updateRoom(roomNumber, numberOfBeds, size, price, orientation, internet, bathroom, kitchen, balcony);
  }

  public String updateCustomer(String username,String firstName, String lastName, String phoneNumber, String payment) throws RemoteException{
    return sharedInterface.updateCustomer(username,firstName,lastName,phoneNumber,payment);
  }
  public String updateEmployee( String firstName, String lastName, String position, String phoneNo)
      throws RemoteException
  {
    return sharedInterface.updateEmployee(firstName,lastName,position,phoneNo);
  }

  public String deleteRoom(int roomNumber) throws RemoteException
  {
    return sharedInterface.deleteRoom(roomNumber);
  }

  public String deleteSelectedCustomer(String username) throws RemoteException{
    return sharedInterface.deleteSelectedCustomer(username);
  }

  public String deleteEmployee(String userID) throws RemoteException
  {
    return sharedInterface.deleteEmployee(userID);
  }
  public void addPropertyChangeListener(PropertyChangeListener listener){
    support.addPropertyChangeListener(listener);
  }
}
