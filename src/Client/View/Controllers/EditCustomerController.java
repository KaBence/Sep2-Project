package Client.View.Controllers;

import Client.View.SceneNames;
import Client.View.ViewHandler;
import Client.ViewModel.EditCustomerViewModel;
import Server.Utility.DataBase.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;

public class EditCustomerController
{
  private Region root;
  private ViewHandler viewHandler;
  private EditCustomerViewModel viewModel;

  @FXML TextField username, phoneNo, lastName, firstName, payment;

  public void init(ViewHandler viewHandler, EditCustomerViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;

    this.viewModel.bindPayment(payment.textProperty());
    this.viewModel.bindFirstName(firstName.textProperty());
    this.viewModel.bindLastName(lastName.textProperty());
    this.viewModel.bindPhoneNo(phoneNo.textProperty());
    this.viewModel.bindUsername(username.textProperty());
    username.setEditable(false);
  }

  public Region getRoot()
  {
    root.setUserData("Edit Customer Info");
    return root;
  }

  public void reset()
  {
    viewModel.fill();
  }

  @FXML void save() throws RemoteException
  {
    String x = viewModel.save();
    if (x.equals(DatabaseConnection.SUCCESS))
    {
      Alert alert = new Alert(Alert.AlertType.INFORMATION, "Edit Successful",
          ButtonType.OK);
      alert.setHeaderText(null);
      alert.setTitle("Success");
      alert.showAndWait();
      viewHandler.openView(SceneNames.EmployeeHomeCustomer);
    }
    else if (x.equals(DatabaseConnection.MANDATORY))
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
      error.setHeaderText("You cannot edit this room right now");
      error.showAndWait();
      viewHandler.openView(SceneNames.EmployeeHomeCustomer);
    }
  }

  @FXML void cancel()
  {
    viewHandler.openView(SceneNames.EmployeeHomeCustomer);
  }

  @FXML void delete() throws RemoteException
  {
    Alert alert = new Alert(Alert.AlertType.WARNING,
        "Do you really want to delete this customer from the system?",
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
        success.setHeaderText("The customer has been successfully removed");
        success.showAndWait();
        viewHandler.openView(SceneNames.EmployeeHomeCustomer);
      }
      else
      {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setHeaderText("Error");
        error.setHeaderText("You cannot delete this customer right now");
        error.showAndWait();
      }

    }
  }

}
