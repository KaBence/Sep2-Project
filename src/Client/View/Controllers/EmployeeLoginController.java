package Client.View.Controllers;

import Client.View.SceneNames;
import Client.View.ViewHandler;
import Client.ViewModel.EmployeeLoginViewModel;
import Client.ViewModel.HomeViewModel;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;

public class EmployeeLoginController
{
  private Region root;
  private ViewHandler viewHandler;
  private EmployeeLoginViewModel viewModel;
  public void init(ViewHandler viewHandler, EmployeeLoginViewModel viewModel, Region root){
    this.viewHandler=viewHandler;
    this.viewModel=viewModel;
    this.root=root;

  }

  public Region getRoot(){
    root.setUserData("Employee Login");
    return root;
  }

  public void reset(){

  }

  @FXML void login(){
    viewHandler.openView(SceneNames.EmployeeHome);
  }

  @FXML void Back(){
    viewHandler.openView(SceneNames.Home);
  }
  @FXML void SignIn(){viewHandler.openView(SceneNames.EmployeeSignIn);}
}
