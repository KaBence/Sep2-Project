package Client.ViewModel;

import Client.Model.Model;
import Server.Model.Hotel.Users.Employee;
import Server.Utility.DataBase.DatabaseConnection;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.rmi.RemoteException;

public class EditEmployeeViewModel
{
  private Model model;
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

  public boolean edit() throws RemoteException
  {
    try
    {
      String state= model.updateEmployee(username.getValue(),firstName.getValue(), lastName.getValue(), position.getValue(), phoneNo.getValue());
      if (state.equals(DatabaseConnection.SUCCESS)){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Edit Successful",
            ButtonType.OK);
        alert.setHeaderText(null);
        alert.setTitle("Success");
        alert.showAndWait();
        return true;
      }
      else {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setHeaderText("Error");
        error.setHeaderText("You cannot edit this employee's information right now");
        error.showAndWait();
        return false;
      }
    }
    catch (Exception e)
    {
      Alert mandatory = new Alert(Alert.AlertType.ERROR);
      mandatory.setHeaderText("Error");
      mandatory.setHeaderText("Mandatory fields can not be empty");
      mandatory.showAndWait();
      return false;
    }
  }

  public boolean delete() throws RemoteException
  {
    Alert alert = new Alert(Alert.AlertType.WARNING,
        "Do you really want to delete this employee from the system?",
        ButtonType.NO, ButtonType.YES);
    alert.setTitle("Warning");
    alert.setHeaderText(null);
    alert.showAndWait();
    if (alert.getResult().equals(ButtonType.NO))
      return false;
    String state= model.deleteEmployee(username.getValue());
    if (state.equals(DatabaseConnection.SUCCESS)){
      Alert success = new Alert(Alert.AlertType.INFORMATION);
      success.setHeaderText("Success");
      success.setHeaderText("The employee has been successfully removed");
      success.showAndWait();
      return true;
    }
    else {
      Alert error = new Alert(Alert.AlertType.ERROR);
      error.setHeaderText("Error");
      error.setHeaderText("You cannot delete this employee right now");
      error.showAndWait();
      return false;
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
