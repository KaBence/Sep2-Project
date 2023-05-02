package Client.View;

import Client.View.Controllers.CustomerHomeController;
import Client.View.Controllers.EmployeeHomeController;
import Client.View.Controllers.EmployeeLoginController;
import Client.View.Controllers.HomeController;
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


  public Region load(SceneNames id){
    return switch (id){
      case Home -> loadHomeView();
      case EmployeeLogin -> loadEmployeeLogin();
      case EmployeeHome -> loadEmployeeHome();
      case CustomerHome -> loadCustomerHome();
      default -> throw new IllegalArgumentException("Unknown id");
    };
  }
}
