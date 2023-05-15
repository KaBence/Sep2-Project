import Client.Mediator.Client;
import Client.Model.Model;
import Client.Model.ModelManager;
import Server.Mediator.Server;
import Server.Model.Room;
import Shared.SharedInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class ClientModelTest
{
  private Client client;
  private SharedInterface sharedInterface;

  private Model model;

  @BeforeEach
  void setUp() throws NotBoundException, IOException
  {
    sharedInterface=Mockito.mock(Server.class);
    client=new Client(sharedInterface);
    model=new ModelManager(sharedInterface);
  }

  @Test
  void temp(){

  }
}
