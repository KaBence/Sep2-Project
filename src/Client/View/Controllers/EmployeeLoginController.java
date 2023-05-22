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
    String login = viewModel.logIn(employeeIdField.getText(),passwordField.getText());
    if (login.equals("admin")){
      viewHandler.openView(SceneNames.Admin);
      return;
    }
    if (login.equals(DatabaseConnection.SUCCESS))
    {
      viewHandler.openView(SceneNames.EmployeeHomeReservations);
    }
    else if (login.equals(DatabaseConnection.PASSWORD))
    {
      Alert x = new Alert(Alert.AlertType.ERROR,"Invalid password");
      x.showAndWait();
    }
    else
    {
      Alert x = new Alert(Alert.AlertType.ERROR,"User: "+employeeIdField.getText()+", does not exist");
      x.showAndWait();
    }
  }

  @FXML void Back()
  {
    viewHandler.openView(SceneNames.Home);
  }


}
