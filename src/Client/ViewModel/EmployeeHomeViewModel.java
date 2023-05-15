package Client.ViewModel;

import Client.Model.Model;
import Server.Model.Customer;
import Server.Model.Employee;
import Server.Model.Room;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class EmployeeHomeViewModel implements PropertyChangeListener
{
  private Model model;
  private SimpleObjectProperty<ObservableList<Room>> rooms;
  private SimpleObjectProperty<ObservableList<Customer>> customers;
  private SimpleObjectProperty<ObservableList<Employee>> employees;

  private SimpleBooleanProperty bathroomFilter,kitchenFilter,internetFilter,balconyFilter;
  private SimpleObjectProperty<Integer> priceFilter;

  private SimpleStringProperty roomNoFilter,bedsFilter;

  public EmployeeHomeViewModel(Model model)
  {
    this.model = model;
    model.addPropertyChangeListener(this);
    this.rooms = new SimpleObjectProperty<>();
    customers=new SimpleObjectProperty<>();
    employees=new SimpleObjectProperty<>();

    balconyFilter=new SimpleBooleanProperty();
    kitchenFilter=new SimpleBooleanProperty();
    internetFilter=new SimpleBooleanProperty();
    bathroomFilter=new SimpleBooleanProperty();
    priceFilter=new SimpleObjectProperty<>();
    roomNoFilter=new SimpleStringProperty();
    bedsFilter=new SimpleStringProperty();

    bedsFilter.set("");
    roomNoFilter.set("");
  }

  public void bindRoomList(ObjectProperty<ObservableList<Room>> property)
  {
    property.bindBidirectional(rooms);
  }

  public void bindCustomerList(ObjectProperty<ObservableList<Customer>> property){
    property.bindBidirectional(customers);
  }

  public void bindEmployeeList(ObjectProperty<ObservableList<Employee>> property){
    property.bindBidirectional(employees);
  }

  public void bindInternet(BooleanProperty property){
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

  public void update(){
    balconyFilter.set(false);
    bathroomFilter.set(false);
    kitchenFilter.set(false);
    internetFilter.set(false);
    roomNoFilter.set("");
    bedsFilter.set("");
    priceFilter.set(0);
    ArrayList<Room> allRooms;
    ArrayList<Employee> allEmployee;
    ArrayList<Customer> allCustomer;
    try
    {
      allRooms = model.getAllRooms();
      allCustomer=model.getAllCustomers();
      allEmployee=model.getAllEmployees();
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
    ObservableList<Room> roomObservableList = FXCollections.observableList(allRooms);
    ObservableList<Customer> customerObservableList= FXCollections.observableList(allCustomer);
    ObservableList<Employee> employeeObservableList=FXCollections.observableList(allEmployee);
    employees.set(employeeObservableList);
    customers.set(customerObservableList);
    rooms.set(roomObservableList);
  }

  public void saveRoom(Room room){
    model.saveSelectedRoom(room);
  }

  public String deleteRoom(Room room) throws RemoteException
  {
    return model.deleteRoom(room.getRoomNo());
  }

  public void filterEmployee(String employee)
      throws RemoteException
  {
    ArrayList<Employee> filterEmployee;
    try
    {
      filterEmployee = model.filterEmployee(employee);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
    ObservableList<Employee> employeeObservableList=FXCollections.observableList(filterEmployee);
    employees.set(employeeObservableList);
  }

  public void filterCustomer(String customer)
      throws RemoteException
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

  public void saveCustomer(Customer customer){
    model.saveSelectedCustomer(customer);
  }

  public void filterRoom() throws RemoteException
  {
    String[] temp=new String[7];
    int counter=0;
    if (balconyFilter.getValue()){
      temp[counter]="balcony, ";
      counter++;
    }
    if (kitchenFilter.getValue()){
      temp[counter]="kichenet, ";
      counter++;
    }
    if (internetFilter.getValue()){
      temp[counter]="internet, ";
      counter++;
    }
    if (bathroomFilter.getValue()){
      temp[counter]="bathroom, ";
      counter++;
    }

    if (priceFilter.getValue()!=0){
      temp[counter]="Price, "+priceFilter.getValue();
      counter++;
    }

    if (!roomNoFilter.getValue().equals("")){
      temp[counter]="RoomNo: "+roomNoFilter.getValue()+", ";
      counter++;
    }

    if (!bedsFilter.getValue().equals("")){
      temp[counter]= "NoBeds: "+bedsFilter.getValue()+", ";
    }

    ObservableList<Room> roomObservableList = FXCollections.observableList(model.getFilteredRoom(temp));
    rooms.set(roomObservableList);
  }

  public void saveEmployee(Employee employee){
    model.saveSelectedEmployee(employee);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      update();
    });
  }
}
