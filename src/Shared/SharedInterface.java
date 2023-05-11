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

  ArrayList<Customer> getAllCustomers() throws RemoteException;
  ArrayList<Employee> getAllEmployees() throws RemoteException;

  String updateRoom(int roomNumber, int numberOfBeds, int size,int price,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony) throws RemoteException;

  String deleteRoom(int roomNumber) throws RemoteException;


}