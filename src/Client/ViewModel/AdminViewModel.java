package Client.ViewModel;

import Client.Model.Model;
import Server.Model.Hotel.Users.Employee;
import Server.Utility.DataBase.DatabaseConnection;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class AdminViewModel
{
  private Model model;
  private SimpleStringProperty username, firstName, lastName, phoneNo,password,repeatPassword;
  private SimpleObjectProperty<String> position;

  private SimpleStringProperty employeeUsernameFilter, employeeFirstNameFilter, employeeLastNameFilter, employeePhoneNumberFilter, employeePosition,filteringEmployee;
  private SimpleObjectProperty<ObservableList<Employee>> employees;



  public AdminViewModel(Model model)
  {
    this.model = model;
    employees=new SimpleObjectProperty<>();
    username = new SimpleStringProperty();
    firstName = new SimpleStringProperty();
    lastName = new SimpleStringProperty();
    position = new SimpleObjectProperty<>();
    phoneNo = new SimpleStringProperty();
    password=new SimpleStringProperty();
    repeatPassword=new SimpleStringProperty();

    employeeUsernameFilter = new SimpleStringProperty();
    employeeFirstNameFilter = new SimpleStringProperty();
    employeeLastNameFilter = new SimpleStringProperty();
    employeePosition = new SimpleStringProperty();
    employeePhoneNumberFilter = new SimpleStringProperty();
    filteringEmployee=new SimpleStringProperty();
  }

  public void update(){
    employeeUsernameFilter.set("");
    employeeFirstNameFilter.set("");
    employeeLastNameFilter.set("");
    employeePhoneNumberFilter.set("");
    employeePosition.set("");
    filteringEmployee.set("");
    ArrayList<Employee> allEmployee;
    try
    {
      allEmployee=model.getAllEmployees();
    }
    catch (RemoteException e){
      throw new RuntimeException(e);
    }
    ObservableList<Employee> employeeObservableList = FXCollections.observableList(
        allEmployee);
    employees.set(employeeObservableList);
  }


  public String edit() throws RemoteException
  {
    try
    {
      return model.updateEmployee(username.getValue(),firstName.getValue(), lastName.getValue(),
          position.getValue(), phoneNo.getValue());
    }
    catch (Exception e)
    {
      return DatabaseConnection.MANDATORY;
    }
  }

  public String delete() throws RemoteException
  {
    return  model.deleteEmployee(username.getValue());
  }

  public boolean addEmployee() throws RemoteException
  {
    if (!(password.getValue().equals(repeatPassword.getValue()))){
      Alert alert=new Alert(Alert.AlertType.ERROR,"The two passwords doesn't match", ButtonType.OK);
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.showAndWait();
      return false;
    }
    String state= model.addEmployee(firstName.getValue(), lastName.getValue(), position.getValue(), phoneNo.getValue(), password.getValue());
    if (state.equals(DatabaseConnection.SUCCESS)){
      Alert alert=new Alert(Alert.AlertType.INFORMATION);
      alert.setHeaderText("Employee added successfully");
      alert.setContentText("New Employee login: "+model.getNewEmployee().getUsername()+"\nPassword: "+password.getValue());
      alert.setTitle("Success");
      alert.showAndWait();
      return true;
    }
    if (state.equals(DatabaseConnection.ERROR)){
      Alert alert=new Alert(Alert.AlertType.ERROR,"An error occurred", ButtonType.OK);
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.showAndWait();
      return false;
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
    ObservableList<Employee> employeeObservableList = FXCollections.observableList(
        model.getFilteredEmployee(temp));
    employees.set(employeeObservableList);
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

  public void saveEmployee(Employee employee)
  {
    model.saveSelectedEmployee(employee);
  }


  public void bindFirstName(StringProperty property)
  {
    property.bindBidirectional(firstName);
  }

  public void bindLastName(StringProperty property)
  {
    property.bindBidirectional(lastName);
  }

  public void bindPosition(ObjectProperty<String> property)
  {
    property.bindBidirectional(position);
  }

  public void bindPhoneNo(StringProperty property)
  {
    property.bindBidirectional(phoneNo);
  }

  public void bindPassword(StringProperty property)
  {
    property.bindBidirectional(password);
  }

  public void bindRepeatPassword(StringProperty property)
  {
    property.bindBidirectional(repeatPassword);
  }

  public void bindEmployees(ObjectProperty<ObservableList<Employee>> property){
    property.bindBidirectional(employees);
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

  public void bindFilteringEmployee(StringProperty property){
    property.bindBidirectional(filteringEmployee);
  }
}
