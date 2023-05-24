package Client.View.Controllers.Customer;

import Client.Utility.Alerts;
import Client.View.Scenes.SceneNames;
import Client.View.ViewHandler;
import Client.ViewModel.Customer.CustomerHomeViewModel;
import Server.Model.Hotel.Reservation;
import Server.Model.Hotel.Review;
import Server.Model.Hotel.Room;
import Server.Utility.IllegalDateException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class CustomerHomeController
{
  @FXML TextField username;
  @FXML Label firstName, lastName;

  @FXML DatePicker toDateNewReservation;
  @FXML DatePicker fromDateNewReservation;
  @FXML CheckBox reserveBalcony, reserveKitchen, reserveInternet, reserveBathroom;
  @FXML ComboBox<Integer> reservePricePerNight;
  @FXML TextField reserveNrOfBeds;
  @FXML TextField reserveRoomNr;
  @FXML TextField hiddenFieldRoomNo;

  @FXML ListView<Review> listReviews;
  @FXML PasswordField passwordField;

  @FXML ListView<Reservation> myReservations;
  @FXML ListView<Room> roomListViewNewReservation;
  @FXML Button logout, cancel, edit;
  @FXML AnchorPane loggingIn, myProfileAnchorPane;
  @FXML TabPane tabPane;
  @FXML Tab newReservation, myReservation, allReviews;
  private Region root;
  private ViewHandler viewHandler;
  private CustomerHomeViewModel viewModel;

  public void init(ViewHandler viewHandler, CustomerHomeViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    if (viewModel.previousView())
    {
      myProfileAnchorPane.setOpacity(0.0);
      loggingIn.setOpacity(1.0);
    }
    else
    {
      myProfileAnchorPane.setOpacity(1.0);
      loggingIn.setLayoutY(400.00);
    }
    this.viewModel.bindRooms(roomListViewNewReservation.itemsProperty());
    this.viewModel.bindUsername(username.textProperty());
    this.viewModel.bindPassword(passwordField.textProperty());
    this.viewModel.bindReviews(listReviews.itemsProperty());

    this.viewModel.bindFromDateNewReservation(fromDateNewReservation.valueProperty());
    this.viewModel.bindReserveBalcony(reserveBalcony.selectedProperty());
    this.viewModel.bindToDateNewReservation(toDateNewReservation.valueProperty());
    this.viewModel.bindReserveInternet(reserveInternet.selectedProperty());
    this.viewModel.bindReserveKitchen(reserveKitchen.selectedProperty());
    this.viewModel.bindReservePrice(reservePricePerNight.valueProperty());
    this.viewModel.bindReserveBathroom(reserveBathroom.selectedProperty());
    this.viewModel.bindHiddenText(hiddenFieldRoomNo.textProperty());
    this.viewModel.bindReserveRoomNo(reserveRoomNr.textProperty());
    this.viewModel.bindReserveNoBeds(reserveNrOfBeds.textProperty());

    this.viewModel.bindMyReservation(myReservations.itemsProperty());
    this.viewModel.bindFirstName(firstName.textProperty());
    this.viewModel.bindLastName(lastName.textProperty());
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

  public void initialize()
  {
    ArrayList<Integer> prices = new ArrayList<>();
    prices.add(0);
    prices.add(200);
    prices.add(270);
    prices.add(300);
    prices.add(500);
    reservePricePerNight.setItems(FXCollections.observableList(prices));
  }

  @FXML void Home()
  {
    viewHandler.openView(SceneNames.Home);
    onLogOut();
  }

  @FXML void createNewReservation()
  {
    if (viewModel.addReservation() == null)
    {
      SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
      selectionModel.select(1);
    }
  }

  @FXML void tableClickNewReservation()
  {
    if (roomListViewNewReservation.getSelectionModel().getSelectedItem()
        != null)
    {
      viewModel.saveRoom(
          roomListViewNewReservation.getSelectionModel().getSelectedItem());
      viewModel.fillHiddenField();
    }
  }

  @FXML void filterNewReservation()
  {
    try
    {
      viewModel.filterNewReservation();
    }
    catch (IllegalDateException e)
    {
      Alerts x = new Alerts(Alert.AlertType.ERROR,"Error", e.message());
      x.showAndWait();
    }
    catch (RemoteException e)
    {
      Alerts alert = new Alerts(Alert.AlertType.ERROR,"Error","Error occurred");
      alert.showAndWait();
    }
  }

  @FXML void clearDates()
  {
    fromDateNewReservation.setValue(null);
    toDateNewReservation.setValue(null);
  }

  @FXML void register()
  {
    viewHandler.openView(SceneNames.AddCustomer);
  }

  @FXML void review()
  {
    Alerts x = viewModel.getSelectedReservation();
    if (!x.getAlertType().equals(Alert.AlertType.ERROR))
    {
      viewHandler.openView(SceneNames.Review);
    }
    x.showAndWait();
  }

  @FXML void onLogin()
  {
    Alerts x = viewModel.logIn();
    if (x.getAlertType().equals(Alert.AlertType.NONE))
    {
      myProfileAnchorPane.setOpacity(1.0);
      loggingIn.setLayoutY(400.00);
    }
    username.clear();
    passwordField.clear();
  }

  @FXML public void onLogOut()
  {
    Alerts x = viewModel.logOut();
    if (x.getAlertType().equals(Alert.AlertType.NONE))
    {
      myProfileAnchorPane.setOpacity(0.0);
      loggingIn.setLayoutY(00.00);
    }
  }

  @FXML void tableClickReservation()
  {
    if (myReservations.getSelectionModel().getSelectedItem() != null)
    {
      viewModel.saveReservation(
          myReservations.getSelectionModel().getSelectedItem());
    }
  }

  @FXML void cancelReservation()
  {
    Alerts x = viewModel.cancelReservation();
    x.showAndWait();
  }

  @FXML void editReservation()
  {
    Alerts x = viewModel.reservationEditCheckers();
    x.showAndWait();
    if (x.getAlertType().equals(Alert.AlertType.NONE))
    {
      viewModel.saveReservation(myReservations.getSelectionModel().getSelectedItem());
      viewHandler.openView(SceneNames.CustomerEditReservation);
    }
  }

  public SingleSelectionModel<Tab> selection(int i)
  {
    SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
    selectionModel.select(i);
    return selectionModel;
  }
}
