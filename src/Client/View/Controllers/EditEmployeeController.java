package Client.View.Controllers;

import Client.View.SceneNames;
import Client.View.ViewHandler;
import Client.ViewModel.EditEmployeeViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;



public class EditEmployeeController
{
  private Region root;
  private ViewHandler viewHandler;
  private EditEmployeeViewModel viewModel;

  @FXML TextField firstName, lastName, phoneNo;
  @FXML ChoiceBox<String> position;

  public void init(ViewHandler viewHandler, EditEmployeeViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    position.getItems().add("Manager");
    position.getItems().add("Cleaner");
    position.getItems().add("Handyman");
    position.getItems().add("Receptionists");

    this.viewModel.bindFirstName(firstName.textProperty());
    this.viewModel.bindLastName(lastName.textProperty());
    this.viewModel.bindPosition(position.valueProperty());
    this.viewModel.bindPhoneNo(phoneNo.textProperty());
  }

  public Region getRoot(){
    root.setUserData("Edit Employee Info");
    return root;
  }

  public void reset(){

  }

  @FXML void save(){

  }

  @FXML void cancel(){
    viewHandler.openView(SceneNames.EmployeeHome);
  }
}
