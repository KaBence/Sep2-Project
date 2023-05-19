package Client.ViewModel;

import Client.Model.Model;
import Client.View.Controllers.EmployeeLoginController;
import Server.Model.Hotel.Users.Employee;
import Server.Model.Hotel.Users.Person;
import Server.Utility.DataBase.DatabaseConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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
      System.out.println(e.detail);
      //throw new RuntimeException(e);
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
            System.out.println(e.getMessage());
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
