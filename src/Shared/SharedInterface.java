package Shared;

import Server.Model.Room;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SharedInterface extends Remote
{
  void addRoom(int roomNumber, int numberOfBeds, int size,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony) throws RemoteException;
}
