package Shared;

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

  void updateRoom(int roomNumber, int numberOfBeds, int size,int price,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony) throws RemoteException;

  void deleteRoom(int roomNumber) throws RemoteException;


}