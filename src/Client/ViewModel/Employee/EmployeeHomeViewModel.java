package Client.ViewModel.Employee;

import Client.Model.Model;
import Client.Model.ModelEmployeeSide;
import Client.Utility.Alerts;
import Server.Model.Hotel.Users.Customer;
import Server.Model.Hotel.Users.Employee;
import Server.Model.Hotel.Reservation;
import Server.Model.Hotel.Room;
import Server.Model.Hotel.Users.Person;
import Server.Model.MyDate;
import Server.Utility.DataBase.DatabaseConnection;
import Server.Utility.IllegalDateException;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public class EmployeeHomeViewModel implements PropertyChangeListener
{
  private ModelEmployeeSide model;
  private SimpleObjectProperty<ObservableList<Room>> rooms;
  private SimpleObjectProperty<ObservableList<Customer>> customers;
  private SimpleObjectProperty<ObservableList<Employee>> employees;
  private SimpleObjectProperty<ObservableList<Room>> newReservations;

  private SimpleObjectProperty<ObservableList<Reservation>> reservations;
  private SimpleBooleanProperty reservationFilter, allBookingFilter, bookingFilter;
  private SimpleObjectProperty<LocalDate> fromDateReservation, toDateReservation;
  private SimpleStringProperty usernameFilter, firstNameFilter, lastNameFilter, phoneNumberFilter, paymentInfoFilter;
  private SimpleStringProperty employeeUsernameFilter, employeeFirstNameFilter, employeeLastNameFilter, employeePhoneNumberFilter, employeePosition;

  private SimpleBooleanProperty bathroomFilter, kitchenFilter, internetFilter, balconyFilter;
  private SimpleObjectProperty<Integer> priceFilter;

  private SimpleStringProperty roomNoFilter,bedsFilter,filteringRoom, hiddenFieldRoomNo;
  private SimpleObjectProperty<LocalDate> fromDateNewReservation, toDateNewReservation;
  private SimpleBooleanProperty reserveBalcony,reserveKitchen,reserveInternet,reserveBathroom;
  private SimpleObjectProperty<Integer> reservePricePerNight;
  private SimpleStringProperty reserveNoBeds,reserveRoomNo;
  private SimpleStringProperty filteringEmployee;

  public EmployeeHomeViewModel(Model model)
  {
    this.model = model;
    model.addPropertyChangeListener(this);
    this.rooms = new SimpleObjectProperty<>();
    customers = new SimpleObjectProperty<>();
    employees = new SimpleObjectProperty<>();
    reservations = new SimpleObjectProperty<>();
    newReservations=new SimpleObjectProperty<>();

    allBookingFilter = new SimpleBooleanProperty();
    reservationFilter = new SimpleBooleanProperty();
    bookingFilter = new SimpleBooleanProperty();
    fromDateReservation = new SimpleObjectProperty<>();
    toDateReservation = new SimpleObjectProperty<>();

    balconyFilter = new SimpleBooleanProperty();
    kitchenFilter = new SimpleBooleanProperty();
    internetFilter = new SimpleBooleanProperty();
    bathroomFilter = new SimpleBooleanProperty();
    priceFilter = new SimpleObjectProperty<>();
    roomNoFilter = new SimpleStringProperty();
    bedsFilter = new SimpleStringProperty();

    usernameFilter = new SimpleStringProperty();
    firstNameFilter = new SimpleStringProperty();
    lastNameFilter = new SimpleStringProperty();
    phoneNumberFilter = new SimpleStringProperty();
    paymentInfoFilter = new SimpleStringProperty();
    usernameFilter.set("");
    firstNameFilter.set("");
    lastNameFilter.set("");
    phoneNumberFilter.set("");
    paymentInfoFilter.set("");

    employeeUsernameFilter = new SimpleStringProperty();
    employeeFirstNameFilter = new SimpleStringProperty();
    employeeLastNameFilter = new SimpleStringProperty();
    employeePosition = new SimpleStringProperty();
    employeePhoneNumberFilter = new SimpleStringProperty();
    filteringEmployee=new SimpleStringProperty();
    employeeUsernameFilter.set("");
    employeeFirstNameFilter.set("");
    employeeLastNameFilter.set("");
    employeePhoneNumberFilter.set("");
    employeePosition.set("");

    filteringRoom = new SimpleStringProperty();

    bedsFilter.set("");
    roomNoFilter.set("");

    hiddenFieldRoomNo=new SimpleStringProperty();
    fromDateNewReservation=new SimpleObjectProperty<>();
    toDateNewReservation=new SimpleObjectProperty<>();
    reserveBalcony=new SimpleBooleanProperty();
    reserveBathroom=new SimpleBooleanProperty();
    reserveInternet=new SimpleBooleanProperty();
    reserveKitchen=new SimpleBooleanProperty();
    reserveNoBeds=new SimpleStringProperty();
    reserveRoomNo=new SimpleStringProperty();
    reservePricePerNight=new SimpleObjectProperty<>();
  }

  public void bindRoomList(ObjectProperty<ObservableList<Room>> property)
  {
    property.bindBidirectional(rooms);
  }

  public void bindNewReservations(ObjectProperty<ObservableList<Room>> property){
    property.bindBidirectional(newReservations);
  }

  public void bindCustomerList(
      ObjectProperty<ObservableList<Customer>> property)
  {
    property.bindBidirectional(customers);
  }

  public void bindEmployeeList(ObjectProperty<ObservableList<Employee>> property){
    property.bindBidirectional(employees);
  }

  public void bindReservationList(ObjectProperty<ObservableList<Reservation>> property){
    property.bindBidirectional(reservations);
  }

  public void bindAllBookings(BooleanProperty property){
    property.bindBidirectional(allBookingFilter);
  }

  public void bindReservationFilter(BooleanProperty property){
    property.bindBidirectional(reservationFilter);
  }

  public void bindBookingFilter(BooleanProperty property){
    property.bindBidirectional(bookingFilter);
  }

  public void bindFromDateReservation(ObjectProperty<LocalDate> property){
    property.bindBidirectional(fromDateReservation);
  }

  public void bindToDateReservation(ObjectProperty<LocalDate> property){
    property.bindBidirectional(toDateReservation);
  }

  public void bindFilteringRoom(StringProperty property){
    property.bindBidirectional(filteringRoom);
  }
public void bindHiddenText(StringProperty property){
    property.bindBidirectional(hiddenFieldRoomNo);
}
  public void bindInternet(BooleanProperty property)
  {
    property.bindBidirectional(internetFilter);
  }

  public void bindKitchen(BooleanProperty property){
    property.bindBidirectional(kitchenFilter);
  }

  public void bindBathroom(BooleanProperty property){
    property.bindBidirectional(bathroomFilter);
  }

  public void bindBalcony(BooleanProperty property){
    property.bindBidirectional(balconyFilter);
  }

  public void bindPrice(ObjectProperty<Integer> property){
    property.bindBidirectional(priceFilter);
  }

  public void bindBeds(StringProperty property){
    property.bindBidirectional(bedsFilter);
  }

  public void bindRoomNo(StringProperty property){
    property.bindBidirectional(roomNoFilter);
  }

  public void bindEmployeeUsername(StringProperty property)
  {
    property.bindBidirectional(employeeUsernameFilter);
  }

  public void bindEmployeeFirstName(StringProperty property)
  {
    property.bindBidirectional(employeeFirstNameFilter);
  }

  public void bindEmployeeLastName(StringProperty property)
  {
    property.bindBidirectional(employeeLastNameFilter);
  }

  public void bindEmployeePhoneNo(StringProperty property)
  {
    property.bindBidirectional(employeePhoneNumberFilter);
  }

  public void bindEmployeePosition(StringProperty property)
  {
    property.bindBidirectional(employeePosition);
  }

  public void bindUsername(StringProperty property)
  {
    property.bindBidirectional(usernameFilter);
  }

  public void bindFirstName(StringProperty property)
  {
    property.bindBidirectional(firstNameFilter);
  }

  public void bindLastName(StringProperty property)
  {
    property.bindBidirectional(lastNameFilter);
  }

  public void bindPhoneNo(StringProperty property)
  {
    property.bindBidirectional(phoneNumberFilter);
  }

  public void bindPaymentInfo(StringProperty property)
  {
    property.bindBidirectional(paymentInfoFilter);
  }

  public void bindFromDateNewReservation(ObjectProperty<LocalDate> property){
    property.bindBidirectional(fromDateNewReservation);
  }

  public void bindToDateNewReservation(ObjectProperty<LocalDate> property){
    property.bindBidirectional(toDateNewReservation);
  }

  public void bindReserveKitchen(BooleanProperty property){
    property.bindBidirectional(reserveKitchen);
  }

  public void bindReserveBalcony(BooleanProperty property){
    property.bindBidirectional(reserveBalcony);
  }

  public void bindReserveInternet(BooleanProperty property){
    property.bindBidirectional(reserveInternet);
  }

  public void bindReserveBathroom(BooleanProperty property){
    property.bindBidirectional(reserveBathroom);
  }

  public void bindReservePrice(ObjectProperty<Integer> property){
    property.bindBidirectional(reservePricePerNight);
  }

  public void bindReserveRoomNo(StringProperty property){
    property.bindBidirectional(reserveRoomNo);
  }

  public void bindReserveNoBeds(StringProperty property){
    property.bindBidirectional(reserveNoBeds);
  }

  public void bindFilteringEmployee(StringProperty property){
    property.bindBidirectional(filteringEmployee);
  }


  public void update()
  {
    balconyFilter.set(false);
    bathroomFilter.set(false);
    kitchenFilter.set(false);
    internetFilter.set(false);
    priceFilter.set(0);
    reservePricePerNight.set(0);
    filteringRoom.set("");

    hiddenFieldRoomNo.set("");
    toDateReservation.set(null);
    fromDateReservation.set(null);
    reserveNoBeds.set("");
    reserveRoomNo.set("");
    reserveBalcony.set(false);
    reserveBathroom.set(false);
    reserveKitchen.set(false);
    reserveInternet.set(false);
    fromDateNewReservation.set(null);
    toDateNewReservation.set(null);

    employeeUsernameFilter.set("");
    employeeFirstNameFilter.set("");
    employeeLastNameFilter.set("");
    employeePhoneNumberFilter.set("");
    employeePosition.set("");

    roomNoFilter.set("");
    bedsFilter.set("");
    usernameFilter.set("");
    firstNameFilter.set("");
    lastNameFilter.set("");
    phoneNumberFilter.set("");
    paymentInfoFilter.set("");

    allBookingFilter.set(true);


    ArrayList<Room> allRooms;
    ArrayList<Employee> allEmployee;
    ArrayList<Customer> allCustomer;
    ArrayList<Reservation> allReservations;
    try
    {
      allRooms = model.getAllRooms();
      allCustomer = model.getAllCustomers();
      allEmployee = model.getAllEmployees();
      allReservations = model.getAllReservations();
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
    ObservableList<Room> roomObservableList = FXCollections.observableList(
        allRooms);
    ObservableList<Customer> customerObservableList = FXCollections.observableList(
        allCustomer);
    ObservableList<Employee> employeeObservableList = FXCollections.observableList(
        allEmployee);
    ObservableList<Reservation> reservationObservableList = FXCollections.observableList(
        allReservations);
    reservations.set(reservationObservableList);
    employees.set(employeeObservableList);
    customers.set(customerObservableList);
    rooms.set(roomObservableList);
    newReservations.set(roomObservableList);
  }

  public Person logOut() throws RemoteException
  {
    return model.logOut();
  }
  public void saveRoom(Room room)
  {
    model.saveSelectedRoom(room);
  }

  public String deleteRoom(Room room) throws RemoteException
  {
    return model.deleteRoom(room.getRoomNo());
  }

  public void filterReservation() throws RemoteException
  {
    String state = "";
    if (allBookingFilter.getValue())
      state = "all";
    if (reservationFilter.getValue())
      state = "Reserved";
    if (bookingFilter.getValue())
      state = "Booked";
    ArrayList<Reservation> filtered;
    if (fromDateReservation.getValue() == null
        || toDateReservation.getValue() == null)
    {
      filtered = model.getFilteredReservation(state, null, null);
    }
    else
      filtered=model.getFilteredReservation(state,MyDate.LocalDateToMyDate(fromDateReservation.getValue()),MyDate.LocalDateToMyDate(toDateReservation.getValue()));
    ObservableList<Reservation> reservationObservableList=FXCollections.observableList(filtered);
    reservations.set(reservationObservableList);
  }

  public void simpleFilterEmployee() throws RemoteException
  {
    ArrayList<Employee> filterEmployee;
    try
    {
      filterEmployee = model.filterEmployee(filteringEmployee.getValue());
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
    ObservableList<Employee> employeeObservableList=FXCollections.observableList(filterEmployee);
    employees.set(employeeObservableList);
  }

  public void filterCustomer(String customer) throws RemoteException
  {
    ArrayList<Customer> filterEmployee;
    try
    {
      filterEmployee = model.filterCustomer(customer);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
    ObservableList<Customer> employeeObservableList=FXCollections.observableList(filterEmployee);
    customers.set(employeeObservableList);
  }


  public void filterFilterCustomer() throws RemoteException
  {
    String[] temp = new String[5];
    int counter = 0;
    if (!usernameFilter.getValue().equals(""))
    {
      temp[counter] = usernameFilter.getValue();
      counter++;
    }
    if (!firstNameFilter.getValue().equals(""))
    {
      temp[counter] = " -> , FirstName " + firstNameFilter.getValue();
      counter++;
    }
    if (!lastNameFilter.getValue().equals(""))
    {
      temp[counter] = ", LastName " + lastNameFilter.getValue();
      counter++;
    }
    if (!phoneNumberFilter.getValue().equals(""))
    {
      temp[counter] = ", PhoneNumber " + phoneNumberFilter.getValue();
      counter++;
    }
    if (!paymentInfoFilter.getValue().equals(""))
    {
      temp[counter] = ", PaymentInfo " + paymentInfoFilter.getValue();
    }
    ObservableList<Customer> customerObservableList = FXCollections.observableList(
        model.getFilteredCustomers(temp));
    customers.set(customerObservableList);
  }

  public void saveCustomer(Customer customer)
  {
    model.saveSelectedCustomer(customer);
  }

  public void filterRoom() throws RemoteException
  {
    String[] temp = new String[7];
    int counter = 0;
    if (balconyFilter.getValue())
    {
      temp[counter] = "balcony, ";
      counter++;
    }
    if (kitchenFilter.getValue())
    {
      temp[counter] = "kichenet, ";
      counter++;
    }
    if (internetFilter.getValue())
    {
      temp[counter] = "internet, ";
      counter++;
    }
    if (bathroomFilter.getValue())
    {
      temp[counter] = "bathroom, ";
      counter++;
    }

    if (priceFilter.getValue() != 0)
    {
      temp[counter] = "Price, " + priceFilter.getValue();
      counter++;
    }

    if (!roomNoFilter.getValue().equals(""))
    {
      temp[counter] = "RoomNo: " + roomNoFilter.getValue() + ", ";
      counter++;
    }

    if (!bedsFilter.getValue().equals(""))
    {
      temp[counter] = "NoBeds: " + bedsFilter.getValue() + ", ";
    }

    ObservableList<Room> roomObservableList = FXCollections.observableList(
        model.getFilteredRoom(null,null,temp));
    rooms.set(roomObservableList);
  }

  public void simpleRoomFilter() throws RemoteException
  {
    ObservableList<Room> roomObservableList = FXCollections.observableList(
        model.getSimpleFilteredRoom(filteringRoom.getValue()));
    rooms.set(roomObservableList);
  }


  public void filterNewReservation() throws RemoteException
  {
    String[] temp = new String[7];
    int counter = 0;
    if (reserveBalcony.getValue())
    {
      temp[counter] = "balcony, ";
      counter++;
    }
    if (reserveKitchen.getValue())
    {
      temp[counter] = "kichenet, ";
      counter++;
    }
    if (reserveInternet.getValue())
    {
      temp[counter] = "internet, ";
      counter++;
    }
    if (reserveBathroom.getValue())
    {
      temp[counter] = "bathroom, ";
      counter++;
    }

    if (reservePricePerNight.getValue() != 0)
    {
      temp[counter] = "Price, " + reservePricePerNight.getValue();
      counter++;
    }

    if (!reserveRoomNo.getValue().equals(""))
    {
      temp[counter] = "RoomNo: " + reserveRoomNo.getValue() + ", ";
      counter++;
    }

    if (!reserveNoBeds.getValue().equals(""))
    {
      temp[counter] = "NoBeds: " + reserveNoBeds.getValue() + ", ";
      counter++;
    }
    ObservableList<Room> roomObservableList;

    if (toDateNewReservation.getValue()!=null&&fromDateNewReservation.getValue()!=null){
      if(MyDate.LocalDateToMyDate(toDateNewReservation.getValue()).isBefore(MyDate.LocalDateToMyDate(fromDateNewReservation.getValue()))){
        Alert alert=new Alert(Alert.AlertType.ERROR,"The finish date is before from date",ButtonType.OK);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.showAndWait();
        return;
      }
      roomObservableList = FXCollections.observableList(model.getFilteredRoom(MyDate.LocalDateToMyDate(fromDateNewReservation.getValue()),MyDate.LocalDateToMyDate(toDateNewReservation.getValue()),temp));
    }
    else
      roomObservableList = FXCollections.observableList(model.getFilteredRoom(null,null,temp));
    newReservations.set(roomObservableList);
  }

  public void saveEmployee(Employee employee)
  {
    model.saveSelectedEmployee(employee);
  }

  public void fillHiddenField()
  {
    Room room = model.getSelectedRoom();
    hiddenFieldRoomNo.set(String.valueOf(room.getRoomNo()));
  }

  public void fillHiddenFieldForTest(int something){
    hiddenFieldRoomNo.set(String.valueOf(something));
  }

  public Alerts saveReservation(Reservation reservation)
  {
    if (reservation==null)
    {
      Alerts x = new Alerts(Alert.AlertType.ERROR,"Error","Select a reservation first");
      /*
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "Select a reservation first", ButtonType.OK);
      alert.setHeaderText(null);
      alert.setTitle("Error");
      alert.showAndWait();
       */
      return x;
    }
    model.saveSelectedReservation(reservation);
    return new Alerts(Alert.AlertType.INFORMATION,"Success","Reservation has been saved");
  }

  public boolean addReservation() throws RemoteException
  {
    try
    {
      if (fromDateNewReservation.getValue()==null||toDateNewReservation.getValue()==null)
        throw new IllegalDateException(8);
      if (MyDate.LocalDateToMyDate(fromDateNewReservation.getValue()).isBefore(MyDate.today())||MyDate.LocalDateToMyDate(toDateNewReservation.getValue()).isBefore(MyDate.today()))
        throw new IllegalDateException(9);
      String state= model.addReservation(Integer.parseInt(hiddenFieldRoomNo.getValue()), model.getCurrentCustomer().getUsername(), MyDate.LocalDateToMyDate(fromDateNewReservation.getValue()), MyDate.LocalDateToMyDate(toDateNewReservation.getValue()), false);
      if (state.equals(DatabaseConnection.SUCCESS)){
        Alert alert=new Alert(Alert.AlertType.INFORMATION,"Successfully added a new reservation",ButtonType.OK);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.showAndWait();
        return true;
      }
      if (state.equals(DatabaseConnection.ERROR)){
        Alert alert=new Alert(Alert.AlertType.ERROR,"Error occurred",ButtonType.OK);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.showAndWait();
        return false;
      }
    }
    catch (NumberFormatException e)
    {
      Alert alert=new Alert(Alert.AlertType.ERROR,"Please select a room to reserve",ButtonType.OK);
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.showAndWait();
    }
    catch (IllegalDateException e)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR, e.message(),
          ButtonType.OK);
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.showAndWait();
    }
    return false;
  }

  public void filterEmployee() throws RemoteException
  {
    String[] temp = new String[5];
    int counter = 0;
    if (!employeeUsernameFilter.getValue().equals(""))
    {
      temp[counter] = employeeUsernameFilter.getValue().toLowerCase();
      counter++;
    }
    if (!employeeFirstNameFilter.getValue().equals(""))
    {
      temp[counter] = " -> , firstname " + employeeFirstNameFilter.getValue().toLowerCase();
      counter++;
    }
    if (!employeeLastNameFilter.getValue().equals(""))
    {
      temp[counter] = ", lastname " + employeeLastNameFilter.getValue().toLowerCase();
      counter++;
    }
    if (!employeePhoneNumberFilter.getValue().equals(""))
    {
      temp[counter] = ", phonenumber " + employeePhoneNumberFilter.getValue().toLowerCase();
      counter++;
    }
    if (!employeePosition.getValue().equals(""))
    {
      temp[counter] = ", position " + employeePosition.getValue().toLowerCase();
      System.out.println(temp[counter]);
    }
    ObservableList<Employee> customerObservableList = FXCollections.observableList(
        model.getFilteredEmployee(temp));
    employees.set(customerObservableList);
  }

  public void checkIn() throws RemoteException
  {
    try
    {
      Reservation reservation = model.getSelectedReservation();
      Alert conf=new Alert(Alert.AlertType.CONFIRMATION,reservation.getUsername()+" -> "+reservation.getFromDate()+" / "+reservation.getToDate(),ButtonType.YES,ButtonType.NO);
      conf.setTitle("Confirmation");
      conf.setHeaderText("Do you really want to check in this reservation?");
      conf.showAndWait();
      if (conf.getResult().equals(ButtonType.NO)){
        return;
      }
      if (!(reservation.getFromDate().equals(MyDate.today()))){
        Alert alert=new Alert(Alert.AlertType.ERROR,"You are not supposed to be here yet...GTFO",ButtonType.OK);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.showAndWait();
        return;
      }
      if (!reservation.isCheckedIn())
      {
        String state=model.checkIn(reservation.getRoomNumber(), reservation.getUsername(), reservation.getFromDate());
        if (state.equals(DatabaseConnection.SUCCESS))
        {
          Alert alert = new Alert(Alert.AlertType.INFORMATION, "Check In successful",
              ButtonType.OK);
          alert.setHeaderText(null);
          alert.setTitle("Success");
          alert.showAndWait();
        }
      }
      else
      {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setHeaderText("Error");
        error.setHeaderText("The customer is already checked in");
        error.showAndWait();
      }
    }
    catch (NullPointerException e)
    {
      Alert error = new Alert(Alert.AlertType.ERROR);
      error.setHeaderText("Error");
      error.setHeaderText("Select a reservation");
      error.showAndWait();
    }
    model.saveSelectedReservation(null);
  }

  public void checkOut() throws RemoteException
  {
    try
    {
      Reservation reservation = model.getSelectedReservation();
      Alert conf=new Alert(Alert.AlertType.CONFIRMATION,reservation.getUsername()+" -> "+reservation.getFromDate()+" / "+reservation.getToDate(),ButtonType.YES,ButtonType.NO);
      conf.setTitle("Confirmation");
      conf.setHeaderText("Do you really want to check out this reservation?");
      conf.showAndWait();
      if (conf.getResult().equals(ButtonType.NO)){
        return;
      }
      if (reservation.isCheckedIn())
      {
        String state= model.checkOut(reservation.getRoomNumber(), reservation.getUsername(), reservation.getFromDate());
        if (state.equals(DatabaseConnection.SUCCESS)){
          Alert alert = new Alert(Alert.AlertType.INFORMATION, "Check Out successful",
              ButtonType.OK);
          alert.setHeaderText(null);
          alert.setTitle("Success");
          alert.showAndWait();
        }
      }
      else
      {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setHeaderText("Error");
        error.setHeaderText("The customer has never checked in");
        error.showAndWait();
      }
    }
    catch (NullPointerException e)
    {
      Alert error = new Alert(Alert.AlertType.ERROR);
      error.setHeaderText("Error");
      error.setHeaderText("Select a reservation");
      error.showAndWait();
    }
    model.saveSelectedReservation(null);
  }

  public Alerts deleteReservation()
      throws RemoteException
  {
    Alerts bad = new Alerts(Alert.AlertType.ERROR,"","");
    Reservation selected=model.getSelectedReservation();
    if (selected==null){
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "Select a reservation first", ButtonType.OK);
      alert.setHeaderText(null);
      alert.setTitle("Error");
      alert.showAndWait();
      return bad;
    }
    String state= model.deleteReservation(selected.getRoomNumber(), selected.getUsername(), selected.getFromDate());
    if (state.equals(DatabaseConnection.SUCCESS))
    {
      /*
      Alert good = new Alert(Alert.AlertType.INFORMATION);
      good.setHeaderText("The reservation has been canceled.");
      good.showAndWait();
       */
      Alerts x = new Alerts(Alert.AlertType.INFORMATION,"Success","Reservation has been canceled");
      return x;
    }
    else {
      Alert bads = new Alert(Alert.AlertType.ERROR);
      bads.setHeaderText("You cannot cancel this reservation");
      bads.showAndWait();
      return bad;
    }
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      update();
    });
  }
}