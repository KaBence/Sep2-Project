package Client.ViewModel.Employee;

import Client.Model.Model;
import Server.Model.Hotel.Room;
import Server.Utility.DataBase.DatabaseConnection;
import javafx.beans.property.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.rmi.RemoteException;

public class AddRoomViewModel
{
  private Model model;

  private SimpleBooleanProperty internet, bathroom, kitchen, balcony;
  private SimpleStringProperty roomNumber, numberOfBeds, size;
  private SimpleObjectProperty<Integer> price;
  private SimpleObjectProperty<String> orientation;

  public AddRoomViewModel(Model model)
  {
    this.model = model;
    internet = new SimpleBooleanProperty();
    balcony = new SimpleBooleanProperty();
    bathroom = new SimpleBooleanProperty();
    kitchen = new SimpleBooleanProperty();
    roomNumber = new SimpleStringProperty();
    numberOfBeds = new SimpleStringProperty();
    size = new SimpleStringProperty();
    price = new SimpleObjectProperty();
    orientation = new SimpleObjectProperty();
  }

  public void bindInternet(BooleanProperty property)
  {
    property.bindBidirectional(internet);
  }

  public void bindBalcony(BooleanProperty property)
  {
    property.bindBidirectional(balcony);
  }

  public void bindBathroom(BooleanProperty property)
  {
    property.bindBidirectional(bathroom);
  }

  public void bindKitchen(BooleanProperty property)
  {
    property.bindBidirectional(kitchen);
  }

  public void bindRoomNo(StringProperty property)
  {
    property.bindBidirectional(roomNumber);
  }

  public void bindNoBeds(StringProperty property)
  {
    property.bindBidirectional(numberOfBeds);
  }

  public void bindSize(StringProperty property)
  {
    property.bindBidirectional(size);
  }

  public void bindPrice(ObjectProperty<Integer> property)
  {
    property.bindBidirectional(price);
  }

  public void bindOrientation(ObjectProperty<String> property)
  {
    property.bindBidirectional(orientation);
  }

  public boolean addRoom() throws RemoteException
  {
    try
    {
      String state = model.addRoom(Integer.parseInt(roomNumber.getValue()),
          Integer.parseInt(numberOfBeds.getValue()),
          Integer.parseInt(size.getValue()), price.getValue(),
          orientation.getValue(), internet.getValue(), bathroom.getValue(),
          kitchen.getValue(), balcony.getValue());
      if (state.equals(DatabaseConnection.SUCCESS)){
        Alert alert=new Alert(Alert.AlertType.INFORMATION,"Adding a room is successful", ButtonType.OK);
        alert.setHeaderText(null);
        alert.setTitle("Success");
        alert.showAndWait();
        return true;
      }
      if (state.equals(DatabaseConnection.ERROR)){
        Alert alert=new Alert(Alert.AlertType.ERROR,"Some Error occurred", ButtonType.OK);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.showAndWait();
        return false;
      }
      if (state.equals(DatabaseConnection.MANDATORY)){
        Alert alert=new Alert(Alert.AlertType.ERROR,"Please fill up every field", ButtonType.OK);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.showAndWait();
        return false;
      }
    }
    catch (NumberFormatException | NullPointerException e)
    {
      Alert alert=new Alert(Alert.AlertType.ERROR,"Please fill up the every field", ButtonType.OK);
      alert.setHeaderText(null);
      alert.setTitle("Error");
      alert.showAndWait();
    }
    return false;
  }
}
