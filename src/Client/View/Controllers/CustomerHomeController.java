package Client.View.Controllers;

import Client.View.ViewHandler;
import Client.ViewModel.CustomerHomeViewModel;
import Client.ViewModel.HomeViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

import java.awt.*;
import java.awt.Button;
import java.awt.TextField;

public class CustomerHomeController
{
  @FXML TextField info, nrOfBeds, roomNr, username, password;
  @FXML DatePicker fromDate, finishDate;
  @FXML CheckBox balcony, kitchen, internet, bathroom;
  @FXML ComboBox<Integer> pricePerNight;
  @FXML ListView<String> listReviews, roomListView, myReservations;
  @FXML Button logout, review, cancel, edit;
  @FXML AnchorPane loggingIn;
  @FXML TabPane tabPane;
  @FXML Tab newReservation, myReservation, allReviews;
  private Region root;
  private ViewHandler viewHandler;
  private CustomerHomeViewModel viewModel;
  public void init(ViewHandler viewHandler, CustomerHomeViewModel viewModel, Region root){
    this.viewHandler=viewHandler;
    this.viewModel=viewModel;
    this.root=root;

  }

  public Region getRoot(){
    root.setUserData("Customer Home Page");
    return root;
  }

  public void reset(){

  }
}
