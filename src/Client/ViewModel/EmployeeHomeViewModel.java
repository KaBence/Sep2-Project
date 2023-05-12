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
    System.out.println("estsgdblkgjdhguidhgd");
  }

  public void saveRoom(Room room){
    model.saveSelectedRoom(room);
  }

  public String deleteRoom(Room room) throws RemoteException
  {
    return model.deleteRoom(room.getRoomNo());
  }

  public void saveCustomer(Customer customer){
    model.saveSelectedCustomer(customer);
  }

  public void filterRoom() throws RemoteException
  {
    String temp="";
    if (balconyFilter.getValue())
      temp+="balcony, ";
    if (kitchenFilter.getValue())
      temp+="kichenet, ";
    if (internetFilter.getValue())
      temp+="internet, ";
    if (bathroomFilter.getValue())
      temp+="bathroom, ";
    //if (priceFilter.getValue()!=null)
      //temp+=priceFilter.getValue();
    if (roomNoFilter.getValue()!=null)
      temp+=roomNoFilter.getValue();
    if (bedsFilter.getValue()!=null)
      temp+= bedsFilter.getValue();
    ObservableList<Room> roomObservableList = FXCollections.observableList(model.getFilteredRoom(temp));
    rooms.set(roomObservableList);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      update();
    });
  }
}
