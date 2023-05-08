package Client.View.Controllers;

import Client.View.SceneNames;
import Client.View.ViewHandler;
import Client.ViewModel.EmployeeHomeViewModel;
import Client.ViewModel.HomeViewModel;
import Server.Model.Room;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;

public class EmployeeHomeController
{
  @FXML TableView<Room> tableView;
  @FXML TableColumn<Integer,Room> tableRoomNo;
  @FXML TableColumn<Integer,Room> tableBeds;
  @FXML TableColumn<Integer,Room> tableSize;
  @FXML TableColumn<Integer,String> tableOri;
  private Region root;
  private ViewHandler viewHandler;
  private EmployeeHomeViewModel viewModel;




  public void init(ViewHandler viewHandler, EmployeeHomeViewModel viewModel, Region root){
    this.viewHandler=viewHandler;
    this.viewModel=viewModel;
    this.viewModel.bindRoomList(tableView.itemsProperty());
    this.root=root;

  }

  public Region getRoot(){
    root.setUserData("Employee Home Page");
    return root;
  }

  public void reset(){

  }

  @FXML void addRoom(){
    viewHandler.openView(SceneNames.AddRoom);
  }

  @FXML void Home(){
    viewHandler.openView(SceneNames.Home);
  }
}
