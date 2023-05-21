package Client.View.Controllers;

import Client.View.SceneNames;
import Client.View.ViewHandler;
import Client.ViewModel.EmployeeHomeViewModel;
import Server.Model.Hotel.Users.Customer;
import Server.Model.Hotel.Users.Employee;
import Server.Model.Hotel.Reservation;
import Server.Model.Hotel.Room;
import Server.Utility.DataBase.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class EmployeeHomeController
{
  @FXML Button checkIn;
  @FXML Button checkOut;
  @FXML Tab room;
  @FXML Tab employeeInfo;
  @FXML Tab customerInfo;
  @FXML Tab reservation;
  @FXML TabPane tabPane;

  @FXML ListView<Employee> employeeListView;
  @FXML ListView<Customer> customerListView;
  @FXML ListView<Room> roomListView;
  @FXML ListView<Room> roomListViewNewReservation;

  @FXML ListView<Reservation> reservationListView;

  @FXML RadioButton allFilter, reservationFilter, bookingFilter;
  @FXML DatePicker fromDateReservation, toDateReservation;

  @FXML CheckBox bathroomFilter, kitchenFilter, balconyFilter, internetFilter;

  @FXML ComboBox<Integer> priceFilter;
  @FXML TextField roomNoFilter, bedsFilter;

  @FXML TextField filteringEmployee;

  @FXML TextField employeeUsernameFilter, employeeFirstNameFilter, employeeLastNameFilter, employeePositionFilter, employeePhoneNumberFilter;
  @FXML TextField filteringCustomer;
  @FXML TextField username, firstName, lastName, phoneNo, paymentInfo;

  @FXML TextField filteringRoom;

  @FXML ToggleButton toggleRoomButton, toggleEmployeeButton, toggleCustomerButton;

  // employee new reservations
  @FXML TextField reserveInfo;

  @FXML DatePicker toDateNewReservation;
  @FXML DatePicker fromDateNewReservation;
  @FXML CheckBox reserveBalcony;
  @FXML CheckBox reserveKitchen;
  @FXML CheckBox reserveInternet;
  @FXML CheckBox reserveBathroom;
  @FXML ComboBox reservePricePerNight;
  @FXML TextField reserveNrOfBeds;
  @FXML TextField reserveRoomNr;
  @FXML TextField hiddenFieldRoomNo;

  private Region root;
  private ViewHandler viewHandler;
  private EmployeeHomeViewModel viewModel;

  private boolean toggleRoomfilter = false, toggleEmployeefilter = false, toggleCustomerfilter = false;

  public void init(ViewHandler viewHandler, EmployeeHomeViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.viewModel.bindRoomList(roomListView.itemsProperty());
    this.viewModel.bindRoomList(roomListViewNewReservation.itemsProperty());
    this.viewModel.bindCustomerList(customerListView.itemsProperty());
    this.viewModel.bindEmployeeList(employeeListView.itemsProperty());
    this.viewModel.bindReservationList(reservationListView.itemsProperty());
    this.viewModel.bindFilteringRoom(filteringRoom.textProperty());
    this.root = root;
    viewModel.bindHiddenText(hiddenFieldRoomNo.textProperty());
    viewModel.bindToDateNewReservation(toDateNewReservation.valueProperty());
    viewModel.bindFromDateNewReservation(fromDateNewReservation.valueProperty());


    viewModel.bindInternet(internetFilter.selectedProperty());
    viewModel.bindRoomNo(roomNoFilter.textProperty());
    viewModel.bindBeds(bedsFilter.textProperty());
    viewModel.bindBalcony(balconyFilter.selectedProperty());
    viewModel.bindBathroom(bathroomFilter.selectedProperty());
    viewModel.bindKitchen(kitchenFilter.selectedProperty());
    viewModel.bindPrice(priceFilter.valueProperty());

    viewModel.bindUsername(username.textProperty());
    viewModel.bindFirstName(firstName.textProperty());
    viewModel.bindLastName(lastName.textProperty());
    viewModel.bindPhoneNo(phoneNo.textProperty());
    viewModel.bindPaymentInfo(paymentInfo.textProperty());

    viewModel.bindEmployeeUsername(employeeUsernameFilter.textProperty());
    viewModel.bindEmployeeFirstName(employeeFirstNameFilter.textProperty());
    viewModel.bindEmployeeLastName(employeeLastNameFilter.textProperty());
    viewModel.bindEmployeePosition(employeePositionFilter.textProperty());
    viewModel.bindEmployeePhoneNo(employeePhoneNumberFilter.textProperty());

    viewModel.bindAllBookings(allFilter.selectedProperty());
    viewModel.bindBookingFilter(bookingFilter.selectedProperty());
    viewModel.bindReservationFilter(reservationFilter.selectedProperty());
    viewModel.bindFromDateReservation(fromDateReservation.valueProperty());
    viewModel.bindToDateReservation(toDateReservation.valueProperty());
    viewModel.bindReserveInfo(reserveInfo.textProperty());
  }

  public void initialize()
  {
    ArrayList<Integer> prices = new ArrayList<>();
    prices.add(0);
    prices.add(200);
    prices.add(270);
    prices.add(300);
    prices.add(500);
    priceFilter.setItems(FXCollections.observableList(prices));
  }

  public Region getRoot()
  {
    root.setUserData("Employee Home Page");
    return root;
  }

  public void reset()
  {
    ToggleRoom();
    ToggleCustomer();
    ToggleEmployee();
  }

  @FXML void addRoom()
  {
    viewHandler.openView(SceneNames.AddRoom);
  }

  @FXML void Home() throws RemoteException
  {
    viewModel.logOut();
    viewHandler.openView(SceneNames.Home);
  }

  @FXML void tableClickRoom(MouseEvent event)
  {
    viewModel.saveRoom(roomListView.getSelectionModel().getSelectedItem());
    if (event.getClickCount() == 2)
      viewHandler.openView(SceneNames.EditRoom);
  }

  @FXML void tableClickCustomer(MouseEvent event)
  {
    viewModel.saveCustomer(
        customerListView.getSelectionModel().getSelectedItem());
    if (event.getClickCount() == 2)
      viewHandler.openView(SceneNames.EditCustomer);
  }

  @FXML void tableClickEmployee(MouseEvent event)
  {
    viewModel.saveEmployee(
        employeeListView.getSelectionModel().getSelectedItem());
    if (event.getClickCount() == 2)
    {
      viewHandler.openView(SceneNames.EditEmployee);
    }
  }

  @FXML void tableClickBooking(MouseEvent event)
  {
    viewModel.saveReservation(
        reservationListView.getSelectionModel().getSelectedItem());
    if (event.getClickCount() == 2)
      viewHandler.openView(SceneNames.EditReservation);
  }

  @FXML void filterRoom() throws RemoteException
  {
    viewModel.filterRoom();
  }

  @FXML void onFilter() throws RemoteException
  {
    viewModel.filterFilterCustomer();
  }

  @FXML void filterEmployee() throws RemoteException
  {
    viewModel.filterEmployee();
  }

  @FXML void checkIn() throws RemoteException
  {
    Alert conf=new Alert(Alert.AlertType.CONFIRMATION,"Do you really want to check in this reservation?",ButtonType.YES,ButtonType.NO);
    conf.setTitle("Confirmation");
    conf.setHeaderText(null);
    conf.showAndWait();
    String x="";
    if (conf.getResult().equals(ButtonType.YES)){
      x = viewModel.checkIn();
    }
    else
      return;
    if (x.equals(DatabaseConnection.SUCCESS))
    {
      Alert alert = new Alert(Alert.AlertType.INFORMATION, "Check In successful",
          ButtonType.OK);
      alert.setHeaderText(null);
      alert.setTitle("Success");
      alert.showAndWait();
      viewHandler.openView(SceneNames.EmployeeHomeReservations);
    }
    else if (x.equals(DatabaseConnection.MANDATORY))
    {
      Alert error = new Alert(Alert.AlertType.ERROR);
      error.setHeaderText("Error");
      error.setHeaderText("Select a reservation");
      error.showAndWait();
      viewHandler.openView(SceneNames.EmployeeHomeReservations);
    }
    else
    {
      Alert error = new Alert(Alert.AlertType.ERROR);
      error.setHeaderText("Error");
      error.setHeaderText("The customer is already checked in");
      error.showAndWait();
      viewHandler.openView(SceneNames.EmployeeHomeReservations);
    }
  }

  @FXML void checkOut() throws RemoteException
  {
    Alert conf=new Alert(Alert.AlertType.CONFIRMATION,"Do you really want to check in this reservation?",ButtonType.YES,ButtonType.NO);
    conf.setTitle("Confirmation");
    conf.setHeaderText(null);
    conf.showAndWait();
    String x="";
    if (conf.getResult().equals(ButtonType.YES)){
       x = viewModel.checkOut();
    }
    else
      return;
    if (x.equals(DatabaseConnection.SUCCESS))
    {
      Alert alert = new Alert(Alert.AlertType.INFORMATION, "Check Out successful",
          ButtonType.OK);
      alert.setHeaderText(null);
      alert.setTitle("Success");
      alert.showAndWait();
      viewHandler.openView(SceneNames.EmployeeHomeReservations);
    }
    else if (x.equals(DatabaseConnection.MANDATORY))
    {
      Alert error = new Alert(Alert.AlertType.ERROR);
      error.setHeaderText("Error");
      error.setHeaderText("Select a reservation");
      error.showAndWait();
      viewHandler.openView(SceneNames.EmployeeHomeReservations);
    }
    else
    {
      Alert error = new Alert(Alert.AlertType.ERROR);
      error.setHeaderText("Error");
      error.setHeaderText("The customer has never checked in");
      error.showAndWait();
      viewHandler.openView(SceneNames.EmployeeHomeReservations);
    }
  }

  @FXML void deleteReservation() throws RemoteException
  {
    Reservation x = reservationListView.getSelectionModel().getSelectedItem();
    if (x == null)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "Select a reservation first", ButtonType.OK);
      alert.setHeaderText(null);
      alert.setTitle("Error");
      alert.showAndWait();
    }
    else
    {
      if (viewModel.deleteReservation(x.getRoomNumber(), x.getUsername(),
          x.getFromDate()).equals(DatabaseConnection.SUCCESS))
      {
        Alert good = new Alert(Alert.AlertType.INFORMATION);
        good.setHeaderText("The reservation has been canceled.");
        good.showAndWait();
      }
      else
      {
        Alert bad = new Alert(Alert.AlertType.ERROR);
        bad.setHeaderText("You cannot cancel this reservation");
        bad.showAndWait();
      }
    }
  }

  @FXML void editReservation()
  {
    if (reservationListView.getSelectionModel().getSelectedItem() == null)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "Select a reservation first", ButtonType.OK);
      alert.setHeaderText(null);
      alert.setTitle("Error");
      alert.showAndWait();
      return;
    }

    viewModel.saveReservation(
        reservationListView.getSelectionModel().getSelectedItem());
    viewHandler.openView(SceneNames.EditReservation);
  }

  @FXML void tableClickNewResevation() throws RemoteException
  {
    viewModel.saveRoom(
        roomListViewNewReservation.getSelectionModel().getSelectedItem());
    viewModel.fillHiddenField();
  }

  @FXML void createNewReservation() throws RemoteException
  {
    boolean flag = true;
    if (viewModel.addReservation() == null)
    {
      Alert wrong = new Alert(Alert.AlertType.ERROR);
      wrong.setTitle("Invalid data");
      wrong.setHeaderText("something is wrong");
      wrong.showAndWait();
    }
    else
    {
      try
      {
        System.out.println(viewModel.addReservation());
      }
      catch (RuntimeException e)
      {
        Alert empty = new Alert(Alert.AlertType.WARNING);
        empty.setTitle("Invalid data");
        empty.setHeaderText(
            "You need to fill up mandatory fields: \n from date and to date");
        empty.showAndWait();
        flag = false;
      }
      if (flag)
      {
        Alert success = new Alert(Alert.AlertType.INFORMATION);
        success.setTitle("Success");
        success.setHeaderText(
            "The room has been successfully added to the system");
        success.showAndWait();
        viewHandler.openView(SceneNames.EmployeeHomeReservations);
      }
    }
  }

  @FXML void ToggleRoom()
  {
    viewModel.update();
    if (toggleRoomfilter)
    {
      toggleRoomfilter = false;
      toggleRoomButton.setText("Simple");
      bedsFilter.setDisable(true);
      roomNoFilter.setDisable(true);
      bathroomFilter.setDisable(true);
      priceFilter.setDisable(true);
      internetFilter.setDisable(true);
      bathroomFilter.setDisable(true);
      balconyFilter.setDisable(true);
      kitchenFilter.setDisable(true);
      filteringRoom.setDisable(false);
    }
    else
    {
      toggleRoomfilter = true;
      toggleRoomButton.setText("Advanced");
      bedsFilter.setDisable(false);
      roomNoFilter.setDisable(false);
      bathroomFilter.setDisable(false);
      priceFilter.setDisable(false);
      internetFilter.setDisable(false);
      bathroomFilter.setDisable(false);
      balconyFilter.setDisable(false);
      kitchenFilter.setDisable(false);
      filteringRoom.setDisable(true);
    }
  }

  @FXML void ToggleEmployee()
  {
    viewModel.update();
    if (toggleEmployeefilter)
    {
      toggleEmployeefilter = false;
      toggleEmployeeButton.setText("Simple");
      employeeUsernameFilter.setDisable(true);
      employeeFirstNameFilter.setDisable(true);
      employeeLastNameFilter.setDisable(true);
      employeePhoneNumberFilter.setDisable(true);
      employeePositionFilter.setDisable(true);
      filteringEmployee.setDisable(false);
    }
    else
    {
      toggleEmployeefilter = true;
      toggleEmployeeButton.setText("Advanced");
      employeeUsernameFilter.setDisable(false);
      employeeFirstNameFilter.setDisable(false);
      employeeLastNameFilter.setDisable(false);
      employeePhoneNumberFilter.setDisable(false);
      employeePositionFilter.setDisable(false);
      filteringEmployee.setDisable(true);
    }
  }

  @FXML void ToggleCustomer()
  {
    viewModel.update();
    if (toggleCustomerfilter)
    {
      toggleCustomerfilter = false;
      toggleCustomerButton.setText("Simple");
      username.setDisable(true);
      firstName.setDisable(true);
      lastName.setDisable(true);
      phoneNo.setDisable(true);
      paymentInfo.setDisable(true);
      filteringCustomer.setDisable(false);
    }
    else
    {
      toggleCustomerfilter = true;
      toggleCustomerButton.setText("Advanced");
      username.setDisable(false);
      firstName.setDisable(false);
      lastName.setDisable(false);
      phoneNo.setDisable(false);
      paymentInfo.setDisable(false);
      filteringCustomer.setDisable(true);
    }
  }

  public SingleSelectionModel<Tab> selection(int i)
  {
    SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
    selectionModel.select(i);
    return selectionModel;
  }

  @FXML void simpleFilterEmployee() throws RemoteException
  {
    String x = filteringEmployee.getText();
    viewModel.simpleFilterEmployee(x);
  }

  @FXML void simpleFilterNewReservation() throws RemoteException
  {
    viewModel.simpleRoomNewReservationFilter();
  }

  @FXML void filterCustomer() throws RemoteException
  {
    String x = filteringCustomer.getText();
    viewModel.filterCustomer(x);
  }

  @FXML void simpleFilterRoom() throws RemoteException
  {
    viewModel.simpleRoomFilter();
  }
  @FXML void filterReservation() throws RemoteException
  {
    viewModel.filterReservation();
  }

}
