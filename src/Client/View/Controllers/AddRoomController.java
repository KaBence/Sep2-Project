package Client.View.Controllers;

import Client.View.SceneNames;
import Client.View.ViewHandler;
import Client.ViewModel.AddRoomViewModel;
import Client.ViewModel.CustomerHomeViewModel;
import Server.Model.Room;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class AddRoomController
{

  private Region root;
  private ViewHandler viewHandler;
  private AddRoomViewModel viewModel;

  @FXML TextField roomNo,size,beds;
  @FXML ChoiceBox<String> orientation;
  @FXML CheckBox internet,balcony,kitchen,bathroom;
  public void init(ViewHandler viewHandler, AddRoomViewModel viewModel, Region root){
    this.viewHandler=viewHandler;
    this.viewModel=viewModel;
    this.root=root;

  }

  public void initialize(){
    orientation.getItems().add("North");
    orientation.getItems().add("West");
    orientation.getItems().add("South");
    orientation.getItems().add("East");
  }

  public Region getRoot(){
    root.setUserData("Add a room to the system");
    return root;
  }

  public void reset(){

  }

  @FXML void Add(){
    viewModel.addRoom(Integer.parseInt(roomNo.getText()),Integer.parseInt(beds.getText()),Integer.parseInt(size.getText()),orientation.getValue(),internet.isSelected(),bathroom.isSelected(),kitchen.isSelected(),balcony.isSelected());
  }

  @FXML void Cancel(){
    viewHandler.openView(SceneNames.EmployeeHome);
  }
}
