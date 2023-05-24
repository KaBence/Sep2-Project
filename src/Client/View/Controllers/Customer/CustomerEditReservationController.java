package Client.View.Controllers.Customer;

import Client.View.Scenes.SceneNames;
import Client.View.ViewHandler;
import Client.ViewModel.Customer.CustomerEditReservationViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;

public class CustomerEditReservationController
{

  @FXML TextField username, roomNo;
  @FXML DatePicker fromDate, toDate;
  private Region root;
  private ViewHandler viewHandler;
  private CustomerEditReservationViewModel viewModel;

  public void init(ViewHandler viewHandler,
      CustomerEditReservationViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.viewModel.bindRoomNo(roomNo.textProperty());
    this.viewModel.bindFromDate(fromDate.valueProperty());
    this.viewModel.bindToDate(toDate.valueProperty());
    this.viewModel.bindUsername(username.textProperty());
    this.viewModel.previousScene(false);
    this.root = root;
  }

  public Region getRoot()
  {
    root.setUserData("Edit Reservation");
    return root;
  }

  public void reset()
  {
    viewModel.fill();
  }

  @FXML void cancel()
  {
    viewHandler.openView(SceneNames.CustomerHomeNewReservations);
  }

  @FXML void save() throws RemoteException
  {
    viewModel.save().showAndWait();
    viewHandler.openView(SceneNames.CustomerHome);
  }
}
