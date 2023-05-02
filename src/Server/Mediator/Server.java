package Server.Mediator;

import Server.Model.Model;
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
}
