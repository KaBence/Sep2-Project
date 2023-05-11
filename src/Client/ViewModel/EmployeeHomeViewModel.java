package Client.ViewModel;

import Client.Model.Model;
import Server.Model.Customer;
import Server.Model.Employee;
import Server.Model.Room;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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

  public EmployeeHomeViewModel(Model model)
  {
    this.model = model;
    model.addPropertyChangeListener(this);
    this.rooms = new SimpleObjectProperty<>();
    customers=new SimpleObjectProperty<>();
    employees=new SimpleObjectProperty<>();
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

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      update();
    });
  }
}
