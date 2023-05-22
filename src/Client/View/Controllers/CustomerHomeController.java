package Client.View.Controllers;

import Client.View.SceneNames;
import Client.View.ViewHandler;
import Client.ViewModel.CustomerHomeViewModel;
import Client.ViewModel.HomeViewModel;
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
  @FXML TextField username, password;

  @FXML DatePicker toDateNewReservation;
  @FXML DatePicker fromDateNewReservation;
  @FXML CheckBox reserveBalcony, reserveKitchen, reserveInternet, reserveBathroom;
  @FXML ComboBox<Integer> reservePricePerNight;
  @FXML TextField reserveNrOfBeds;
  @FXML TextField reserveRoomNr;
  @FXML TextField hiddenFieldRoomNo;


  @FXML ListView<Review> listReviews;
  @FXML PasswordField passwordField;

  @FXML ListView<String> myReservations;
  @FXML ListView<Room> roomListViewNewReservation;
  @FXML Button logout, review, cancel, edit;
  @FXML AnchorPane loggingIn,myProfileAnchorPane;
  @FXML TabPane tabPane;
  @FXML Tab newReservation, myReservation, allReviews;
  private Region root;
  private ViewHandler viewHandler;
  private CustomerHomeViewModel viewModel;
  public void init(ViewHandler viewHandler, CustomerHomeViewModel viewModel, Region root){
    this.viewHandler=viewHandler;
    this.viewModel=viewModel;
    this.root=root;
    myProfileAnchorPane.setOpacity(0.0);
    loggingIn.setOpacity(1.0);

    this.viewModel.bindRooms(roomListViewNewReservation.itemsProperty());
    this.viewModel.bindUsername(username.textProperty());
    this.viewModel.bindPassword(passwordField.textProperty());
    this.viewModel.bindReviews(listReviews.itemsProperty());

    viewModel.bindFromDateNewReservation(fromDateNewReservation.valueProperty());
    viewModel.bindReserveBalcony(reserveBalcony.selectedProperty());
    viewModel.bindToDateNewReservation(toDateNewReservation.valueProperty());
    viewModel.bindReserveInternet(reserveInternet.selectedProperty());
    viewModel.bindReserveKitchen(reserveKitchen.selectedProperty());
    viewModel.bindReservePrice(reservePricePerNight.valueProperty());
    viewModel.bindReserveBathroom(reserveBathroom.selectedProperty());
    viewModel.bindHiddenText(hiddenFieldRoomNo.textProperty());
    viewModel.bindReserveRoomNo(reserveRoomNr.textProperty());
    viewModel.bindReserveNoBeds(reserveNrOfBeds.textProperty());
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

  public void initialize(){
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
  }


  @FXML void createNewReservation() throws RemoteException
  {
    viewModel.addReservation();
  }
  @FXML void tableClickNewReservation(){
    viewModel.saveRoom(roomListViewNewReservation.getSelectionModel().getSelectedItem());
    viewModel.fillHiddenField();
  }

  @FXML void filterNewReservation() throws RemoteException
  {
    viewModel.filterNewReservation();
  }

  @FXML void clearDates(){
    fromDateNewReservation.setValue(null);
    toDateNewReservation.setValue(null);
  }
  @FXML void register()
  {
    viewHandler.openView(SceneNames.AddCustomer);
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
