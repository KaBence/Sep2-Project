package Client.View.Controllers;

import Client.View.SceneNames;
import Client.View.ViewHandler;
import Client.ViewModel.AdminViewModel;
import Server.Model.Hotel.Users.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;

public class AdminController
{
  private Region root;
  private ViewHandler viewHandler;
  private AdminViewModel viewModel;

  @FXML TextField firstNameAddEmployee,lastNameAddEmployee,phoneAddEmployee, password, repeatPassword;
  @FXML ChoiceBox<String> positionAddNewEmployee;

  @FXML TextField employeeUsernameFilter,filteringEmployee, employeeFirstNameFilter, employeeLastNameFilter, employeePositionFilter, employeePhoneNumberFilter;
  @FXML ToggleButton toggleEmployeeButton;
  @FXML ListView<Employee> employeeListView;

  private boolean toggleEmployeefilter;

  public void init(ViewHandler viewHandler, AdminViewModel viewModel, Region root){
    this.viewHandler=viewHandler;
    this.viewModel=viewModel;
    this.root=root;
    viewModel.bindEmployees(employeeListView.itemsProperty());


    viewModel.bindFirstName(firstNameAddEmployee.textProperty());
    viewModel.bindLastName(lastNameAddEmployee.textProperty());
    viewModel.bindPhoneNo(phoneAddEmployee.textProperty());
    viewModel.bindPosition(positionAddNewEmployee.valueProperty());
    viewModel.bindPassword(password.textProperty());
    viewModel.bindRepeatPassword(repeatPassword.textProperty());

    viewModel.bindEmployeeUsername(employeeUsernameFilter.textProperty());
    viewModel.bindEmployeeFirstName(employeeFirstNameFilter.textProperty());
    viewModel.bindEmployeeLastName(employeeLastNameFilter.textProperty());
    viewModel.bindEmployeePosition(employeePositionFilter.textProperty());
    viewModel.bindEmployeePhoneNo(employeePhoneNumberFilter.textProperty());
    viewModel.bindFilteringEmployee(filteringEmployee.textProperty());
  }

  public void initialize(){
    positionAddNewEmployee.getItems().add("Manager");
    positionAddNewEmployee.getItems().add("Cleaner");
    positionAddNewEmployee.getItems().add("Handyman");
    positionAddNewEmployee.getItems().add("Receptionist");
  }

  public Region getRoot(){
    root.setUserData("Admin");
    return root;
  }

  public void reset(){
    firstNameAddEmployee.clear();
    lastNameAddEmployee.clear();
    phoneAddEmployee.clear();
    positionAddNewEmployee.setValue(null);
    password.clear();
    repeatPassword.clear();
    viewModel.update();
    ToggleEmployee();
  }

  @FXML void home()
  {
    viewHandler.openView(SceneNames.Home);
  }

  @FXML void register() throws RemoteException
  {
    viewModel.addEmployee();
    reset();
  }

  @FXML void filterEmployee() throws RemoteException
  {
    viewModel.filterEmployee();
  }

  @FXML void simpleFilterEmployee() throws RemoteException
  {
    viewModel.simpleFilterEmployee();
  }

  @FXML void tableClickEmployee(MouseEvent event){
    viewModel.saveEmployee(employeeListView.getSelectionModel().getSelectedItem());
    if (event.getClickCount() == 2)
    {
      viewHandler.openView(SceneNames.EditEmployee);
    }
  }

  @FXML void ToggleEmployee(){
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
}
