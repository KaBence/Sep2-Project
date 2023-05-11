package Client.View.Controllers;

import Client.View.ViewHandler;
import Client.ViewModel.EditEmployeeViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Region;

import java.awt.*;

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

    this.viewModel.bindFirstName(firstName.);
    this.viewModel.bindLastName();
    this.viewModel.bindPosition();
    this.viewModel.bindPhoneNo();
  }

  public Region getRoot(){
    root.setUserData("Edit Employee Info");
    return root;
  }

  public void reset(){

  }
}
