package Client;

import Client.Model.Model;
import Client.Model.ModelManager;
import Client.View.ViewHandler;
import Client.ViewModel.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class StartClient extends Application
{
  private Model model;

  @Override public void start(Stage primaryStage) throws Exception
  {
    model=new ModelManager();
    ViewModelFactory viewModelFactory=new ViewModelFactory(model);
    ViewHandler view=new ViewHandler(viewModelFactory);
    view.start(primaryStage);
  }
}
