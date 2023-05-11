package Client.ViewModel;

import Client.Model.Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EditCustomerViewModel
{
  private Model model;

  private SimpleStringProperty username,firstName,phoneNo,lastName,payment;

  public EditCustomerViewModel(Model model)
  {
    this.model = model;
    username=new SimpleStringProperty();
    firstName=new SimpleStringProperty();
    phoneNo=new SimpleStringProperty();
    lastName=new SimpleStringProperty();
    payment=new SimpleStringProperty();
  }

  public void bindUsername(StringProperty property){
    property.bindBidirectional(username);
  }

  public void bindFirstName(StringProperty property){
    property.bindBidirectional(firstName);
  }

  public void bindLastName(StringProperty property){
    property.bindBidirectional(lastName);
  }

  public void bindPhoneNo(StringProperty property){
    property.bindBidirectional(phoneNo);
  }

  public void bindPayment(StringProperty property){
    property.bindBidirectional(payment);
  }
}
