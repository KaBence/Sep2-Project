package Client.View.Controllers;

import Client.View.SceneNames;
import Client.View.ViewHandler;
import Client.ViewModel.EmployeeHomeViewModel;
import Server.Model.Customer;
import Server.Model.Employee;
import Server.Model.Room;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;
import java.util.ArrayList;

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

  @FXML CheckBox bathroomFilter, kitchenFilter, balconyFilter, internetFilter;

  @FXML ComboBox<Integer> priceFilter;
  @FXML TextField roomNoFilter, bedsFilter;

  @FXML TextField filteringEmployee;

  @FXML TextField employeeUsernameFilter,employeeFirstNameFilter,employeeLastNameFilter,employeePositionFilter,employeePhoneNumberFilter;
  @FXML TextField filteringCustomer;
  @FXML TextField username, firstName, lastName, phoneNo, paymentInfo;

  @FXML TextField filteringRoom;

  @FXML ToggleButton toggleRoomButton,toggleEmployeeButton,toggleCustomerButton;

  private Region root;
  private ViewHandler viewHandler;
  private EmployeeHomeViewModel viewModel;

  private boolean toggleRoomfilter=false, toggleEmployeefilter = false, toggleCustomerfilter = false;

  public void init(ViewHandler viewHandler, EmployeeHomeViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.viewModel.bindRoomList(roomListView.itemsProperty());
    this.viewModel.bindCustomerList(customerListView.itemsProperty());
    this.viewModel.bindEmployeeList(employeeListView.itemsProperty());
    this.viewModel.bindFilteringRoom(filteringRoom.textProperty());
    this.root = root;

    viewModel.bindInternet(internetFilter.selectedProperty());
    viewModel.bindRoomNo(roomNoFilter.textProperty());
    viewModel.bindBeds(bedsFilter.textProperty());
    viewModel.bindBalcony(balconyFilter.selectedProperty());
    viewModel.bindBathroom(bathroomFilter.selectedProperty());
    viewModel.bindKitchen(kitchenFilter.selectedProperty());
    viewModel.bindPrice(priceFilter.valueProperty());

    viewModel.bindUsername(username.textProperty());
    viewModel.bindFirstName(firstName.textProperty());
    viewModel.bindLastName(lastName.textProperty());
    viewModel.bindPhoneNo(phoneNo.textProperty());
    viewModel.bindPaymentInfo(paymentInfo.textProperty());

    viewModel.bindEmployeeUsername(employeeUsernameFilter.textProperty());
    viewModel.bindEmployeeFirstName(employeeFirstNameFilter.textProperty());
    viewModel.bindEmployeeLastName(employeeLastNameFilter.textProperty());
    viewModel.bindEmployeePosition(employeePositionFilter.textProperty());
    viewModel.bindEmployeePhoneNo(employeePhoneNumberFilter.textProperty());

  }

  public void initialize(){
    ArrayList<Integer> prices=new ArrayList<>();
    prices.add(0);
    prices.add(200);
    prices.add(270);
    prices.add(300);
    prices.add(500);
    priceFilter.setItems(FXCollections.observableList(prices));
  }

  public Region getRoot()
  {
    root.setUserData("Employee Home Page");
    return root;
  }

  public void reset()
  {
    ToggleRoom();
    ToggleCustomer();
    ToggleEmployee();
  }

  @FXML void addRoom()
  {
    viewHandler.openView(SceneNames.AddRoom);
  }

  @FXML void editRoom()
  {
    if (roomListView.getSelectionModel().getSelectedItem() == null)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR, "Select a room first",
          ButtonType.OK);
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.showAndWait();
      return;
    }
    viewHandler.openView(SceneNames.EditRoom);
  }

  @FXML void deleteRoom() throws RemoteException
  {
    if (roomListView.getSelectionModel().getSelectedItem() == null)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR, "Select a room first",
          ButtonType.OK);
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.showAndWait();
      return;
    }
    Alert alert = new Alert(Alert.AlertType.WARNING,
        "Do you really want to delete this room?", ButtonType.NO,
        ButtonType.YES);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.showAndWait();
    if (alert.getResult() == ButtonType.YES)
      if (alert.getResult() == ButtonType.YES)
      {
        if (viewModel.deleteRoom(
                roomListView.getSelectionModel().getSelectedItem())
            .equals("success"))
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

  @FXML void tableClickCustomer(MouseEvent event)
  {
    viewModel.saveCustomer(
        customerListView.getSelectionModel().getSelectedItem());
    if (event.getClickCount() == 2)
      viewHandler.openView(SceneNames.EditCustomer);
  }

  @FXML void tableClickEmployee(MouseEvent event)
  {
    viewModel.saveEmployee(
        employeeListView.getSelectionModel().getSelectedItem());
    if (event.getClickCount() == 2)
    {
      viewHandler.openView(SceneNames.EditEmployee);
    }
  }

  @FXML void filterRoom() throws RemoteException
  {
    viewModel.filterRoom();
  }

  @FXML void onFilter() throws RemoteException
  {
    viewModel.filterFilterCustomer();
  }

  @FXML void checkIn()
  {

  }
  @FXML void filterEmployee() throws RemoteException
  {
    viewModel.filterEmployee();
  }



  @FXML void checkOut()
  {
  }
  @FXML void back()
  {
viewHandler.openView(SceneNames.Home);
  }

  @FXML void edit()
  {
  }
  @FXML void ToggleRoom(){
    viewModel.update();
    if (toggleRoomfilter){
      toggleRoomfilter=false;
      toggleRoomButton.setText("Simple");
      bedsFilter.setDisable(true);
      roomNoFilter.setDisable(true);
      bathroomFilter.setDisable(true);
      priceFilter.setDisable(true);
      internetFilter.setDisable(true);
      bathroomFilter.setDisable(true);
      balconyFilter.setDisable(true);
      kitchenFilter.setDisable(true);
      filteringRoom.setDisable(false);
    }
    else {
      toggleRoomfilter=true;
      toggleRoomButton.setText("Advanced");
      bedsFilter.setDisable(false);
      roomNoFilter.setDisable(false);
      bathroomFilter.setDisable(false);
      priceFilter.setDisable(false);
      internetFilter.setDisable(false);
      bathroomFilter.setDisable(false);
      balconyFilter.setDisable(false);
      kitchenFilter.setDisable(false);
      filteringRoom.setDisable(true);
    }
  }

  @FXML void ToggleEmployee(){
    viewModel.update();
    if (toggleEmployeefilter){
      toggleEmployeefilter=false;
      toggleEmployeeButton.setText("Simple");
      employeeUsernameFilter.setDisable(true);
      employeeFirstNameFilter.setDisable(true);
      employeeLastNameFilter.setDisable(true);
      employeePhoneNumberFilter.setDisable(true);
      employeePositionFilter.setDisable(true);
      filteringEmployee.setDisable(false);
    }
    else {
      toggleEmployeefilter=true;
      toggleEmployeeButton.setText("Advanced");
      employeeUsernameFilter.setDisable(false);
      employeeFirstNameFilter.setDisable(false);
      employeeLastNameFilter.setDisable(false);
      employeePhoneNumberFilter.setDisable(false);
      employeePositionFilter.setDisable(false);
      filteringEmployee.setDisable(true);
    }
  }

  @FXML void ToggleCustomer(){
    viewModel.update();
    if (toggleCustomerfilter){
      toggleCustomerfilter=false;
      toggleCustomerButton.setText("Simple");

      bedsFilter.setDisable(true);
      roomNoFilter.setDisable(true);
      bathroomFilter.setDisable(true);
      priceFilter.setDisable(true);
      internetFilter.setDisable(true);
      bathroomFilter.setDisable(true);
      balconyFilter.setDisable(true);
      kitchenFilter.setDisable(true);
      filteringRoom.setDisable(false);
    }
    else {
      toggleCustomerfilter=true;
      toggleCustomerButton.setText("Advanced");
      bedsFilter.setDisable(false);
      roomNoFilter.setDisable(false);
      bathroomFilter.setDisable(false);
      priceFilter.setDisable(false);
      internetFilter.setDisable(false);
      bathroomFilter.setDisable(false);
      balconyFilter.setDisable(false);
      kitchenFilter.setDisable(false);
      filteringRoom.setDisable(true);
    }
  }

  public SingleSelectionModel<Tab> selection(int i)
  {
    SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
    selectionModel.select(i);
    return selectionModel;
  }


   @FXML void simpleFilterEmployee() throws RemoteException
  {
    String x = filteringEmployee.getText();
    viewModel.simpleFilterEmployee(x);
  }

  @FXML void filterCustomer() throws RemoteException
  {
    String x = filteringCustomer.getText();
    viewModel.filterCustomer(x);
  }

  @FXML void simpleFilterRoom() throws RemoteException
  {
    viewModel.simpleRoomFilter();
  }
}
