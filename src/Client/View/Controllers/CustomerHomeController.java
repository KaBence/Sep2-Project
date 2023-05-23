package Client.View.Controllers;

import Client.View.SceneNames;
import Client.View.ViewHandler;
import Client.ViewModel.CustomerHomeViewModel;
import Client.ViewModel.HomeViewModel;
import Server.Model.Hotel.Reservation;
import Server.Model.Hotel.Review;
import Server.Model.Hotel.Room;
import javafx.collections.FXCollections;
import Server.Utility.DataBase.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
  @FXML Button logout, review, cancel, edit;
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
    myProfileAnchorPane.setOpacity(0.0);
    loggingIn.setOpacity(1.0);

    this.viewModel.bindRooms(roomListViewNewReservation.itemsProperty());
    this.viewModel.bindUsername(username.textProperty());
    this.viewModel.bindPassword(passwordField.textProperty());
    this.viewModel.bindReviews(listReviews.itemsProperty());

    viewModel.bindFromDateNewReservation(
        fromDateNewReservation.valueProperty());
    viewModel.bindReserveBalcony(reserveBalcony.selectedProperty());
    viewModel.bindToDateNewReservation(toDateNewReservation.valueProperty());
    viewModel.bindReserveInternet(reserveInternet.selectedProperty());
    viewModel.bindReserveKitchen(reserveKitchen.selectedProperty());
    viewModel.bindReservePrice(reservePricePerNight.valueProperty());
    viewModel.bindReserveBathroom(reserveBathroom.selectedProperty());
    viewModel.bindHiddenText(hiddenFieldRoomNo.textProperty());
    viewModel.bindReserveRoomNo(reserveRoomNr.textProperty());
    viewModel.bindReserveNoBeds(reserveNrOfBeds.textProperty());

    this.viewModel.bindMyReservation(myReservations.itemsProperty());
    viewModel.bindFirstName(firstName.textProperty());
    viewModel.bindLastName(lastName.textProperty());
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

  @FXML void createNewReservation() throws RemoteException
  {
    viewModel.addReservation();
  }

  @FXML void tableClickNewReservation()
  {
    if (roomListViewNewReservation.getSelectionModel().getSelectedItem()
        != null)
      viewModel.saveRoom(
          roomListViewNewReservation.getSelectionModel().getSelectedItem());
    viewModel.fillHiddenField();
  }

  @FXML void filterNewReservation() throws RemoteException
  {
    viewModel.filterNewReservation();
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
    if (viewModel.getSelectedReservation()){
      viewHandler.openView(SceneNames.Review);
    }
  }

  @FXML void onLogin()
  {
    Boolean x = viewModel.logIn();
    if (x)
    {
      myProfileAnchorPane.setOpacity(1.0);
      loggingIn.setLayoutY(400.00);
    }
    username.clear();
    passwordField.clear();
  }

  @FXML public void onLogOut()
  {
    if (viewModel.logOut())
    {
      myProfileAnchorPane.setOpacity(0.0);
      loggingIn.setLayoutY(00.00);
    }
  }

  @FXML void tableClickReservation()
  {
    if (myReservations.getSelectionModel().getSelectedItem()
        != null)
    {
      viewModel.saveReservation(myReservations.getSelectionModel().getSelectedItem());
    }
  }

  public SingleSelectionModel<Tab> selection(int i)
  {
    SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
    selectionModel.select(i);
    return selectionModel;
  }

  @FXML void cancelReservation() throws RemoteException
  {
    Reservation temp = myReservations.getSelectionModel().getSelectedItem();
    if (temp == null)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "Select a reservation first", ButtonType.OK);
      alert.setHeaderText(null);
      alert.setTitle("Error");
      alert.showAndWait();
    }
    else if (temp.isCheckedIn())
    {
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "You cannot cancel active booking", ButtonType.OK);
      alert.setHeaderText(null);
      alert.setTitle("Error");
      alert.showAndWait();
    }
    else if(!temp.isCheckedIn())
    {
      if (viewModel.cancelReservation(temp.getRoomNumber(), temp.getUsername(),
          temp.getFromDate()).equals(DatabaseConnection.SUCCESS))
      {
        Alert good = new Alert(Alert.AlertType.INFORMATION);
        good.setHeaderText("The reservation has been canceled.");
        good.showAndWait();
      }
      else
      {
        Alert bad = new Alert(Alert.AlertType.ERROR);
        bad.setHeaderText("You cannot cancel this reservation");
        bad.showAndWait();
      }
    }
  }

}
