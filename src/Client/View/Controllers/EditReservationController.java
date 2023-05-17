package Client.View.Controllers;

import Client.View.SceneNames;
import Client.View.ViewHandler;
import Client.ViewModel.CustomerHomeViewModel;
import Client.ViewModel.EditReservationViewModel;
import Server.Utility.DataBase.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;

public class EditReservationController
{

  @FXML TextField username,roomNo;
  @FXML DatePicker fromDate,toDate;
  private Region root;
  private ViewHandler viewHandler;
  private EditReservationViewModel viewModel;
  public void init(ViewHandler viewHandler, EditReservationViewModel viewModel, Region root){
    this.viewHandler=viewHandler;
    this.viewModel=viewModel;
    viewModel.bindRoomNo(roomNo.textProperty());
    viewModel.bindFromDate(fromDate.valueProperty());
    viewModel.bindToDate(toDate.valueProperty());
    viewModel.bindUsername(username.textProperty());
    this.root=root;

  }

  public Region getRoot(){
    root.setUserData("Edit Reservation");
    return root;
  }

  public void reset(){
    viewModel.fill();
  }

  @FXML void cancel(){
    viewHandler.openView(SceneNames.EmployeeHomeReservations);
  }

  @FXML void save() throws RemoteException
  {
    if (viewModel.save().equals(DatabaseConnection.SUCCESS)){
      Alert alert=new Alert(Alert.AlertType.INFORMATION,"Edit Reservation was successful", ButtonType.OK);
      alert.setTitle("Success");
      alert.setHeaderText(null);
      alert.showAndWait();
      viewHandler.openView(SceneNames.EmployeeHomeReservations);
    }
    if (viewModel.save().equals(DatabaseConnection.ERROR)){
      Alert alert=new Alert(Alert.AlertType.ERROR,"Edit Reservation has caused an error", ButtonType.OK);
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.showAndWait();
    }
  }
}
