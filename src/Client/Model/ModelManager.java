package Client.Model;

import Client.Mediator.Client;
import Server.Model.Room;
import Shared.SharedInterface;
import javafx.scene.layout.Region;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class ModelManager implements Model
{
  private Client client;
  public ModelManager() throws IOException, NotBoundException
  {
    Registry registry= LocateRegistry.getRegistry(1337);
    SharedInterface sharedInterface=(SharedInterface) registry.lookup("HotelServer");
    client=new Client(sharedInterface);
  }

  @Override public void addRoom(int roomNumber, int numberOfBeds, int size,int price,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony) throws RemoteException
  {
    client.addRoom(roomNumber, numberOfBeds, size,price, orientation, internet, bathroom, kitchen, balcony);
  }

  @Override public ArrayList<Room> getAllRooms() throws RemoteException
  {
    return client.getAllRooms();
  }
}
