package Client.View.Controllers;

import Client.View.SceneNames;
import Client.View.ViewHandler;
import Client.ViewModel.EmployeeLoginViewModel;
import Server.Utility.DataBase.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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

  }

  @FXML void login() throws RemoteException
  {
    if (viewModel.logIn(employeeIdField.getText(),passwordField.getText()))
    {
      if (employeeIdField.getText().equals("admin")&&passwordField.getText().equals("admin"))
      {
        viewHandler.openView(SceneNames.Admin);
      }
      else
      {
        viewHandler.openView(SceneNames.EmployeeHomeReservations);
      }
    }
    employeeIdField.clear();
    passwordField.clear();
  }

  @FXML void Back()
  {
    viewHandler.openView(SceneNames.Home);
  }


}
