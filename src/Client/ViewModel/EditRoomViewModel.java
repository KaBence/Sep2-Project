package Client.ViewModel;

import Client.Model.Model;
import Server.Model.Hotel.Room;
import Server.Utility.DataBase.DatabaseConnection;
import javafx.beans.property.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.rmi.RemoteException;

public class EditRoomViewModel
{
  private Model model;
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

  public boolean edit() throws RemoteException
  {
    try
    {
      String state= model.updateRoom(Integer.parseInt(roomNumber.getValue()),Integer.parseInt(numberOfBeds.getValue()),Integer.parseInt(size.getValue()),price.getValue(),orientation.getValue(),internet.getValue(),bathroom.getValue(),kitchen.getValue(),balcony.getValue());
      if (state.equals(DatabaseConnection.SUCCESS)){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Edit Successful", ButtonType.OK);
        alert.setHeaderText(null);
        alert.setTitle("Success");
        alert.showAndWait();
        return true;
      }
      if (state.equals(DatabaseConnection.ERROR)){
        Alert alert = new Alert(Alert.AlertType.ERROR, "An error occurred", ButtonType.OK);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.showAndWait();
        return false;
      }
      if (state.equals(DatabaseConnection.MANDATORY)){
        Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill up the fields with the correct information", ButtonType.OK);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.showAndWait();
        return false;
      }
    }
    catch (NumberFormatException e)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill up the fields with the correct information", ButtonType.OK);
      alert.setHeaderText(null);
      alert.setTitle("Error");
      alert.showAndWait();
    }
    return false;
  }

  public boolean delete() throws RemoteException
  {
    Alert alert = new Alert(Alert.AlertType.WARNING,
        "Do you really want to delete this room from the system?", ButtonType.NO, ButtonType.YES);
    alert.setTitle("Warning");
    alert.setHeaderText(null);
    alert.showAndWait();
    if (alert.getResult().equals(ButtonType.NO))
      return false;
    String state = model.deleteRoom(Integer.parseInt(roomNumber.getValue()));
    if (state.equals(DatabaseConnection.SUCCESS)){
      Alert success = new Alert(Alert.AlertType.INFORMATION);
      success.setHeaderText("Success");
      success.setHeaderText("The room has been successfully removed");
      success.showAndWait();
      return true;
    }
    else {
      Alert error = new Alert(Alert.AlertType.ERROR);
      error.setHeaderText("Error");
      error.setHeaderText("You cannot delete this room right now");
      error.showAndWait();
      return false;
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
