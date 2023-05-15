package Shared;

import Server.Model.Customer;
import Server.Model.Employee;
import Server.Model.Room;
import dk.via.remote.observer.RemotePropertyChangeListener;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface SharedInterface extends Remote
{
  void addPropertyChangeListener(RemotePropertyChangeListener listener) throws RemoteException;
  Room addRoom(int roomNumber, int numberOfBeds, int size, int price,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony) throws RemoteException;

  ArrayList<Room> getAllRooms() throws RemoteException;

  ArrayList<Room> getFilteredRoom(String... attr) throws RemoteException;

  ArrayList<Customer> getAllCustomers() throws RemoteException;
  ArrayList<Customer> filterCustomer(String customer) throws RemoteException;

  ArrayList<Employee> getAllEmployees() throws RemoteException;
  ArrayList<Employee> filterEmployee(String employee) throws RemoteException;

  String updateRoom(int roomNumber, int numberOfBeds, int size,int price,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony) throws RemoteException;
  String updateCustomer(String username, String firstName, String lastName, String phoneNumber, String payment) throws RemoteException;

  String deleteRoom(int roomNumber) throws RemoteException;
  String deleteSelectedCustomer(String username) throws RemoteException;

  String updateEmployee(String username, String firstName, String lastName, String position, String phoneNo) throws RemoteException;

  String deleteEmployee(String userID) throws RemoteException;

}