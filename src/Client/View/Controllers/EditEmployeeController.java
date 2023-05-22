package Client.View.Controllers;

import Client.View.SceneNames;
import Client.View.ViewHandler;
import Client.ViewModel.EditEmployeeViewModel;
import Server.Utility.DataBase.Customer.CustomerData;
import Server.Utility.DataBase.DatabaseConnection;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;

public class EditEmployeeController
{
  private Region root;
  private ViewHandler viewHandler;
  private EditEmployeeViewModel viewModel;

  @FXML TextField firstName, lastName, phoneNo;
  @FXML ChoiceBox<String> position;
  @FXML Label username;

  public void init(ViewHandler viewHandler, EditEmployeeViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;


    this.viewModel.bindFirstName(firstName.textProperty());
    this.viewModel.bindLastName(lastName.textProperty());
    this.viewModel.bindPhoneNo(phoneNo.textProperty());
    this.viewModel.bindPosition(position.valueProperty());
    this.viewModel.bindUsername(username.textProperty());
  }

  public void initialize()
  {
    position.getItems().add("Manager");
    position.getItems().add("Cleaner");
    position.getItems().add("Handyman");
    position.getItems().add("Receptionist");

  }
  public Region getRoot(){
    root.setUserData("Edit Employee Info");
    return root;
  }

  public void reset(){
  viewModel.fill();
  }

  @FXML void save() throws RemoteException
  {
    String temp = viewModel.edit();
    if (temp.equals(DatabaseConnection.SUCCESS))
    {
      Alert alert = new Alert(Alert.AlertType.INFORMATION, "Edit Successful",
          ButtonType.OK);
      alert.setHeaderText(null);
      alert.setTitle("Success");
      alert.showAndWait();
      viewHandler.openView(SceneNames.Admin);
    }
    else if(temp.equals(DatabaseConnection.MANDATORY))
    {
      Alert mandatory = new Alert(Alert.AlertType.ERROR);
      mandatory.setHeaderText("Error");
      mandatory.setHeaderText("Mandatory fields can not be empty");
      mandatory.showAndWait();
    }
    else
    {
      Alert error = new Alert(Alert.AlertType.ERROR);
      error.setHeaderText("Error");
      error.setHeaderText("You cannot edit this employee's information right now");
      error.showAndWait();
    }
  }

  @FXML void delete() throws RemoteException
  {
    Alert alert = new Alert(Alert.AlertType.WARNING,
        "Do you really want to delete this employee from the system?",
        ButtonType.NO, ButtonType.YES);
    alert.setTitle("Warning");
    alert.setHeaderText(null);
    alert.showAndWait();
    if (alert.getResult() == ButtonType.YES)
    {
      if (viewModel.delete().equals(DatabaseConnection.SUCCESS))
      {
        Alert success = new Alert(Alert.AlertType.INFORMATION);
        success.setHeaderText("Success");
        success.setHeaderText("The employee has been successfully removed");
        success.showAndWait();
      }
      else
      {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setHeaderText("Error");
        error.setHeaderText("You cannot delete this employee right now");
        error.showAndWait();
      }
      viewHandler.openView(SceneNames.Admin);
    }
  }
  @FXML void cancel(){
    viewHandler.openView(SceneNames.Admin);
  }
}
