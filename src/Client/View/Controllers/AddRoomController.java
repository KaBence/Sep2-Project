package Client.View.Controllers;

import Client.View.SceneNames;
import Client.View.ViewHandler;
import Client.ViewModel.AddRoomViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class AddRoomController
{

  @FXML TextField roomNo, size, beds;
  @FXML ChoiceBox<String> orientation;
  @FXML ChoiceBox<Integer> price;
  @FXML CheckBox internet, balcony, kitchen, bathroom;
  private Region root;
  private ViewHandler viewHandler;
  private AddRoomViewModel viewModel;

  public void init(ViewHandler viewHandler, AddRoomViewModel viewModel,
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
    root.setUserData("Add a room to the system");
    return root;
  }

  public void reset()
  {
    roomNo.clear();
    beds.clear();
    size.clear();
    orientation.setValue(null);
    price.setValue(null);
    internet.setSelected(false);
    balcony.setSelected(false);
    kitchen.setSelected(false);
    bathroom.setSelected(false);
  }

  @FXML void Add() throws RemoteException
  {
    boolean flag = true;
    if (viewModel.addRoom() == null)
    {
      Alert wrong = new Alert(Alert.AlertType.ERROR);
      wrong.setTitle("Invalid data");
      wrong.setHeaderText("The room with room number "+roomNo.getText()+" already exist");
      wrong.showAndWait();
      viewHandler.openView(SceneNames.EmployeeHomeRoom);
    }
    else
    {
      try
      {
        viewModel.addRoom();
      }
      catch (RuntimeException e)
      {
        Alert empty = new Alert(Alert.AlertType.WARNING);
        empty.setTitle("Invalid data");
        empty.setHeaderText(
            "You need to fill up mandatory fields: \nRoom number, Number of beds and Size of the room as a number,\nOrientation and Price per night");
        empty.showAndWait();
        flag = false;
      }
      if (flag)
      {
        Alert success = new Alert(Alert.AlertType.INFORMATION);
        success.setTitle("Success");
        success.setHeaderText(
            "The room has been successfully added to the system");
        success.showAndWait();
        viewHandler.openView(SceneNames.EmployeeHomeRoom);
      }
    }
  }

  @FXML void Cancel()
  {
    viewHandler.openView(SceneNames.EmployeeHomeRoom);
  }

}
