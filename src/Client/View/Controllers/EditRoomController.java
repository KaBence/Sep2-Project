package Client.View.Controllers;

import Client.View.ViewHandler;
import Client.ViewModel.EditRoomViewModel;
import Client.ViewModel.HomeViewModel;
import javafx.scene.layout.Region;

public class EditRoomController
{
  private Region root;
  private ViewHandler viewHandler;
  private EditRoomViewModel viewModel;
  public void init(ViewHandler viewHandler, EditRoomViewModel viewModel, Region root){
    this.viewHandler=viewHandler;
    this.viewModel=viewModel;
    this.root=root;

  }

  public Region getRoot(){
    root.setUserData("Edit Room");
    return root;
  }

  public void reset(){

  }
}
