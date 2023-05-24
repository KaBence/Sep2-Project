package Client.View.Controllers.Employee;

import Client.View.Scenes.SceneNames;
import Client.View.ViewHandler;
import Client.ViewModel.Employee.EditRoomViewModel;
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
    if (viewModel.delete())
      viewHandler.openView(SceneNames.EmployeeHomeRoom);
  }
}
