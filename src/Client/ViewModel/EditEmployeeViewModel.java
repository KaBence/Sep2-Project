package Client.ViewModel;

import Client.Model.Model;
import Client.View.ViewHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EditEmployeeViewModel
{
  private Model model;
  private SimpleStringProperty username, firstName, lastName, position, phoneNo;

  public EditEmployeeViewModel(Model model)
  {
    this.model = model;
    this.username = new SimpleStringProperty();
    this.firstName = new SimpleStringProperty();
    this.lastName = new SimpleStringProperty();
    this.position = new SimpleStringProperty();
    this.phoneNo = new SimpleStringProperty();
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

  public void bindPosition(StringProperty property)
  {
    property.bindBidirectional(property);
  }

  public void bindPhoneNo(StringProperty property)
  {
    property.bindBidirectional(phoneNo);
  }
}
