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

public class EditRoomViewModel
{
  private ModelEmployeeSide model;
  private SimpleBooleanProperty internet,bathroom,kitchen,balcony;
  private SimpleStringProperty roomNumber,numberOfBeds,size;
  private SimpleObjectProperty<Integer> price;
  private SimpleObjectProperty<String > orientation;

  public EditRoomViewModel(Model model)
  {
    this.model = model;
    internet=new SimpleBooleanProperty();
    balcony=new SimpleBooleanProperty();
    bathroom=new SimpleBooleanProperty();
    kitchen=new SimpleBooleanProperty();
    roomNumber= new SimpleStringProperty();
    numberOfBeds= new SimpleStringProperty();
    size= new SimpleStringProperty();
    price= new SimpleObjectProperty();
    orientation= new SimpleObjectProperty();
  }

  public Alerts edit()
  {
    try
    {
      String state= model.updateRoom(Integer.parseInt(roomNumber.getValue()),Integer.parseInt(numberOfBeds.getValue()),Integer.parseInt(size.getValue()),price.getValue(),orientation.getValue(),internet.getValue(),bathroom.getValue(),kitchen.getValue(),balcony.getValue());
      if (state.equals(DatabaseConnection.SUCCESS))
      {
        return new Alerts(Alert.AlertType.INFORMATION,"Success","Edit was successful");
      }
      else if (state.equals(DatabaseConnection.ERROR))
      {
        return new Alerts(Alert.AlertType.ERROR,"Error","An error occurred");
      }
      else
      {
        return new Alerts(Alert.AlertType.ERROR,"Error","Please fill up the fields with the correct information");
      }
    }
    catch (NumberFormatException e)
    {
      return new Alerts(Alert.AlertType.ERROR,"Error","Please fill up the fields with the correct information");
    }
    catch (RemoteException e)
    {
      return new Alerts(Alert.AlertType.ERROR,"Error","An error occurred");
    }
  }

  public Alerts delete()
  {
    try
    {
      String state = model.deleteRoom(Integer.parseInt(roomNumber.getValue()));
      if (state.equals(DatabaseConnection.SUCCESS))
      {
        return new Alerts(Alert.AlertType.INFORMATION, "Success", "The room has been successfully removed");
      }
      else
      {
        return new Alerts(Alert.AlertType.ERROR,"Error","You cannot delete this room right now");
      }
    }
    catch (RemoteException e)
    {
      return new Alerts(Alert.AlertType.ERROR,"Error","An error occurred");
    }
  }

  public void fill(){
    Room temp=model.getSelectedRoom();
    internet.set(temp.isInternet());
    balcony.set(temp.isBalcony());
    bathroom.set(temp.isBathroom());
    kitchen.set(temp.isKitchenet());
    roomNumber.set(String.valueOf(temp.getRoomNo()));
    numberOfBeds.set(String.valueOf(temp.getNoOfBeds()));
    size.set(String.valueOf(temp.getSize()));
    price.set(temp.getPrice());
    orientation.set(temp.getOrientation());
  }

  public void bindInternet(BooleanProperty property){
    property.bindBidirectional(internet);
  }
  public void bindBalcony(BooleanProperty property){
    property.bindBidirectional(balcony);
  }
  public void bindBathroom(BooleanProperty property){
    property.bindBidirectional(bathroom);
  }
  public void bindKitchen(BooleanProperty property){
    property.bindBidirectional(kitchen);
  }
  public void bindRoomNo(StringProperty property){
    property.bindBidirectional(roomNumber);
  }

  public void bindNoBeds(StringProperty  property){
    property.bindBidirectional(numberOfBeds);
  }

  public void bindSize(StringProperty property){
    property.bindBidirectional(size);
  }

  public void bindPrice(ObjectProperty<Integer > property){
    property.bindBidirectional(price);
  }

  public void bindOrientation(ObjectProperty<String > property){
    property.bindBidirectional(orientation);
  }
}
