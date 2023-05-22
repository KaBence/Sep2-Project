package Client.View.Controllers;

import Client.View.SceneNames;
import Client.View.ViewHandler;
import Client.ViewModel.CustomerHomeViewModel;
import Client.ViewModel.HomeViewModel;
import Server.Model.Hotel.Reservation;
import Server.Model.Hotel.Review;
import Server.Model.Hotel.Room;
import Server.Utility.DataBase.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

public class CustomerHomeController
{
  @FXML TextField info, nrOfBeds, roomNr, username;
  @FXML PasswordField passwordField;
  @FXML DatePicker fromDate, finishDate;
  @FXML CheckBox balcony, kitchen, internet, bathroom;
  @FXML ComboBox<Integer> pricePerNight;
  @FXML ListView<Reservation>  myReservations;
  @FXML ListView<Room> roomListView;
  @FXML ListView<Review> listReviews;
  @FXML Button logout, cancel, edit;
  @FXML AnchorPane loggingIn;
  @FXML Button logout, review, cancel, edit;
  @FXML AnchorPane loggingIn,myProfileAnchorPane;
  @FXML TabPane tabPane;
  @FXML Tab newReservation, myReservation, allReviews;
  private Region root;
  private ViewHandler viewHandler;
  private CustomerHomeViewModel viewModel;
  public void init(ViewHandler viewHandler, CustomerHomeViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    myProfileAnchorPane.setOpacity(0.0);
    loggingIn.setOpacity(1.0);
    this.viewModel.bindRooms(roomListView.itemsProperty());
    this.viewModel.bindUsername(username.textProperty());
    this.viewModel.bindPassword(passwordField.textProperty());
    this.viewModel.bindReviews(listReviews.itemsProperty());
    this.viewModel.bindMyReservation(myReservations.itemsProperty());

  }


  public Region getRoot()
  {
    root.setUserData("Customer Home Page");
    return root;
  }

  public void reset()
  {
    viewModel.update();
  }

  @FXML void simpleFilterRoom()
  {
  }

  @FXML void Home()
  {
    viewHandler.openView(SceneNames.Home);
  }

  @FXML void tableClickRoom()
  {
  }

  @FXML void filterRoom()
  {
  }

  @FXML void review()
  {
    viewHandler.openView(SceneNames.Review);
  }
  @FXML void onLogin()
  {
    if (viewModel.logIn())
    {
      myProfileAnchorPane.setOpacity(1.0);
      loggingIn.setOpacity(0.0);
    }
  }

}
