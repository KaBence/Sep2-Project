package Client.ViewModel;

import Client.Model.Model;
import Server.Model.Hotel.Users.Customer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.rmi.RemoteException;

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

  public String delete() throws RemoteException
  {
    return  model.deleteSelectedCustomer(username.getValue());
  }
  public void fill(){
    Customer temp= model.getSelectedCustomer();
    username.set(temp.getUsername());
    firstName.set(temp.getFirstName());
    lastName.set(temp.getLastName());
    phoneNo.set(temp.getPhoneNo());
    payment.set(temp.getPaymentInfo());
  }
  public String save() throws RemoteException{
    try{
      return model.updateCustomer(username.getValue(),firstName.getValue(),
          lastName.getValue(), phoneNo.getValue(), payment.getValue());
    }
    catch (Exception e){
      return "mandatory";
    }
  }
}
