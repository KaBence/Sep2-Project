package Client.View.Controllers.Employee;

import Client.Utility.Alerts;
import Client.View.Scenes.SceneNames;
import Client.View.ViewHandler;
import Client.ViewModel.Employee.EmployeeLoginViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;

public class EmployeeLoginController
{
  @FXML TextField employeeIdField;
  @FXML PasswordField passwordField;
  private Region root;
  private ViewHandler viewHandler;
  private EmployeeLoginViewModel viewModel;

  public void init(ViewHandler viewHandler, EmployeeLoginViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    viewModel.bindUsername(employeeIdField.textProperty());
    viewModel.bindPassword(passwordField.textProperty());
  }

  public Region getRoot()
  {
    root.setUserData("Employee Login");
    return root;
  }

  public void reset()
  {
    employeeIdField.clear();
    passwordField.clear();
  }

  @FXML void login()
  {
    Alerts x = viewModel.logIn();
    x.showAndWait();
    if (x.getAlertType().equals(Alert.AlertType.NONE))
    {
      if (employeeIdField.getText().equals("admin")&&passwordField.getText().equals("admin"))
      {
        viewHandler.openView(SceneNames.AdminAddEmployee);
      }
      else
      {
        viewHandler.openView(SceneNames.EmployeeHomeReservations);
      }
      reset();
    }
    else
    {
      if (x.getContentText().equals("Invalid password"))
      {
        passwordField.clear();
      }
      else
      {
        reset();
      }
    }
  }

  @FXML void Back()
  {
    viewHandler.openView(SceneNames.Home);
  }
}
