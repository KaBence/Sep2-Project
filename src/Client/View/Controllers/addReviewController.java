package Client.View.Controllers;

import Client.View.SceneNames;
import Client.View.ViewHandler;
import Client.ViewModel.AddReviewViewModel;
import Client.ViewModel.CustomerHomeViewModel;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;

import java.awt.*;
import java.rmi.RemoteException;

public class addReviewController
{
  @FXML TextArea review;
  private Region root;
  private ViewHandler viewHandler;
  private AddReviewViewModel viewModel;

  public void init(ViewHandler viewHandler, AddReviewViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
  }

  public Region getRoot()
  {
    root.setUserData("add a review Page");
    return root;
  }

  public void reset()
  {
    // viewModel.update();
  }

  @FXML void Home()
  {
    viewHandler.openView(SceneNames.Home);
  }

  @FXML void create() throws RemoteException
  {
    viewModel.addReview();
  }
}
