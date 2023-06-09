package Client.View.Controllers.Employee;

import Client.Utility.Alerts;
import Client.View.Scenes.SceneNames;
import Client.View.ViewHandler;
import Client.ViewModel.Employee.EmployeeHomeViewModel;
import Server.Model.Hotel.Users.Customer;
import Server.Model.Hotel.Users.Employee;
import Server.Model.Hotel.Reservation;
import Server.Model.Hotel.Room;
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

  @FXML DatePicker toDateNewReservation;
  @FXML DatePicker fromDateNewReservation;
  @FXML CheckBox reserveBalcony;
  @FXML CheckBox reserveKitchen;
  @FXML CheckBox reserveInternet;
  @FXML CheckBox reserveBathroom;
  @FXML ComboBox<Integer> reservePricePerNight;
  @FXML TextField reserveNrOfBeds;
  @FXML TextField reserveRoomNr;
  @FXML TextField hiddenFieldRoomNo;

  private Region root;
  private ViewHandler viewHandler;
  private EmployeeHomeViewModel viewModel;

  private boolean toggleRoomfilter = false, toggleEmployeefilter = false, toggleCustomerFilter = false, toggleNewReservationFilter=false;

  public void init(ViewHandler viewHandler, EmployeeHomeViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.viewModel.bindRoomList(roomListView.itemsProperty());
    this.viewModel.bindNewReservations(roomListViewNewReservation.itemsProperty());
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
    viewModel.bindFilteringEmployee(filteringEmployee.textProperty());

    viewModel.bindAllBookings(allFilter.selectedProperty());
    viewModel.bindBookingFilter(bookingFilter.selectedProperty());
    viewModel.bindReservationFilter(reservationFilter.selectedProperty());
    viewModel.bindFromDateReservation(fromDateReservation.valueProperty());
    viewModel.bindToDateReservation(toDateReservation.valueProperty());
    viewModel.bindReserveBalcony(reserveBalcony.selectedProperty());
    viewModel.bindReserveBathroom(reserveBathroom.selectedProperty());
    viewModel.bindReserveInternet(reserveInternet.selectedProperty());
    viewModel.bindReserveKitchen(reserveKitchen.selectedProperty());
    viewModel.bindReservePrice(reservePricePerNight.valueProperty());
    viewModel.bindReserveNoBeds(reserveNrOfBeds.textProperty());
    viewModel.bindReserveRoomNo(reserveRoomNr.textProperty());
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
    reservePricePerNight.setItems(FXCollections.observableList(prices));
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

  @FXML void Home()
  {
    viewModel.logOut();
    viewHandler.openView(SceneNames.Home);
  }

  @FXML void tableClickRoom(MouseEvent event)
  {
    if (roomListView.getSelectionModel().getSelectedItem()!=null){
      viewModel.saveRoom(roomListView.getSelectionModel().getSelectedItem());
      if (event.getClickCount() == 2)
        viewHandler.openView(SceneNames.EditRoom);
    }
  }

  @FXML void tableClickCustomer(MouseEvent event)
  {
    if (customerListView.getSelectionModel().getSelectedItem()!=null){
      viewModel.saveCustomer(customerListView.getSelectionModel().getSelectedItem());
      if (event.getClickCount() == 2)
        viewHandler.openView(SceneNames.EditCustomer);
    }
  }



  @FXML void tableClickBooking(MouseEvent event)
  {
    if (reservationListView.getSelectionModel().getSelectedItem()!=null)
    {
      viewModel.saveReservation(reservationListView.getSelectionModel().getSelectedItem());
      if (event.getClickCount() == 2)
      {
        viewHandler.openView(SceneNames.EditReservation);
      }
    }
  }

  @FXML void filterRoom()
  {
    viewModel.filterRoom();
  }

  @FXML void onFilter()
  {
    viewModel.filterFilterCustomer();
  }

  @FXML void filterEmployee()
  {
    viewModel.filterEmployee();
  }

  @FXML void checkIn()
  {
    Alerts conformation = new Alerts(Alert.AlertType.CONFIRMATION,"Do you really want to check in this reservation?",null);
    if (conformation.getResult().equals(ButtonType.OK))
    {
      Alerts x = viewModel.checkIn();
      x.showAndWait();
    }
  }

  @FXML void checkOut()
  {
    Alerts conformation = new Alerts(Alert.AlertType.CONFIRMATION,"Do you really want to check out this reservation?",null);
    if (conformation.getResult().equals(ButtonType.OK))
    {
      Alerts x = viewModel.checkOut();
      x.showAndWait();
    }
  }

  @FXML void deleteReservation()
  {
    Alerts confirmation = new Alerts(Alert.AlertType.CONFIRMATION,"Do you really want cancel this reservation?",null);
    if (confirmation.getResult().equals(ButtonType.OK))
    {
      Alerts x = viewModel.deleteReservation();
      x.showAndWait();
    }
  }

  @FXML void editReservation()
  {
    Alerts x = viewModel.saveReservation(reservationListView.getSelectionModel().getSelectedItem());
    if (x.getAlertType().equals(Alert.AlertType.NONE))
    {
      x.showAndWait();
      viewHandler.openView(SceneNames.EditReservation);
    }
    else
    {
      x.showAndWait();
    }
  }

  @FXML void tableClickNewReservation()
  {
    if (roomListViewNewReservation.getSelectionModel().getSelectedItem()!=null){
      viewModel.saveRoom(roomListViewNewReservation.getSelectionModel().getSelectedItem());
      viewModel.fillHiddenField();
    }

  }

  @FXML void createNewReservation()
  {
    viewModel.addReservation().showAndWait();
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
    if (toggleCustomerFilter)
    {
      toggleCustomerFilter = false;
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
      toggleCustomerFilter = true;
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

  @FXML void simpleFilterEmployee()
  {
    viewModel.simpleFilterEmployee();
  }

  @FXML void clearDates(){
    fromDateNewReservation.setValue(null);
    toDateNewReservation.setValue(null);
    fromDateReservation.setValue(null);
    toDateReservation.setValue(null);
  }

  @FXML void filterCustomer()
  {
    String x = filteringCustomer.getText();
    viewModel.filterCustomer(x);
  }

  @FXML void simpleFilterRoom()
  {
    viewModel.simpleRoomFilter();
  }
  @FXML void filterReservation()
  {
    viewModel.filterReservation();
  }

  @FXML void filterNewReservation()
  {
    viewModel.filterNewReservation();
  }
}
