package Client.ViewModel.Customer;

import Client.Model.Model;
import Client.Model.ModelCustomerSide;
import Client.Utility.Alerts;
import Server.Utility.DataBase.DatabaseConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import java.rmi.RemoteException;

public class AddCustomerViewModel
{
  private ModelCustomerSide model;
  private SimpleStringProperty firstName, lastName, payment, password, repeatPassword, phoneNo, username;

  public AddCustomerViewModel(Model model)
  {
    this.model = model;
    this.firstName = new SimpleStringProperty();
    this.lastName = new SimpleStringProperty();
    this.username = new SimpleStringProperty();
    this.phoneNo = new SimpleStringProperty();
    this.payment = new SimpleStringProperty();
    this.password = new SimpleStringProperty();
    this.repeatPassword = new SimpleStringProperty();
  }

  public void bindFirstName(StringProperty property)
  {
    property.bindBidirectional(firstName);
  }

  public void bindLastName(StringProperty property)
  {
    property.bindBidirectional(lastName);
  }

  public void bindUsername(StringProperty property)
  {
    property.bindBidirectional(username);
  }

  public void bindPhoneNo(StringProperty property)
  {
    property.bindBidirectional(phoneNo);
  }

  public void bindPayment(StringProperty property)
  {
    property.bindBidirectional(payment);
  }

  public void bindPassword(StringProperty property)
  {
    property.bindBidirectional(password);
  }

  public void bindRepeatPassword(StringProperty property)
  {
    property.bindBidirectional(repeatPassword);
  }

  public Alerts addCustomer()
  {
    try
    {
      if (!(password.getValue().equals(repeatPassword.getValue())))
      {
        return new Alerts(Alert.AlertType.ERROR,"Error","Passwords are not identical");
      }
      else
      {
        String temp = model.addCustomer(username.getValue(), password.getValue(),
            firstName.getValue(), lastName.getValue(), phoneNo.getValue(),
            payment.getValue());
        if (temp.equals(DatabaseConnection.SUCCESS))
        {
          return new Alerts(Alert.AlertType.INFORMATION,"Customer account has been created","Username: " + username.getValue() + "\nPassword: "+ password.getValue());
        }
        if (temp.equals(DatabaseConnection.MANDATORY))
        {
          return new Alerts(Alert.AlertType.ERROR,"Error","Please fill up every field");
        }
        else
        {
          return new Alerts(Alert.AlertType.ERROR,"Error","Sorry. Some Error Occurred");
        }
      }
    }
    catch (NumberFormatException | NullPointerException | RemoteException e)
    {
      return new Alerts(Alert.AlertType.ERROR,"Error","Please fill up the every field");
    }
  }
}
