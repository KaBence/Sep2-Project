package Client.View.Controllers;

import Client.View.SceneNames;
import Client.View.ViewHandler;
import Client.ViewModel.EmployeeHomeViewModel;
import Client.ViewModel.HomeViewModel;
import Server.Model.Customer;
import Server.Model.Employee;
import Server.Model.Room;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;

public class EmployeeHomeController
{
  @FXML Button checkIn;
  @FXML Button checkOut;
  @FXML Tab room;
  @FXML Tab employeeInfo;
  @FXML Tab customerInfo;
  @FXML Tab reservation;
  @FXML TabPane tabPane;

  @FXML ListView<Employee> employeeListView;
  @FXML ListView<Customer> customerListView;
  @FXML ListView<Room> roomListView;

  @FXML CheckBox bathroomFilter,kitchenFilter,balconyFilter,internetFilter;

  @FXML ComboBox<Integer> priceFilter;
  @FXML TextField roomNoFilter,bedsFilter;


  private Region root;
  private ViewHandler viewHandler;
  private EmployeeHomeViewModel viewModel;

  public void init(ViewHandler viewHandler, EmployeeHomeViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.viewModel.bindRoomList(roomListView.itemsProperty());
    this.viewModel.bindCustomerList(customerListView.itemsProperty());
    this.viewModel.bindEmployeeList(employeeListView.itemsProperty());
    this.root = root;

    viewModel.bindInternet(internetFilter.selectedProperty());
    viewModel.bindRoomNo(roomNoFilter.textProperty());
    viewModel.bindBeds(bedsFilter.textProperty());
    viewModel.bindBalcony(balconyFilter.selectedProperty());
    viewModel.bindBathroom(bathroomFilter.selectedProperty());
    viewModel.bindKitchen(kitchenFilter.selectedProperty());
    viewModel.bindPrice(priceFilter.valueProperty());

  }


  public Region getRoot()
  {
    root.setUserData("Employee Home Page");
    return root;
  }

  public void reset()
  {
    viewModel.update();
  }

  @FXML void addRoom()
  {
    viewHandler.openView(SceneNames.AddRoom);
  }

  @FXML void editRoom(){
    if (roomListView.getSelectionModel().getSelectedItem()==null){
      Alert alert=new Alert(Alert.AlertType.ERROR,"Select a room first",ButtonType.OK);
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.showAndWait();
      return;
    }
    viewHandler.openView(SceneNames.EditRoom);
  }

  @FXML void deleteRoom() throws RemoteException
  {
    if (roomListView.getSelectionModel().getSelectedItem()==null){
      Alert alert=new Alert(Alert.AlertType.ERROR,"Select a room first",ButtonType.OK);
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.showAndWait();
      return;
    }
    Alert alert=new Alert(Alert.AlertType.WARNING,"Do you really want to delete this room?",ButtonType.NO,ButtonType.YES);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.showAndWait();
    if (alert.getResult()==ButtonType.YES)
      if (alert.getResult() == ButtonType.YES)
      {
        if (viewModel.deleteRoom(roomListView.getSelectionModel().getSelectedItem()).equals("success"))
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

  @FXML void Home()
  {
    viewHandler.openView(SceneNames.Home);
  }

  @FXML void tableClickRoom(MouseEvent event)
  {
    viewModel.saveRoom(roomListView.getSelectionModel().getSelectedItem());
    if (event.getClickCount() == 2)
      viewHandler.openView(SceneNames.EditRoom);
  }

  @FXML void tableClickCustomer(MouseEvent event){
    viewModel.saveCustomer(customerListView.getSelectionModel().getSelectedItem());
    if (event.getClickCount()==2)
      viewHandler.openView(SceneNames.EditCustomer);
  }

  @FXML void tableClickEmployee(MouseEvent event){
    viewModel.saveCustomer(customerListView.getSelectionModel().getSelectedItem());
    if (event.getClickCount()==2)
      viewHandler.openView(SceneNames.EditEmployee);
  }

  @FXML void filterRoom() throws RemoteException
  {
    viewModel.filterRoom();
  }


  @FXML void checkIn(){

  }
  @FXML void checkOut(){

  }
  @FXML void back(){

  }
  @FXML void edit(){

  }

  public SingleSelectionModel<Tab> selection(int i)
  {
    SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
    selectionModel.select(i);
    return selectionModel;
  }
}
