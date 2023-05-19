package Client.ViewModel;

import Client.Model.Model;
import Server.Model.Hotel.Users.Employee;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;

import javax.print.DocFlavor;
import java.rmi.RemoteException;

public class RegisterEmployeeViewModel
{
  private Model model;
  private SimpleStringProperty firstNameAddNewEmployee, lastNameAddEmployee, phoneAddEmployee;
  private SimpleObjectProperty<String> positionAddNewEmployee;
  private SimpleStringProperty password, repeatPassword;

  public RegisterEmployeeViewModel(Model model)
  {
    this.model = model;
    firstNameAddNewEmployee = new SimpleStringProperty();
    lastNameAddEmployee = new SimpleStringProperty();
    phoneAddEmployee = new SimpleStringProperty();
    password = new SimpleStringProperty();
    repeatPassword = new SimpleStringProperty();
    positionAddNewEmployee = new SimpleObjectProperty<>();
  }

  public void bindFirstName(StringProperty property)
  {
    property.bindBidirectional(firstNameAddNewEmployee);
  }

  public void bindLastName(StringProperty property)
  {
    property.bindBidirectional(lastNameAddEmployee);
  }

  public void bindPhoneNo(StringProperty property)
  {
    property.bindBidirectional(phoneAddEmployee);
  }

  public void bindPosition(ObjectProperty property)
  {
    property.bindBidirectional(positionAddNewEmployee);
  }

  public void bindPassword(StringProperty property)
  {
    property.bindBidirectional(password);
  }

  public void bindReapeatPassword(StringProperty property)
  {
    property.bindBidirectional(repeatPassword);
  }

  public boolean addEmployee() throws RemoteException
  {
    if (password.getValue().equals(repeatPassword.getValue()))
    {
      try
      {
        model.addEmployee(firstNameAddNewEmployee.getValue(),
            lastNameAddEmployee.getValue(), positionAddNewEmployee.getValue(),
            phoneAddEmployee.getValue(), repeatPassword.getValue());
        Alert wrong = new Alert(Alert.AlertType.CONFIRMATION);
        wrong.setTitle("Good job");
        wrong.setHeaderText("New employee has been added\"");
        wrong.showAndWait();
        return true;
      }
      catch (NumberFormatException e)
      {
        Alert wrong = new Alert(Alert.AlertType.ERROR);
        wrong.setTitle("Invalid data");
        wrong.setHeaderText("Problem");
        wrong.showAndWait();
        return false;
      }
    }
    return false;
  }
}

