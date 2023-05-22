package Client.View;

import Client.View.Controllers.*;
import Client.ViewModel.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;

import java.io.IOError;
import java.io.IOException;

public class ViewFactory
{

  private ViewHandler viewHandler;
  private HomeController homeController;
  private EmployeeLoginController employeeLoginController;
  private EmployeeHomeController employeeHomeController;
  private CustomerHomeController customerHomeController;
  private AddRoomController addRoomController;
  private RegistrateEmployeeController registrateEmployeeController;
  private EditRoomController editRoomController;
  private EditCustomerController editCustomerController;
  private EditEmployeeController editEmployeeController;
  private EditReservationController editReservationController;
  private AddCustomerController addCustomerController;

  private ViewModelFactory viewModelFactory;


  public ViewFactory(ViewHandler viewHandler, ViewModelFactory viewModelFactory){
    this.viewHandler=viewHandler;
    this.viewModelFactory=viewModelFactory;
  }

  private Region loadHomeView(){
    FXMLLoader loader=new FXMLLoader();
    loader.setLocation(getClass().getResource("Scenes/Home.fxml"));
    try
    {
      Region root = loader.load();
      homeController=loader.getController();
      homeController.init(viewHandler,viewModelFactory.getHomeViewModel(),root);
    }
    catch(IOException e){
      throw new IOError(e);
    }
    homeController.reset();
    return homeController.getRoot();
  }


  private Region loadEmployeeLogin(){
    FXMLLoader loader=new FXMLLoader();
    loader.setLocation(getClass().getResource("Scenes/EmployeeLogin.fxml"));
    try
    {
      Region root = loader.load();
      employeeLoginController=loader.getController();
      employeeLoginController.init(viewHandler,viewModelFactory.getEmployeeLoginViewModel(),root);
    }
    catch(IOException e){
      throw new IOError(e);
    }
    employeeLoginController.reset();
    return employeeLoginController.getRoot();
  }

  private Region loadRegisterEmployee(){
    FXMLLoader loader=new FXMLLoader();
    loader.setLocation(getClass().getResource("Scenes/RegisterEmployee.fxml"));
    try
    {
      Region root = loader.load();
      registrateEmployeeController = loader.getController();
      registrateEmployeeController.init(viewHandler,viewModelFactory.getRegisterEmployeeViewModel(),root);
    }
    catch (IOException e){
      throw new IOError(e);
    }

    registrateEmployeeController.reset();
      return registrateEmployeeController.getRoot();
    }

  private Region loadEmployeeHome(int i){
    FXMLLoader loader=new FXMLLoader();
    loader.setLocation(getClass().getResource("Scenes/EmployeeHome.fxml"));
    try
    {
      Region root = loader.load();
      employeeHomeController=loader.getController();
      employeeHomeController.init(viewHandler,viewModelFactory.getEmployeeHomeViewModel(),root);
    }
    catch(IOException e){
      throw new IOError(e);
    }
    employeeHomeController.reset();
    employeeHomeController.selection(i);
    return employeeHomeController.getRoot();
  }

  private Region loadCustomerHome(){
    FXMLLoader loader=new FXMLLoader();
    loader.setLocation(getClass().getResource("Scenes/CustomerHome.fxml"));
    try
    {
      Region root = loader.load();
      customerHomeController=loader.getController();
      customerHomeController.init(viewHandler,viewModelFactory.getCustomerHomeViewModel(),root);
    }
    catch(IOException e){
      throw new IOError(e);
    }
    customerHomeController.reset();
    return customerHomeController.getRoot();
  }

  private Region loadAddRoom(){
    FXMLLoader loader=new FXMLLoader();
    loader.setLocation(getClass().getResource("Scenes/AddRoom.fxml"));
    try
    {
      Region root = loader.load();
      addRoomController=loader.getController();
      addRoomController.init(viewHandler,viewModelFactory.getAddRoomViewModel(),root);
    }
    catch(IOException e){
      throw new IOError(e);
    }
    addRoomController.reset();
    return addRoomController.getRoot();
  }

  private Region loadEditRoom(){
    FXMLLoader loader=new FXMLLoader();
    loader.setLocation(getClass().getResource("Scenes/EditRoom.fxml"));
    try
    {
      Region root = loader.load();
      editRoomController=loader.getController();
      editRoomController.init(viewHandler,viewModelFactory.getEditRoomViewModel(),root);
    }
    catch(IOException e){
      throw new IOError(e);
    }
    editRoomController.reset();
    return editRoomController.getRoot();
  }

  private Region loadEditCustomer(){
    FXMLLoader loader=new FXMLLoader();
    loader.setLocation(getClass().getResource("Scenes/EditCustomer.fxml"));
    try
    {
      Region root = loader.load();
      editCustomerController=loader.getController();
      editCustomerController.init(viewHandler,viewModelFactory.getEditCustomerViewModel(),root);
    }
    catch(IOException e){
      throw new IOError(e);
    }
    editCustomerController.reset();
    return editCustomerController.getRoot();
  }

  private Region loadEditEmployee(){
    FXMLLoader loader=new FXMLLoader();
    loader.setLocation(getClass().getResource("Scenes/EditEmployees.fxml"));
    try
    {
      Region root = loader.load();
      editEmployeeController=loader.getController();
      editEmployeeController.init(viewHandler,viewModelFactory.getEditEmployeeViewModel(),root);
    }
    catch(IOException e){
      throw new IOError(e);
    }
    editEmployeeController.reset();
    return editEmployeeController.getRoot();
  }

  private Region loadEditReservation(){
    FXMLLoader loader=new FXMLLoader();
    loader.setLocation(getClass().getResource("Scenes/EditReservation.fxml"));
    try
    {
      Region root = loader.load();
      editReservationController=loader.getController();
      editReservationController.init(viewHandler,viewModelFactory.getEditReservationViewModel(),root);
    }
    catch(IOException e){
      throw new IOError(e);
    }
    editReservationController.reset();
    return editReservationController.getRoot();
  }

  private Region loadAddCustomer()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("Scenes/AddCustomer.fxml"));

    try
    {
      Region root = loader.load();
      addCustomerController = loader.getController();
      addCustomerController.init(viewHandler, viewModelFactory.getAddCustomerViewModel(), root);
    }
    catch (IOException e)
    {
      throw new IOError(e);
    }
    addCustomerController.reset();
    return addCustomerController.getRoot();

  }


  public Region load(SceneNames id){
    return switch (id){
      case Home -> loadHomeView();
      case EmployeeLogin -> loadEmployeeLogin();
      case EmployeeHomeReservations -> loadEmployeeHome(0);
      case EmployeeHomeAddReservations -> loadEmployeeHome(1);
      case EmployeeHomeEmployee -> loadEmployeeHome(2);
      case EmployeeHomeCustomer -> loadEmployeeHome(3);
      case EmployeeHomeRoom -> loadEmployeeHome(4);
      case CustomerHome -> loadCustomerHome();
      case AddRoom -> loadAddRoom();
      case EditRoom -> loadEditRoom();
      case EmployeeSignIn -> loadRegisterEmployee();
      case EditCustomer -> loadEditCustomer();
      case EditEmployee -> loadEditEmployee();
      case EditReservation -> loadEditReservation();
      case AddCustomer -> loadAddCustomer();
    };
  }
}
