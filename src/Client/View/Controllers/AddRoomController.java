package Client.View.Controllers;

import Client.View.ViewHandler;
import Client.ViewModel.AddRoomViewModel;
import Client.ViewModel.CustomerHomeViewModel;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;

public class AddRoomController
{

  private Region root;
  private ViewHandler viewHandler;
  private AddRoomViewModel viewModel;
  public void init(ViewHandler viewHandler, AddRoomViewModel viewModel, Region root){
    this.viewHandler=viewHandler;
    this.viewModel=viewModel;
    this.root=root;

  }

  public Region getRoot(){
    root.setUserData("Add a room to the system");
    return root;
  }

  public void reset(){

  }

  @FXML void Add(){

  }

  @FXML void Cancel(){

  }
}
