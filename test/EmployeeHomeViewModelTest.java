import Client.Mediator.Client;
import Client.Model.Model;
import Client.Model.ModelManager;
import Client.Utility.Alerts;
import Client.ViewModel.Employee.EmployeeHomeViewModel;
import Server.Model.Hotel.Reservation;
import Server.Model.Hotel.Users.Employee;
import Server.Model.MyDate;
import Server.Utility.DataBase.DatabaseConnection;
import Shared.SharedInterface;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Alert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;


import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;

public class EmployeeHomeViewModelTest
{
  private SharedInterface sharedInterface;
  private Model model;
  private EmployeeHomeViewModel viewModel;

  @BeforeEach
  void setUp() throws IOException, NotBoundException
  {
    sharedInterface= Mockito.mock(SharedInterface.class);
    new Client(sharedInterface);
    model=new ModelManager(sharedInterface);
    viewModel=new EmployeeHomeViewModel(model);
  }

  @Test
  void loggingOutReturnPersonThatWasLoggedInIntoTheSystem() throws RemoteException
  {
    Employee employee = new Employee("testLogOut","Test","LogOut",
        "Mockito","1233456789","qwerty");
    Mockito.when(sharedInterface.logIn(employee)).thenReturn(employee);
    model.logIn(employee);
    Mockito.when(sharedInterface.logOut(employee)).thenReturn(employee);
    assertEquals(employee,viewModel.logOut());
  }

  @Test
  void savingSelectedReservationReturnsNone()
  {
    MyDate fromDate = new MyDate(1,1,2023);
    MyDate toDate = new MyDate(1,2,2023);
    Reservation test = new Reservation(10,"test",fromDate,toDate,false);
    Alerts x = new Alerts(Alert.AlertType.NONE,"","");
    assertEquals(x,viewModel.saveReservation(test));
  }

  @Test
  void addReservationWithoutFillingMandatoryFieldsReturnsError() throws RemoteException
  {
    MyDate fromDate = new MyDate(1,1,2023);
    MyDate toDate = new MyDate(1,2,2023);
    Reservation test = new Reservation(10,"test",fromDate,toDate,false);
    viewModel.saveReservation(test);
    Mockito.when(sharedInterface.addReservation(10,"test",fromDate,toDate,false)).thenReturn(DatabaseConnection.SUCCESS);
    Alerts x = new Alerts(Alert.AlertType.INFORMATION,"Success","Successfully added a new reservation");
    assertNotEquals(x,viewModel.addReservation());
  }

  @Test
  void addReservationInPastReturnsError() throws RemoteException
  {
    MyDate fromDate = new MyDate(1,1,2023);
    SimpleObjectProperty<LocalDate> fromDateNewReservation = new SimpleObjectProperty<>();
    viewModel.bindFromDateNewReservation(fromDateNewReservation);
    fromDateNewReservation.setValue(fromDate.convertToLocalDate());

    MyDate toDate = new MyDate(1,2,2023);
    SimpleObjectProperty<LocalDate> toDateNewReservation = new SimpleObjectProperty<>();
    viewModel.bindToDateNewReservation(toDateNewReservation);
    toDateNewReservation.setValue(toDate.convertToLocalDate());

    Reservation test = new Reservation(10,"test",fromDate,toDate,false);
    viewModel.saveReservation(test);

    Mockito.when(sharedInterface.addReservation(10,"test",fromDate,toDate,false)).thenReturn(DatabaseConnection.SUCCESS);
    Alerts x = new Alerts(Alert.AlertType.ERROR,"Date error","It is not possible to create reservations in the past.");
    assertEquals(x,viewModel.addReservation());
  }

  @Test
  void addReservationReturnsSuccess() throws RemoteException
  {
    String room = "10";
    SimpleStringProperty reserveThisRoom = new SimpleStringProperty();
    viewModel.bindHiddenText(reserveThisRoom);
    reserveThisRoom.setValue(room);

    Employee person = new Employee("test","test","test","test","test","test");
    Mockito.when(sharedInterface.logIn(person)).thenReturn(person);
    model.logIn(person);

    MyDate fromDate = new MyDate(31,12,2023);
    SimpleObjectProperty<LocalDate> fromDateNewReservation = new SimpleObjectProperty<>();
    viewModel.bindFromDateNewReservation(fromDateNewReservation);
    fromDateNewReservation.setValue(fromDate.convertToLocalDate());

    MyDate toDate = new MyDate(15,1,2024);
    SimpleObjectProperty<LocalDate> toDateNewReservation = new SimpleObjectProperty<>();
    viewModel.bindToDateNewReservation(toDateNewReservation);
    toDateNewReservation.setValue(toDate.convertToLocalDate());

    Reservation test = new Reservation(10,"test",fromDate,toDate,false);
    viewModel.saveReservation(test);

    Mockito.when(sharedInterface.addReservation(10,"test",fromDate,toDate,false)).thenReturn(DatabaseConnection.SUCCESS);
    Alerts x = new Alerts(Alert.AlertType.INFORMATION,"Success","Successfully added a new reservation");
    assertEquals(x,viewModel.addReservation());
  }

