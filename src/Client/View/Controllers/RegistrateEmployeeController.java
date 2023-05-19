package Client.View.Controllers;

import Client.View.SceneNames;
import Client.View.ViewHandler;
import Client.ViewModel.RegisterEmployeeViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;

public class RegistrateEmployeeController
{
  @FXML TextField firstNameAddNewEmployee, lastNameAddEmployee, phoneAddEmployee, password, repeatPassword;
  @FXML ChoiceBox<String> positionAddNewEmployee;
  //@FXML PasswordField password, repeatPassword;

  private Region root;
  private ViewHandler viewHandler;
  private RegisterEmployeeViewModel viewModel;

  public void init(ViewHandler viewHandler, RegisterEmployeeViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    viewModel.bindFirstName(firstNameAddNewEmployee.textProperty());
    viewModel.bindLastName(lastNameAddEmployee.textProperty());
    viewModel.bindPhoneNo(phoneAddEmployee.textProperty());
    viewModel.bindPosition(positionAddNewEmployee.valueProperty());
    viewModel.bindPassword(password.textProperty());
    viewModel.bindReapeatPassword(password.textProperty());

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
    root.setUserData("Add an employee to the system");
    return root;
  }

  public void reset()
  {
    firstNameAddNewEmployee.clear();
    lastNameAddEmployee.clear();
    phoneAddEmployee.clear();
    positionAddNewEmployee.setValue(null);
    password.clear();
    repeatPassword.clear();

  }

  @FXML void cancel()
  {
    viewHandler.openView(SceneNames.Home);
  }

  @FXML void register() throws RemoteException
  {

    if (viewModel.addEmployee())
    {

      viewHandler.openView(SceneNames.EmployeeSignIn);
    }

  }
}

