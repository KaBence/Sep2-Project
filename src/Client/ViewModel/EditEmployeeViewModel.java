package Client.ViewModel;

import Client.Model.Model;
import Client.View.ViewHandler;
import Server.Model.Employee;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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

  //String userID, String firstName, String lastName, String position, String phoneNo,String password
  public String edit() throws RemoteException
  {
    try
    {
      return model.updateEmployee(username.getValue(),firstName.getValue(), lastName.getValue(),
          position.getValue(), phoneNo.getValue());
    }
    catch (Exception e)
    {
      return "mandatory";
    }
  }

  public String delete() throws RemoteException
  {
    return  model.deleteEmployee(username.getValue());
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
