package Client;

import Client.Model.Model;
import Client.Model.ModelManager;
import Client.View.ViewHandler;
import Client.ViewModel.ViewModelFactory;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.rmi.RemoteException;

public class StartClient extends Application
{
  private Model model;
  @Override public void start(Stage primaryStage) throws Exception
  {
    model=new ModelManager();
    ViewModelFactory viewModelFactory=new ViewModelFactory(model);
    ViewHandler view=new ViewHandler(viewModelFactory);
    primaryStage.setResizable(false);
    view.start(primaryStage);

    //When we close a client a thread stops (client is fully closed)
    primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>()
    {
      @Override public void handle(WindowEvent event)
      {
        if (model.getCurrent())
        {
          try
          {
            model.logOut();
          }
          catch (RemoteException e)
          {
            throw new RuntimeException(e);
          }
        }
        Platform.exit();
        System.exit(0);
      }
    });
  }
}

