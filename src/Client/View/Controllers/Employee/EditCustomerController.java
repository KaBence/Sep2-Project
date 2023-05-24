package Client.View.Controllers.Employee;

import Client.View.Scenes.SceneNames;
import Client.View.ViewHandler;
import Client.ViewModel.Employee.EditCustomerViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;

public class EditCustomerController
{
  private Region root;
  private ViewHandler viewHandler;
  private EditCustomerViewModel viewModel;

  @FXML TextField username, phoneNo, lastName, firstName, payment;

  public void init(ViewHandler viewHandler, EditCustomerViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;

    this.viewModel.bindPayment(payment.textProperty());
    this.viewModel.bindFirstName(firstName.textProperty());
    this.viewModel.bindLastName(lastName.textProperty());
    this.viewModel.bindPhoneNo(phoneNo.textProperty());
    this.viewModel.bindUsername(username.textProperty());
    username.setEditable(false);
  }

  public Region getRoot()
  {
    root.setUserData("Edit Customer Info");
    return root;
  }

  public void reset()
  {
    viewModel.fill();
  }

  @FXML void save() throws RemoteException
  {
    if (viewModel.save())
      viewHandler.openView(SceneNames.EmployeeHomeCustomer);
  }

  @FXML void cancel()
  {
    viewHandler.openView(SceneNames.EmployeeHomeCustomer);
  }

  @FXML void delete() throws RemoteException
  {
    if (viewModel.delete())
    {
      viewHandler.openView(SceneNames.EmployeeHomeCustomer);
    }
  }

}
