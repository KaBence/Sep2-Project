package Client.ViewModel.Employee;

import Client.Model.Model;
import Client.Model.ModelEmployeeSide;
import Client.Utility.Alerts;
import Server.Model.Hotel.Users.Customer;
import Server.Model.Hotel.Users.States.LogIn;
import Server.Utility.DataBase.DatabaseConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.rmi.RemoteException;

public class EditCustomerViewModel
{
  private ModelEmployeeSide model;

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

  public Alerts delete() throws RemoteException
  {
    if (model.getSelectedCustomer().getState().equals(new LogIn().getState()))
    {
      return new Alerts(Alert.AlertType.ERROR,"Error","You cannot delete this customer right now, because the Customer is currently using the system.");
    }
    else
    {
      String state = model.deleteSelectedCustomer(username.getValue());
      if (state.equals(DatabaseConnection.SUCCESS))
      {
        return new Alerts(Alert.AlertType.INFORMATION,"Success","The customer has been successfully removed");
      }
      else
      {
        return new Alerts(Alert.AlertType.ERROR,"Error","You cannot delete this customer right now");
      }
    }
  }
  public void fill(){
    Customer temp= model.getSelectedCustomer();
    username.set(temp.getUsername());
    firstName.set(temp.getFirstName());
    lastName.set(temp.getLastName());
    phoneNo.set(temp.getPhoneNo());
    payment.set(temp.getPaymentInfo());
  }
  public Alerts save() throws RemoteException{

    try
    {
      String state=model.updateCustomer(username.getValue(),firstName.getValue(), lastName.getValue(), phoneNo.getValue(), payment.getValue());
      if (state.equals(DatabaseConnection.SUCCESS))
      {
        return new Alerts(Alert.AlertType.INFORMATION,"Success","Edit Successful");
      }
      else
      {
        return new Alerts(Alert.AlertType.ERROR,"Error","You cannot edit this customer right now");
      }
    }
    catch (Exception e)
    {
      return new Alerts(Alert.AlertType.WARNING,"Error","Mandatory fields can not be empty");
    }
  }
}
