package Client.Model;

import Server.Model.Room;

import java.rmi.RemoteException;

public interface Model
{
  void addRoom(int roomNumber, int numberOfBeds, int size,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony) throws RemoteException;
}
