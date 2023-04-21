package Client.Model;

import Client.Mediator.Client;
import Shared.SharedInterface;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ModelManager implements Model
{
  private Client client;
  public ModelManager() throws IOException, NotBoundException
  {
    Registry registry= LocateRegistry.getRegistry(1337);
    SharedInterface sharedInterface=(SharedInterface) registry.lookup("HotelServer");
    client=new Client(sharedInterface);
  }
}
