package Client.View.Controllers.Customer;

import Client.Utility.Alerts;
import Client.View.Scenes.SceneNames;
import Client.View.ViewHandler;
import Client.ViewModel.Customer.AddReviewViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;

public class AddReviewController
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
    viewModel.bindReviews(review.textProperty());
    viewModel.previousScene(false);
  }

  public Region getRoot()
  {
    root.setUserData("add a review Page");
    return root;
  }

  public void reset()
  {
  }

  @FXML void Home()
  {
    viewHandler.openView(SceneNames.CustomerHomeNewReservations);
  }

  @FXML void create()
  {
    Alerts x = viewModel.addReview();
    x.showAndWait();
    if (x.getAlertType().equals(Alert.AlertType.INFORMATION))
    {
      viewHandler.openView(SceneNames.CustomerHome);
    }
  }
}
