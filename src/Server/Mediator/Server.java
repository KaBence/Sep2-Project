package Server.Mediator;

import Server.Model.Model;
import Server.Model.Room;
import Shared.SharedInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements SharedInterface
{
  private Model model;
  public Server(Model model) throws RemoteException
  {
    this.model=model;
  }

  @Override public void addRoom(int roomNumber, int numberOfBeds, int size,int price,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony) throws RemoteException
  {
    model.addRoom(roomNumber, numberOfBeds, size, price,orientation, internet, bathroom, kitchen, balcony);
  }
}
