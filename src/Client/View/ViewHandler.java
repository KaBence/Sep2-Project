package Client.View;

import Client.ViewModel.ViewModelFactory;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class ViewHandler
{
  private Stage primaryStage;
  private Scene currentScene;
  private ViewFactory viewFactory;

  public ViewHandler(ViewModelFactory viewModelFactory){
    this.viewFactory=new ViewFactory(this,viewModelFactory);
    this.currentScene=new Scene(new Region());

  }

  public void start(Stage primaryStage){
    this.primaryStage=primaryStage;
    openView(SceneNames.Home);
  }

  public void openView(SceneNames id){
    Region root =viewFactory.load(id);
    currentScene.setRoot(root);
    if (root.getUserData()==null){
      primaryStage.setTitle("");
    }
    else primaryStage.setTitle(root.getUserData().toString());
    primaryStage.setScene(currentScene);
    primaryStage.sizeToScene();
    primaryStage.getIcons().add(new Image("Icon.png"));
    primaryStage.show();
  }
}
