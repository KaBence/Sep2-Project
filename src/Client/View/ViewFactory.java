package Client.View;

import Client.View.Controllers.*;
import Client.ViewModel.EditRoomViewModel;
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
  private EditRoomController editRoomController;

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

  private Region loadEmployeeHome(){
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


  public Region load(SceneNames id){
    return switch (id){
      case Home -> loadHomeView();
      case EmployeeLogin -> loadEmployeeLogin();
      case EmployeeHome -> loadEmployeeHome();
      case CustomerHome -> loadCustomerHome();
      case AddRoom -> loadAddRoom();
      case EditRoom -> loadEditRoom();
      default -> throw new IllegalArgumentException("Unknown id");
    };
  }
}
