package Client.ViewModel.Employee;

import Client.Model.Model;
import Client.Model.ModelEmployeeSide;
import Client.Utility.Alerts;
import Server.Model.Hotel.Users.Employee;
import Server.Model.Hotel.Users.Person;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class EmployeeLoginViewModel
{
  private ModelEmployeeSide model;
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
      Alerts x = new Alerts(Alert.AlertType.ERROR,"Database Error","Contact the developers of the system\nPhone number: +45 8755 4243\nPhone number: +45 8755 4222");
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

  public Alerts logIn()
  {
    if (username.getValue().equals("admin") && password.getValue().equals("admin"))
    {
      return new Alerts(Alert.AlertType.NONE, "", "");
    }
    else
    {
      for (int i = 0; i < employees.size(); i++)
      {
        if (employees.get(i).getUsername().equals(username.getValue()))
        {
          if (employees.get(i).getPassword().equals(password.getValue()))
          {
            user = employees.get(i);
            try
            {
              model.logIn(user);
              return new Alerts(Alert.AlertType.NONE, "", "");
            }
            catch (Exception e)
            {
              if (e.getMessage().contains("already"))
              {
                return new Alerts(Alert.AlertType.ERROR, "Error",
                    username.getValue() + " is already logged in");
              }
              else
              {
                return new Alerts(Alert.AlertType.ERROR, "Error",
                    "User: " + username.getValue() + " does not exist");
              }
            }
          }
          else
          {
            return new Alerts(Alert.AlertType.ERROR, "Error", "Invalid password");
          }
        }
      }
    }
    return new Alerts(Alert.AlertType.ERROR, "Error","User: "+username.getValue()+" does not exist");
  }
}