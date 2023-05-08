package Client.View.Controllers;

import Client.View.ViewHandler;
import Client.ViewModel.EmployeeLoginViewModel;
import Client.ViewModel.EmployeeSignInViewModel;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;

public class EmployeeSignInController
{
  private Region root;
  private ViewHandler viewHandler;
  private EmployeeSignInViewModel viewModel;
  public void init(ViewHandler viewHandler, EmployeeSignInViewModel viewModel, Region root){
    this.viewHandler=viewHandler;
    this.viewModel=viewModel;
    this.root=root;

  }

  public Region getRoot(){
    root.setUserData("Employee Sign In");
    return root;
  }

  public void reset(){

  }

  @FXML void Add(){

  }
}
