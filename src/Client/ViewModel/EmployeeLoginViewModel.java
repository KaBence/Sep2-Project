package Client.ViewModel;

import Client.Model.Model;
import Client.View.Controllers.EmployeeLoginController;
import Server.Model.Hotel.Users.Employee;
import Server.Model.Hotel.Users.Person;
import Server.Utility.DataBase.DatabaseConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class EmployeeLoginViewModel
{
  private Model model;
  private Person user;
  private ArrayList<Employee> employees;
  private SimpleStringProperty username;
  private SimpleStringProperty password;

  public EmployeeLoginViewModel(Model model)
  {
    this.model = model;
    this.username = new SimpleStringProperty();
    this.password = new SimpleStringProperty();
    try
    {
      this.employees = model.getAllEmployees();
    }
    catch (RemoteException e)
    {
      Alert x = new Alert(Alert.AlertType.ERROR);
      x.setHeaderText("The system doesn't have any employees.\nContact the developers of the system\nPhone number: +45 8755 4243\nPhone number: +45 8755 4222");
      x.showAndWait();
    }
  }

  public void bindUsername(StringProperty property)
  {
    property.bindBidirectional(username);
  }

  public void bindPassword(StringProperty property)
  {
    property.bindBidirectional(password);
  }

  public Person getLoggedUser()
  {
    return user;
  }
  public String logIn(String username, String password) throws RemoteException
  {
    if (username.equals("admin")&&password.equals("admin"))
      return "admin";
    for (int i = 0; i < employees.size(); i++)
    {
      if (employees.get(i).getUsername().equals(username))
      {
        if (employees.get(i).getPassword().equals(password))
        {
          user = employees.get(i);
          try
          {
            model.logIn(user);
            return DatabaseConnection.SUCCESS;
          }
          catch (Exception e)
          {
            return DatabaseConnection.ERROR;
          }
        }
        else
        {
          return DatabaseConnection.PASSWORD;
        }
      }
    }
    return DatabaseConnection.USER;
  }
}
