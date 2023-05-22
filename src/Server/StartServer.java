package Server;

import Server.Mediator.Server;
import Server.Model.Model;
import Server.Model.ModelManager;
import Server.View.ConsoleView;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class StartServer
{
  public static void main(String[] args)
      throws RemoteException, AlreadyBoundException
  {
    Model model=new ModelManager();
    ConsoleView consoleView=new ConsoleView(model);

    Registry registry= LocateRegistry.createRegistry(1377);
    Server server=new Server(model);
    registry.bind("HotelServer",server);
    System.out.println("server started");
  }
}
