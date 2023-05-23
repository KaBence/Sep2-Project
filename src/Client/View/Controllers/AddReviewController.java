package Client.View.Controllers;

import Client.View.SceneNames;
import Client.View.ViewHandler;
import Client.ViewModel.AddReviewViewModel;
import Server.Model.Hotel.Reservation;
import javafx.fxml.FXML;
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
  }

  public Region getRoot()
  {
    root.setUserData("add a review Page");
    return root;
  }

  public void reset()
  {
    //viewModel.update();
  }

  @FXML void Home()
  {
    viewHandler.openView(SceneNames.CustomerHomeNewReservations);
    viewModel.logOut();
  }

  @FXML void create() throws RemoteException
  {
    viewModel.addReview();
  }
}
