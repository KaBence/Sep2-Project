package Client.View.Controllers;

import Client.View.SceneNames;
import Client.View.ViewHandler;
import Client.ViewModel.EditCustomerViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class EditCustomerController
{
  private Region root;
  private ViewHandler viewHandler;
  private EditCustomerViewModel viewModel;

  @FXML TextField username,phoneNo,lastName,firstName,payment;

  public void init(ViewHandler viewHandler, EditCustomerViewModel viewModel, Region root){
    this.viewHandler=viewHandler;
    this.viewModel=viewModel;
    this.root=root;

    this.viewModel.bindPayment(payment.textProperty());
    this.viewModel.bindFirstName(firstName.textProperty());
    this.viewModel.bindLastName(lastName.textProperty());
    this.viewModel.bindPhoneNo(phoneNo.textProperty());
    this.viewModel.bindUsername(username.textProperty());

  }

  public Region getRoot(){
    root.setUserData("Edit Customer Info");
    return root;
  }

  public void reset(){

  }

  @FXML void save(){

  }

  @FXML void cancel(){
    viewHandler.openView(SceneNames.EmployeeHome);
  }

  @FXML void delete(){

  }
}
