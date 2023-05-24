package Client.ViewModel.Employee;

import Client.Model.Model;
import Server.Model.Hotel.Users.Customer;
import Server.Utility.DataBase.DatabaseConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

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

  public boolean delete() throws RemoteException
  {
    Alert alert = new Alert(Alert.AlertType.WARNING,
        "Do you really want to delete this customer from the system?",
        ButtonType.NO, ButtonType.YES);
    alert.setTitle("Warning");
    alert.setHeaderText(null);
    alert.showAndWait();
    if (alert.getResult().equals(ButtonType.NO))
      return false;

    if (model.getSelectedCustomer().getState().equals("Logged in"))
    {
      Alert logged = new Alert(Alert.AlertType.ERROR);
      logged.setHeaderText("Error");
      logged.setHeaderText("You cannot delete this customer right now, because the Customer is currently using the system.");
      logged.showAndWait();
      return false;
    }
    else
    {
      String state = model.deleteSelectedCustomer(username.getValue());
      if (state.equals(DatabaseConnection.SUCCESS))
      {
        Alert success = new Alert(Alert.AlertType.INFORMATION);
        success.setHeaderText("Success");
        success.setHeaderText("The customer has been successfully removed");
        success.showAndWait();
        return true;
      }
      else
      {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setHeaderText("Error");
        error.setHeaderText("You cannot delete this customer right now");
        error.showAndWait();
        return false;
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
  public boolean save() throws RemoteException{

    try{
      String state=model.updateCustomer(username.getValue(),firstName.getValue(), lastName.getValue(), phoneNo.getValue(), payment.getValue());
      if (state.equals(DatabaseConnection.SUCCESS)){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Edit Successful", ButtonType.OK);
        alert.setHeaderText(null);
        alert.setTitle("Success");
        alert.showAndWait();
        return true;
      }
      else {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setHeaderText("Error");
        error.setHeaderText("You cannot edit this customer right now");
        error.showAndWait();
        return false;
      }
    }
    catch (Exception e){
      Alert mandatory = new Alert(Alert.AlertType.ERROR);
      mandatory.setHeaderText("Error");
      mandatory.setHeaderText("Mandatory fields can not be empty");
      mandatory.showAndWait();
      return false;
    }
  }
}
