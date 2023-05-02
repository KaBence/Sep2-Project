package Client.View.Controllers;

import Client.View.ViewHandler;
import Client.ViewModel.CustomerHomeViewModel;
import Client.ViewModel.HomeViewModel;
import javafx.scene.layout.Region;

public class CustomerHomeController
{
  private Region root;
  private ViewHandler viewHandler;
  private CustomerHomeViewModel viewModel;
  public void init(ViewHandler viewHandler, CustomerHomeViewModel viewModel, Region root){
    this.viewHandler=viewHandler;
    this.viewModel=viewModel;
    this.root=root;

  }

  public Region getRoot(){
    root.setUserData("Customer Home Page");
    return root;
  }

  public void reset(){

  }
}
