import Client.Mediator.Client;
import Client.Model.Model;
import Client.Model.ModelManager;
import Client.ViewModel.EmployeeHomeViewModel;
import Server.Mediator.Server;
import Server.Model.MyDate;
import Server.Utility.DataBase.DatabaseConnection;
import Shared.SharedInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;


import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class EmployeeHomeViewModelTest
{

  private Client client;
  private SharedInterface sharedInterface;

  private Model model;
  private EmployeeHomeViewModel viewModel;

  @BeforeEach
  void setUp() throws IOException, NotBoundException
  {
    sharedInterface= Mockito.mock(Server.class);
    client=new Client(sharedInterface);
    model=new ModelManager(sharedInterface);
    viewModel=new EmployeeHomeViewModel(model);
  }

  @Test
  void deleteReservationReturnsSuccess() throws RemoteException
  {
    MyDate test=new MyDate(2,5,2023);
    Mockito.when(sharedInterface.deleteReservation(10,"John",test)).thenReturn(
        DatabaseConnection.SUCCESS);
    assertEquals(viewModel.deleteReservation(10,"John",test),DatabaseConnection.SUCCESS);
  }

  @Test
  void addReservationReturnsTrue() throws RemoteException
  {
    MyDate from=new MyDate(1,5,2023);
    MyDate to=new MyDate(5,5,2023);
    Mockito.when(sharedInterface.addReservation(10,"12345",from,to,false)).thenReturn(DatabaseConnection.SUCCESS);
    viewModel.fillHiddenFieldForTest(10);
    assertTrue(viewModel.addReservation());
  }
}
