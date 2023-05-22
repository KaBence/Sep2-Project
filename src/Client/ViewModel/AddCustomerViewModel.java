package Client.ViewModel;

import Client.Model.Model;
import Server.Utility.DataBase.DatabaseConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.rmi.RemoteException;

public class AddCustomerViewModel
{
  private Model model;
  private SimpleStringProperty firstName, lastName, payment, password, repeatPassword, phoneNo, username;

  public AddCustomerViewModel(Model model)
  {
    this.model = model;
    firstName = new SimpleStringProperty();
    lastName = new SimpleStringProperty();
    username = new SimpleStringProperty();
    phoneNo = new SimpleStringProperty();
    payment = new SimpleStringProperty();
    password = new SimpleStringProperty();
    repeatPassword = new SimpleStringProperty();
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

  public boolean addCustomer() throws RemoteException
  {
    try
    {
      if(repeatPassword.getValue().equals(password.getValue()))
      {
        String temp = model.addCustomer(username.getValue(), password.getValue(),
            firstName.getValue(), lastName.getValue(), phoneNo.getValue(),
            payment.getValue());
        if (temp.equals(DatabaseConnection.SUCCESS))
        {
          Alert alert = new Alert(Alert.AlertType.INFORMATION,
              "The Customer Account Was Created: \n Username: "
                  + username.getValue() + "\n Password: " + password.getValue(),
              ButtonType.OK);
          alert.setHeaderText(null);
          alert.setTitle("Success");
          alert.showAndWait();
          return true;
        }
        if (temp.equals(DatabaseConnection.ERROR))
        {
          Alert alert = new Alert(Alert.AlertType.ERROR,
              "Sorry. Some Error Occurred", ButtonType.OK);
          alert.setHeaderText(null);
          alert.setTitle("Error");
          alert.showAndWait();
          return false;
        }
        if (temp.equals(DatabaseConnection.MANDATORY))
        {
          Alert alert = new Alert(Alert.AlertType.ERROR,
              "Please fill up every field", ButtonType.OK);
          alert.setHeaderText(null);
          alert.setTitle("Error");
          alert.showAndWait();
          return false;
        }
      }
      if (repeatPassword.equals(""))
      {
        Alert alert = new Alert(Alert.AlertType.ERROR,
            "Fill in the Repeat Password field, please", ButtonType.OK);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.showAndWait();
        return false;
      }
      else if (!repeatPassword.equals(password))
      {
        Alert alert = new Alert(Alert.AlertType.ERROR,
            "Passwords are not identical", ButtonType.OK);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.showAndWait();
        return false;
      }
    }
    catch (NumberFormatException | NullPointerException e)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "Please fill up the every field", ButtonType.OK);
      alert.setHeaderText(null);
      alert.setTitle("Error");
      alert.showAndWait();
    }
    return false;
  }

}
