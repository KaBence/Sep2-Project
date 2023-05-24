package Client.View.Controllers.Employee;

import Client.View.Scenes.SceneNames;
import Client.View.ViewHandler;
import Client.ViewModel.Employee.EditEmployeeViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;

public class EditEmployeeController
{
  private Region root;
  private ViewHandler viewHandler;
  private EditEmployeeViewModel viewModel;

  @FXML TextField firstName, lastName, phoneNo;
  @FXML ChoiceBox<String> position;
  @FXML Label username;

  public void init(ViewHandler viewHandler, EditEmployeeViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;


    this.viewModel.bindFirstName(firstName.textProperty());
    this.viewModel.bindLastName(lastName.textProperty());
    this.viewModel.bindPhoneNo(phoneNo.textProperty());
    this.viewModel.bindPosition(position.valueProperty());
    this.viewModel.bindUsername(username.textProperty());
  }

  public void initialize()
  {
    position.getItems().add("Manager");
    position.getItems().add("Cleaner");
    position.getItems().add("Handyman");
    position.getItems().add("Receptionist");

  }
  public Region getRoot(){
    root.setUserData("Edit Employee Info");
    return root;
  }

  public void reset(){
  viewModel.fill();
  }

  @FXML void save() throws RemoteException
  {
    if (viewModel.edit())
      viewHandler.openView(SceneNames.AdminAllEmployees);
  }

  @FXML void delete() throws RemoteException
  {
    if (viewModel.delete())
      viewHandler.openView(SceneNames.AdminAllEmployees);
  }
  @FXML void cancel(){
    viewHandler.openView(SceneNames.AdminAllEmployees);
  }
}