  @Test
  void deleteReservationReturnsSuccess() throws RemoteException
  {
    MyDate test=new MyDate(2,5,2023);
    Mockito.when(sharedInterface.deleteReservation(10,"John",test)).thenReturn(DatabaseConnection.SUCCESS);
    viewModel.saveReservation(new Reservation(10,"John",test,new MyDate(12,5,2023),false));
    Alerts x = new Alerts(Alert.AlertType.INFORMATION,"Success","Reservation has been canceled");
    assertEquals(x,viewModel.deleteReservation());
  }

  @Test
  void deleteReservationReturnsErrorWhenUserDoesntSelectAnyReservation() throws RemoteException
  {
    MyDate fromDate = new MyDate(1,1,2023);
    MyDate toDate = new MyDate(1,2,2023);
    Mockito.when(sharedInterface.addReservation(10,"test",fromDate,toDate,false)).thenReturn(DatabaseConnection.SUCCESS);
    //Alert from method save reservation
    Alerts x = new Alerts(Alert.AlertType.ERROR,"Error","Select a reservation first");
    assertEquals(x,viewModel.deleteReservation());
  }

  @Test
  void checkingInReservationWhenCheckInDateIsNotTodayReturnsWarning() throws RemoteException
  {
    MyDate fromDate = new MyDate(31,12,2023);
    MyDate toDate = new MyDate(15,1,2024);
    Reservation reservation = new Reservation(10,"test",fromDate,toDate,false);
    model.saveSelectedReservation(reservation);
    Mockito.when(sharedInterface.checkIn(10,"test",fromDate)).thenReturn(DatabaseConnection.SUCCESS);
    Alerts x = new Alerts(Alert.AlertType.WARNING,"Check in error","You can not check in because your reservation is valid from "+reservation.getFromDate().toString());
    assertEquals(x,viewModel.checkIn());
  }

  @Test
  void checkingInReservationWhenCheckInDateIsTodayReturnsSuccess() throws RemoteException
  {
    MyDate fromDate = MyDate.today();
    MyDate toDate = new MyDate(15,1,2024);
    Reservation reservation = new Reservation(10,"test",fromDate,toDate,false);
    model.saveSelectedReservation(reservation);
    Mockito.when(sharedInterface.checkIn(10,"test",fromDate)).thenReturn(DatabaseConnection.SUCCESS);
    Alerts x = new Alerts(Alert.AlertType.INFORMATION,"Success","Check in was successful");
    assertEquals(x,viewModel.checkIn());
  }

  @Test
  void checkingInReservationThatIsAlreadyCheckedIn() throws RemoteException
  {
    MyDate fromDate = MyDate.today();
    MyDate toDate = new MyDate(15,1,2024);
    Reservation reservation = new Reservation(10,"test",fromDate,toDate,true);
    model.saveSelectedReservation(reservation);
    Mockito.when(sharedInterface.checkIn(10,"test",fromDate)).thenReturn(DatabaseConnection.SUCCESS);
    Alerts x = new Alerts(Alert.AlertType.ERROR,"Error","The reservation is already checked in");
    assertEquals(x,viewModel.checkIn());
  }

  @Test
  void checkingInReservationReturnsErrorWhenUserDoesntSelectAnyReservation() throws RemoteException
  {
    MyDate fromDate = MyDate.today();
    Mockito.when(sharedInterface.checkIn(10,"test",fromDate)).thenReturn(DatabaseConnection.SUCCESS);
    Alerts x = new Alerts(Alert.AlertType.ERROR,"Error","Select a reservation first");
    assertEquals(x,viewModel.checkIn());
  }

  @Test
  void checkingOutReservationReturnsSuccess() throws RemoteException
  {
    MyDate fromDate = new MyDate(1,1,2023);
    MyDate toDate = MyDate.today();
    Reservation reservation = new Reservation(10,"test",fromDate,toDate,true);
    model.saveSelectedReservation(reservation);
    Mockito.when(sharedInterface.checkOut(10,"test",fromDate)).thenReturn(DatabaseConnection.SUCCESS);
    Alerts x = new Alerts(Alert.AlertType.INFORMATION,"Success","Check Out was successful");
    assertEquals(x,viewModel.checkOut());
  }

  @Test
  void checkingOutReservationThatHasNeverCheckedIn() throws RemoteException
  {
    MyDate fromDate = MyDate.today();
    MyDate toDate = new MyDate(15,1,2024);
    Reservation reservation = new Reservation(10,"test",fromDate,toDate,false);
    model.saveSelectedReservation(reservation);
    Mockito.when(sharedInterface.checkOut(10,"test",fromDate)).thenReturn(DatabaseConnection.SUCCESS);
    Alerts x = new Alerts(Alert.AlertType.ERROR,"Error","The customer has never checked in");
    assertEquals(x,viewModel.checkOut());
  }

  @Test
  void checkingOutReservationReturnsErrorWhenUserDoesntSelectAnyReservation() throws RemoteException
  {
    MyDate fromDate = MyDate.today();
    Mockito.when(sharedInterface.checkOut(10,"test",fromDate)).thenReturn(DatabaseConnection.SUCCESS);
    Alerts x = new Alerts(Alert.AlertType.ERROR,"Error","Select a reservation first");
    assertEquals(x,viewModel.checkOut());
  }
}
