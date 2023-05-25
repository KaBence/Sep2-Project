package Client.ViewModel.Employee;

import Client.Model.Model;
import Client.Model.ModelEmployeeSide;
import Client.Utility.Alerts;
import Server.Model.Hotel.Room;
import Server.Utility.DataBase.DatabaseConnection;
import javafx.beans.property.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.rmi.RemoteException;

public class AddRoomViewModel
{
  private ModelEmployeeSide model;

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

  public Alerts addRoom()
  {
    try
    {
      String state = model.addRoom(Integer.parseInt(roomNumber.getValue()), Integer.parseInt(numberOfBeds.getValue()), Integer.parseInt(size.getValue()), price.getValue(), orientation.getValue(), internet.getValue(), bathroom.getValue(), kitchen.getValue(), balcony.getValue());
      if (state.equals(DatabaseConnection.SUCCESS))
      {
        return new Alerts(Alert.AlertType.INFORMATION,"Success","Adding a room is successful");
      }
      if (state.equals(DatabaseConnection.ERROR))
      {
        return new Alerts(Alert.AlertType.ERROR,"Error","Some Error occurred");
      }
      if (state.equals(DatabaseConnection.MANDATORY))
      {
        return new Alerts(Alert.AlertType.WARNING,"Error","Please fill up every field");
      }
    }
    catch (NumberFormatException | NullPointerException e)
    {
      return new Alerts(Alert.AlertType.WARNING,"Error","Please fill up every field");
    }
    catch (RemoteException e)
    {
      return new Alerts(Alert.AlertType.ERROR,"Database Error","Contact the developers of the system\nPhone number: +45 8755 4243\nPhone number: +45 8755 4222");
    }
    return new Alerts(Alert.AlertType.WARNING,"Error","Please fill up every field");
  }
}
