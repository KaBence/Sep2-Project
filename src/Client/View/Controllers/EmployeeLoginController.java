package Client.View.Controllers;

import Client.View.SceneNames;
import Client.View.ViewHandler;
import Client.ViewModel.EmployeeLoginViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;

public class EmployeeLoginController
{
  private Region root;
  private ViewHandler viewHandler;
  private EmployeeLoginViewModel viewModel;

  public void init(ViewHandler viewHandler, EmployeeLoginViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;

  }

  public Region getRoot()
  {
    root.setUserData("Employee Login");
    return root;
  }

  public void reset()
  {

  }

  @FXML void login()
  {
    viewHandler.openView(SceneNames.EmployeeHomeReservations);
  }

  @FXML void Back()
  {
    viewHandler.openView(SceneNames.Home);
  }

  @FXML void SignIn()
  {
    Alert x = new Alert(Alert.AlertType.INFORMATION);
    x.setHeaderText("Do you want to register new Employee?");
    x.setTitle("Confirmation");
    x.getButtonTypes().add(ButtonType.YES);
    x.getButtonTypes().add(ButtonType.CANCEL);
    x.getButtonTypes().remove(ButtonType.OK);
    x.showAndWait();
    if (x.getResult().equals(ButtonType.YES))
    {
      viewHandler.openView(SceneNames.EmployeeSignIn);
    }
  }
}
