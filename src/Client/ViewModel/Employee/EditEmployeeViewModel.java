package Client.ViewModel.Employee;

import Client.Model.Model;
import Client.Model.ModelEmployeeSide;
import Client.Utility.Alerts;
import Server.Model.Hotel.Users.Employee;
import Server.Model.Hotel.Users.States.LogIn;
import Server.Utility.DataBase.DatabaseConnection;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;

import java.rmi.RemoteException;

public class EditEmployeeViewModel
{
  private ModelEmployeeSide model;
  private SimpleStringProperty username, firstName, lastName, phoneNo;
  private SimpleObjectProperty<String> position;

  public EditEmployeeViewModel(Model model)
  {
    this.model = model;
    username = new SimpleStringProperty();
    firstName = new SimpleStringProperty();
    lastName = new SimpleStringProperty();
    position = new SimpleObjectProperty<>();
    phoneNo = new SimpleStringProperty();
  }

  public void fill()
  {
    Employee temp = model.getSelectedEmployee();
    username.set(temp.getUsername());
    firstName.set(temp.getFirstName());
    lastName.set(temp.getLastName());
    phoneNo.set(temp.getPhoneNo());
    position.set(temp.getPosition());

  }

  public Alerts edit()
  {
    try
    {
      String state= model.updateEmployee(username.getValue(),firstName.getValue(), lastName.getValue(), position.getValue(), phoneNo.getValue());
      if (state.equals(DatabaseConnection.SUCCESS))
      {
        return new Alerts(Alert.AlertType.INFORMATION,"Success","Edit was successful");
      }
      else
      {
        return new Alerts(Alert.AlertType.ERROR,"Error","You cannot edit this employee's information right now");
      }
    }
    catch (RemoteException e)
    {
      return new Alerts(Alert.AlertType.ERROR,"Error","Mandatory fields can not be empty");
    }
    catch (Exception e)
    {
      return new Alerts(Alert.AlertType.ERROR,"Error","Some Error occurred");
    }
  }

  public Alerts delete()
  {
    if (model.getSelectedEmployee().getState().equals(new LogIn().getState()))
    {
      return new Alerts(Alert.AlertType.ERROR,"Error","You cannot delete this Employee right now, because the Employee is currently using the system.");
    }
    else
    {
      try
      {
        String state = model.deleteEmployee(username.getValue());
        if (state.equals(DatabaseConnection.SUCCESS))
        {
          return new Alerts(Alert.AlertType.INFORMATION,"Success","The employee has been successfully removed");
        }
        else
        {
          return new Alerts(Alert.AlertType.ERROR,"Error","You cannot delete this employee right now");
        }
      }
      catch (RemoteException e)
      {
        return new Alerts(Alert.AlertType.ERROR,"Error","Some Error occurred");
      }
    }
  }

  public void bindUsername(StringProperty property)
  {
    property.bindBidirectional(username);
  }

  public void bindFirstName(StringProperty property)
  {
    property.bindBidirectional(firstName);
  }

  public void bindLastName(StringProperty property)
  {
    property.bindBidirectional(lastName);
  }

  public void bindPosition(ObjectProperty<String> property)
  {
    property.bindBidirectional(position);
  }

  public void bindPhoneNo(StringProperty property)
  {
    property.bindBidirectional(phoneNo);
  }
}
