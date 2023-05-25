package Client.View.Controllers.Employee;

import Client.Utility.Alerts;
import Client.View.Scenes.SceneNames;
import Client.View.ViewHandler;
import Client.ViewModel.Employee.EditReservationViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

  public Region getRoot()
  {
    root.setUserData("Edit Reservation");
    return root;
  }

  public void reset(){
    viewModel.fill();
  }

  @FXML void cancel()
  {
    viewHandler.openView(SceneNames.EmployeeHomeReservations);
  }

  @FXML void save()
  {
    Alerts x = viewModel.save();
    x.showAndWait();
    if (x.getAlertType().equals(Alert.AlertType.INFORMATION))
    {
      viewHandler.openView(SceneNames.EmployeeHomeReservations);
    }
  }
}
