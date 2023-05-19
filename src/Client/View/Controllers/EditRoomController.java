package Client.View.Controllers;

import Client.View.SceneNames;
import Client.View.ViewHandler;
import Client.ViewModel.EditRoomViewModel;
import Server.Utility.DataBase.DatabaseConnection;
import Server.Utility.DataBase.Room.RoomData;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;

public class EditRoomController
{
  @FXML TextField roomNo, size, beds;
  @FXML ChoiceBox<String> orientation;
  @FXML ChoiceBox<Integer> price;
  @FXML CheckBox internet, balcony, kitchen, bathroom;
  private Region root;
  private ViewHandler viewHandler;
  private EditRoomViewModel viewModel;

  public void init(ViewHandler viewHandler, EditRoomViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;

    viewModel.bindNoBeds(beds.textProperty());
    viewModel.bindBalcony(balcony.selectedProperty());
    viewModel.bindBathroom(bathroom.selectedProperty());
    viewModel.bindInternet(internet.selectedProperty());
    viewModel.bindKitchen(kitchen.selectedProperty());
    viewModel.bindPrice(price.valueProperty());
    viewModel.bindRoomNo(roomNo.textProperty());

    viewModel.bindSize(size.textProperty());

    viewModel.bindOrientation(orientation.valueProperty());
    roomNo.setDisable(true);
  }

  public void initialize()
  {
    orientation.getItems().add("North");
    orientation.getItems().add("West");
    orientation.getItems().add("South");
    orientation.getItems().add("East");

    price.getItems().add(200);
    price.getItems().add(270);
    price.getItems().add(300);
    price.getItems().add(500);
  }

  public Region getRoot()
  {
    root.setUserData("Edit Room");
    return root;
  }

  public void reset()
  {
    viewModel.fill();
  }

  @FXML void Save() throws RemoteException
  {
    if (viewModel.edit())
      viewHandler.openView(SceneNames.EmployeeHomeRoom);
  }

  @FXML void Cancel()
  {
    viewHandler.openView(SceneNames.EmployeeHomeRoom);
  }

  @FXML void delete() throws RemoteException
  {
    Alert alert = new Alert(Alert.AlertType.WARNING,
        "Do you really want to delete this room from the system?",
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
        success.setHeaderText("The room has been successfully removed");
        success.showAndWait();
      }
      else
      {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setHeaderText("Error");
        error.setHeaderText("You cannot delete this room right now");
        error.showAndWait();
      }
      viewHandler.openView(SceneNames.EmployeeHomeRoom);
    }
  }
}
