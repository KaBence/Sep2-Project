package Client.View.Controllers;

import Client.View.SceneNames;
import Client.View.ViewHandler;
import Client.ViewModel.EmployeeHomeViewModel;
import Client.ViewModel.HomeViewModel;
import Server.Model.Customer;
import Server.Model.Employee;
import Server.Model.Room;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

public class EmployeeHomeController
{
  @FXML Button checkIn;
  @FXML Button chckOut;
  @FXML Tab room;
  @FXML Tab employeeInfo;
  @FXML Tab customerinfo;
  @FXML Tab reservation;

  @FXML TableView<Employee> tableViewEmployee;
  @FXML TableColumn<Employee, Integer> employeeId;
  @FXML TableColumn<Employee, String> employeeFirstName;
  @FXML TableColumn<Employee, String> employeeLastName;
  @FXML TableColumn<Employee, String> employeePosition;
  @FXML TableColumn<Employee, String> employeePhoneNumber;

  @FXML TableView<Customer> tableViewCustomer;
  @FXML TableColumn<Customer, String> CustomerEmail;
  @FXML TableColumn<Customer, String> CustomerFirstName;
  @FXML TableColumn<Customer, String> CustomerLastName;
  @FXML TableColumn<Customer, String> CustomerPhoneNumber;
  @FXML TableColumn<Customer, String> CustomerPayment;

  @FXML TableView<Room> TableViewReservation;
  @FXML TableColumn<Room, Integer> TableViewRoomNo;
  @FXML TableColumn<Room, String> TableViewReservationCustomer;
  @FXML TableColumn<Room, String> TableViewFromDate;
  @FXML TableColumn<Room, String> TableViewToDate;
  @FXML TableView<Room> tableView;
  @FXML TableColumn<Room, Integer> tableRoomNo;
  @FXML TableColumn<Room, Integer> tableBeds;
  @FXML TableColumn<Room, Integer> tableSize;
  @FXML TableColumn<Room, String> tableOri;
  private Region root;
  private ViewHandler viewHandler;
  private EmployeeHomeViewModel viewModel;

  public void init(ViewHandler viewHandler, EmployeeHomeViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.viewModel.bindRoomList(tableView.itemsProperty());
    this.root = root;

  }

  public void initialize()
  {
    tableOri.setCellValueFactory(
        new PropertyValueFactory<Room, String>("orientation"));
    tableBeds.setCellValueFactory(
        new PropertyValueFactory<Room, Integer>("noOfBeds"));
    tableSize.setCellValueFactory(
        new PropertyValueFactory<Room, Integer>("size"));
    tableRoomNo.setCellValueFactory(
        new PropertyValueFactory<Room, Integer>("RoomNo"));
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

  @FXML void Home()
  {
    viewHandler.openView(SceneNames.Home);
  }

  @FXML void tableClick(MouseEvent event)
  {
    viewModel.saveRoom(tableView.getSelectionModel().getSelectedItem());
    if (event.getClickCount() == 2)
      viewHandler.openView(SceneNames.EditRoom);
  }
  @FXML void checkIn(){

  }
  @FXML void checkOut(){

  }
  @FXML void back(){

  }
  @FXML void edit(){

  }
}
