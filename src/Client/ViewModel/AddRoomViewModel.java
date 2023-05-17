package Client.ViewModel;

import Client.Model.Model;
import Server.Model.Hotel.Room;
import javafx.beans.property.*;

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

  public Room addRoom() throws RemoteException
  {
    try
    {
      return model.addRoom(Integer.parseInt(roomNumber.getValue()),
          Integer.parseInt(numberOfBeds.getValue()),
          Integer.parseInt(size.getValue()), price.getValue(),
          orientation.getValue(), internet.getValue(), bathroom.getValue(),
          kitchen.getValue(), balcony.getValue());
    }
    catch (NumberFormatException e)
    {
      throw new NumberFormatException();
    }
  }
}
