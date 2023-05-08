package Client.View.Controllers;

import Client.View.SceneNames;
import Client.View.ViewHandler;
import Client.ViewModel.EditRoomViewModel;
import Client.ViewModel.HomeViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class EditRoomController
{
  private Region root;
  private ViewHandler viewHandler;
  private EditRoomViewModel viewModel;

  @FXML TextField roomNo,size,beds;
  @FXML ChoiceBox<String> orientation;
  @FXML ChoiceBox<Integer> price;
  @FXML CheckBox internet,balcony,kitchen,bathroom;
  public void init(ViewHandler viewHandler, EditRoomViewModel viewModel, Region root){
    this.viewHandler=viewHandler;
    this.viewModel=viewModel;
    this.root=root;


    viewModel.bindNoBeds(beds.textProperty());
    viewModel.bindBalcony(balcony.selectedProperty());
    viewModel.bindBathroom(bathroom.selectedProperty());
    viewModel.bindInternet(internet.selectedProperty());
    viewModel.bindKitchen(kitchen.selectedProperty());
    viewModel.bindPrice(price.valueProperty());
    viewModel.bindRoomNo(roomNo.textProperty());

    viewModel.bindSize(size.textProperty());

    viewModel.bindOrientation(orientation.valueProperty());
  }

  public Region getRoot(){
    root.setUserData("Edit Room");
    return root;
  }

  public void reset(){
    viewModel.fill();
  }

  @FXML void Save(){

  }

  @FXML void Cancel(){
    viewHandler.openView(SceneNames.EmployeeHome);
  }
}
