package Client.View.Controllers;

import Client.View.SceneNames;
import Client.View.ViewHandler;
import Client.ViewModel.AddCustomerViewModel;
import Client.ViewModel.AddRoomViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;

public class AddCustomerController
{
  @FXML TextField firstName, lastName, payment, password, repeatPassword, phoneNo, username;
  private Region root;
  private ViewHandler viewHandler;
  private AddCustomerViewModel viewModel;

  public void init(ViewHandler viewHandler, AddCustomerViewModel viewModel, Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;

    viewModel.bindFirstName(firstName.textProperty());
    viewModel.bindLastName(lastName.textProperty());
    viewModel.bindUsername(username.textProperty());
    viewModel.bindPhoneNo(phoneNo.textProperty());
    viewModel.bindPayment(payment.textProperty());
    viewModel.bindPassword(password.textProperty());
    viewModel.bindRepeatPassword(repeatPassword.textProperty());
  }

  public Region getRoot()
  {
    root.setUserData("Add a customer to the system");
    return root;
  }

  public void reset()
  {
    firstName.clear();
    lastName.clear();
    username.clear();
    phoneNo.clear();
    payment.clear();
    password.clear();
    repeatPassword.clear();
  }

  @FXML public void cancel()
  {
    viewHandler.openView(SceneNames.CustomerHome);
  }

  @FXML public void create() throws RemoteException
  {
    if(viewModel.addCustomer())
      viewHandler.openView(SceneNames.CustomerHome);
  }

}
