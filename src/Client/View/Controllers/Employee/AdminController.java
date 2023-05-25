package Client.View.Controllers.Employee;

import Client.Utility.Alerts;
import Client.View.Scenes.SceneNames;
import Client.View.ViewHandler;
import Client.ViewModel.Employee.AdminViewModel;
import Server.Model.Hotel.Users.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;

public class AdminController
{
  @FXML TabPane tabPane;
  @FXML TextField firstNameAddEmployee, lastNameAddEmployee, phoneAddEmployee, password, repeatPassword;
  @FXML ChoiceBox<String> positionAddNewEmployee;
  @FXML TextField employeeUsernameFilter, filteringEmployee, employeeFirstNameFilter, employeeLastNameFilter, employeePositionFilter, employeePhoneNumberFilter;
  @FXML ToggleButton toggleEmployeeButton;
  @FXML ListView<Employee> employeeListView;
  private Region root;
  private ViewHandler viewHandler;
  private AdminViewModel viewModel;
  private boolean toggleEmployeefilter;

  public void init(ViewHandler viewHandler, AdminViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
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

  public void initialize()
  {
    positionAddNewEmployee.getItems().add("Manager");
    positionAddNewEmployee.getItems().add("Cleaner");
    positionAddNewEmployee.getItems().add("Handyman");
    positionAddNewEmployee.getItems().add("Receptionist");
  }

  public Region getRoot()
  {
    root.setUserData("Admin");
    return root;
  }

  public void reset()
  {
    firstNameAddEmployee.clear();
    lastNameAddEmployee.clear();
    phoneAddEmployee.clear();
    positionAddNewEmployee.setValue(null);
    password.clear();
    repeatPassword.clear();
    viewModel.update();
    ToggleEmployee();
  }

  public void wrongPassword()
  {
    password.clear();
    repeatPassword.clear();
  }

  @FXML void home()
  {
    viewHandler.openView(SceneNames.Home);
  }

  @FXML void register()
  {
    Alerts x = viewModel.addEmployee();
    x.showAndWait();
    if (x.getAlertType().equals(Alert.AlertType.WARNING))
    {
      wrongPassword();
    }
    else
    {
      reset();
    }
  }

  @FXML void filterEmployee()
  {
    viewModel.filterEmployee();
  }

  @FXML void simpleFilterEmployee()
  {
    viewModel.simpleFilterEmployee();
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

  public SingleSelectionModel<Tab> selection(int i)
  {
    SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
    selectionModel.select(i);
    return selectionModel;
  }
}
