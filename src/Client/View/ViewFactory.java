package Client.View;

import Client.View.Controllers.HomeController;
import Client.ViewModel.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;

import java.io.IOError;
import java.io.IOException;

public class ViewFactory
{
  public static final String HOME="home";

  private ViewHandler viewHandler;
  private HomeController homeController;

  private ViewModelFactory viewModelFactory;


  public ViewFactory(ViewHandler viewHandler, ViewModelFactory viewModelFactory){
    this.viewHandler=viewHandler;
    this.viewModelFactory=viewModelFactory;
  }

  private Region loadHomeView(){
    FXMLLoader loader=new FXMLLoader();
    loader.setLocation(getClass().getResource("Scenes/Home.fxml"));
    try
    {
      Region root = loader.load();
      homeController=loader.getController();
      homeController.init(viewHandler,viewModelFactory.getHomeViewModel(),root);
    }
    catch(IOException e){
      throw new IOError(e);
    }
    homeController.reset();
    return homeController.getRoot();
  }

  public Region load(String id){
    return switch (id){
      case HOME -> loadHomeView();
      default -> throw new IllegalArgumentException("Unknown id");
    };
  }
}
