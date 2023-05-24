import Client.Mediator.Client;
import Client.Model.Model;
import Client.Model.ModelManager;
import Client.Utility.Alerts;
import Client.ViewModel.Employee.EmployeeHomeViewModel;
import Server.Mediator.Server;
import Server.Model.Hotel.Reservation;
import Server.Model.MyDate;
import Server.Utility.DataBase.DatabaseConnection;
import Shared.SharedInterface;
import javafx.scene.control.Alert;
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
    sharedInterface= Mockito.mock(SharedInterface.class);
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

    viewModel.saveReservation(new Reservation(10,"John",test,new MyDate(12,5,2023),false));
    //assertTrue(viewModel.deleteReservation());
  }

  @Test
  void deleteReservationReturnsSuccessNEWONE() throws RemoteException
  {
    MyDate test=new MyDate(2,5,2023);
    Mockito.when(sharedInterface.deleteReservation(10,"John",test)).thenReturn(
        DatabaseConnection.SUCCESS);
    viewModel.saveReservation(new Reservation(10,"John",test,new MyDate(12,5,2023),false));
    Alerts x = new Alerts(Alert.AlertType.INFORMATION,"Success","Reservation has been canceled");
    assertEquals(viewModel.deleteReservation().toString(), x.toString());
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
