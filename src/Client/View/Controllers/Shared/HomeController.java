package Client.View.Controllers.Shared;

import Client.View.Scenes.SceneNames;
import Client.View.ViewHandler;
import Client.ViewModel.Shared.HomeViewModel;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;

public class HomeController
{
  private Region root;
  private ViewHandler viewHandler;
  private HomeViewModel homeViewModel;
  public void init(ViewHandler viewHandler, HomeViewModel homeViewModel, Region root){
    this.viewHandler=viewHandler;
    this.homeViewModel=homeViewModel;
    this.root=root;

  }

  public Region getRoot(){
    root.setUserData("Home");
    return root;
  }

  public void reset(){

  }

  @FXML void employee(){
    viewHandler.openView(SceneNames.EmployeeLogin);
  }

  @FXML void customer(){
    viewHandler.openView(SceneNames.CustomerHome);
  }
}
