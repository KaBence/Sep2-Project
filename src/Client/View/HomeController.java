package Client.View;

import Client.ViewModel.HomeViewModel;
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
}
