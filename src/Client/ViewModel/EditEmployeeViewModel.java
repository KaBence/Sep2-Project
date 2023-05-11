package Client.ViewModel;

import Client.Model.Model;
import Client.View.ViewHandler;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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
