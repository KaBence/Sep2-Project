package Client.View;

import Client.View.Controllers.Customer.*;
import Client.View.Controllers.Employee.*;
import Client.View.Controllers.Shared.HomeController;
import Client.View.Scenes.SceneNames;
import Client.ViewModel.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;

import java.io.IOError;
import java.io.IOException;

public class ViewFactory
{

  private HomeController homeController;
  private EmployeeLoginController employeeLoginController;
  private EmployeeHomeController employeeHomeController;
  private CustomerHomeController customerHomeController;
  private AddRoomController addRoomController;
  private EditRoomController editRoomController;
  private EditCustomerController editCustomerController;
  private EditEmployeeController editEmployeeController;
  private EditReservationController editReservationController;
  private AdminController adminController;
  private AddCustomerController addCustomerController;
  private CustomerEditReservationController customerEditReservationController;

  private AddReviewController addReviewController;

  private ViewHandler viewHandler;
  private ViewModelFactory viewModelFactory;

  public ViewFactory(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    this.viewModelFactory = viewModelFactory;
  }

  private Region loadHomeView()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("Scenes/Shared/Home.fxml"));
    try
    {
      Region root = loader.load();
      homeController = loader.getController();
      homeController.init(viewHandler, viewModelFactory.getHomeViewModel(),
          root);
    }
    catch (IOException e)
    {
      throw new IOError(e);
    }
    homeController.reset();
    return homeController.getRoot();
  }

  private Region loadEmployeeLogin()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("Scenes/Employee/EmployeeLogin.fxml"));
    try
    {
      Region root = loader.load();
      employeeLoginController = loader.getController();
      employeeLoginController.init(viewHandler,
          viewModelFactory.getEmployeeLoginViewModel(), root);
    }
    catch (IOException e)
    {
      throw new IOError(e);
    }
    employeeLoginController.reset();
    return employeeLoginController.getRoot();
  }

  private Region loadEmployeeHome(int i)
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("Scenes/Employee/EmployeeHome.fxml"));
    try
    {
      Region root = loader.load();
      employeeHomeController = loader.getController();
      employeeHomeController.init(viewHandler,
          viewModelFactory.getEmployeeHomeViewModel(), root);
    }
    catch (IOException e)
    {
      throw new IOError(e);
    }
    employeeHomeController.reset();
    employeeHomeController.selection(i);
    return employeeHomeController.getRoot();
  }

  private Region loadCustomerHome(int i)
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("Scenes/Customer/CustomerHome.fxml"));
    try
    {
      Region root = loader.load();
      customerHomeController = loader.getController();
      customerHomeController.init(viewHandler,
          viewModelFactory.getCustomerHomeViewModel(), root);
    }
    catch (IOException e)
    {
      throw new IOError(e);
    }
    customerHomeController.reset();
    customerHomeController.selection(i);
    return customerHomeController.getRoot();
  }

  private Region loadAddRoom()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("Scenes/Employee/AddRoom.fxml"));
    try
    {
      Region root = loader.load();
      addRoomController = loader.getController();
      addRoomController.init(viewHandler,
          viewModelFactory.getAddRoomViewModel(), root);
    }
    catch (IOException e)
    {
      throw new IOError(e);
    }
    addRoomController.reset();
    return addRoomController.getRoot();
  }

  private Region loadEditRoom()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("Scenes/Employee/EditRoom.fxml"));
    try
    {
      Region root = loader.load();
      editRoomController = loader.getController();
      editRoomController.init(viewHandler,
          viewModelFactory.getEditRoomViewModel(), root);
    }
    catch (IOException e)
    {
      throw new IOError(e);
    }
    editRoomController.reset();
    return editRoomController.getRoot();
  }

  private Region loadEditCustomer()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("Scenes/Employee/EditCustomer.fxml"));
    try
    {
      Region root = loader.load();
      editCustomerController = loader.getController();
      editCustomerController.init(viewHandler,
          viewModelFactory.getEditCustomerViewModel(), root);
    }
    catch (IOException e)
    {
      throw new IOError(e);
    }
    editCustomerController.reset();
    return editCustomerController.getRoot();
  }

  private Region loadEditEmployee()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("Scenes/Employee/EditEmployees.fxml"));
    try
    {
      Region root = loader.load();
      editEmployeeController = loader.getController();
      editEmployeeController.init(viewHandler,
          viewModelFactory.getEditEmployeeViewModel(), root);
    }
    catch (IOException e)
    {
      throw new IOError(e);
    }
    editEmployeeController.reset();
    return editEmployeeController.getRoot();
  }

  private Region loadEditReservation()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("Scenes/Employee/EditReservation.fxml"));
    try
    {
      Region root = loader.load();
      editReservationController = loader.getController();
      editReservationController.init(viewHandler,
          viewModelFactory.getEditReservationViewModel(), root);
    }
    catch (IOException e)
    {
      throw new IOError(e);
    }
    editReservationController.reset();
    return editReservationController.getRoot();
  }

  private Region loadReview()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("Scenes/Customer/AddReview.fxml"));
    try
    {
      Region root = loader.load();
      addReviewController = loader.getController();
      addReviewController.init(viewHandler, viewModelFactory.getAddReviewViewModel(), root);
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
    addReviewController.reset();
    return addReviewController.getRoot();
  }

  private Region loadAddCustomer()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("Scenes/Customer/AddCustomer.fxml"));

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

  private Region loadAdminView(int i){
    FXMLLoader loader=new FXMLLoader();
    loader.setLocation(getClass().getResource("Scenes/Employee/Admin.fxml"));
    try
    {
      Region root = loader.load();
      adminController=loader.getController();
      adminController.init(viewHandler,viewModelFactory.getAdminViewModel(),root);
    }
    catch(IOException e){
      throw new IOError(e);
    }
    adminController.reset();
    adminController.selection(i);
    return adminController.getRoot();
  }

  private Region loadCustomerEditReservation()
  {
    FXMLLoader loader=new FXMLLoader();
    loader.setLocation(getClass().getResource("Scenes/Customer/CustomerEditReservation.fxml"));
    try
    {
      Region root = loader.load();
      customerEditReservationController= loader.getController();
      customerEditReservationController.init(viewHandler,viewModelFactory.getCustomerEditReservationViewModel(),root);
    }
    catch(IOException e){
      throw new IOError(e);
    }
    customerEditReservationController.reset();
    return customerEditReservationController.getRoot();
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
      case CustomerHome -> loadCustomerHome(0);
      case CustomerHomeNewReservations -> loadCustomerHome(1);
      case AddRoom -> loadAddRoom();
      case EditRoom -> loadEditRoom();
      case EditCustomer -> loadEditCustomer();
      case EditEmployee -> loadEditEmployee();
      case EditReservation -> loadEditReservation();
      case AdminAddEmployee -> loadAdminView(0);
      case AdminAllEmployees -> loadAdminView(1);
      case AddCustomer -> loadAddCustomer();
      case Review -> loadReview();
      case CustomerEditReservation -> loadCustomerEditReservation();
    };
  }
}
