package Client.ViewModel;

import Client.Model.Model;
import Server.Model.Room;
import javafx.beans.property.*;

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

  public String edit() throws RemoteException
  {
    return model.updateRoom(Integer.parseInt(roomNumber.getValue()),Integer.parseInt(numberOfBeds.getValue()),Integer.parseInt(size.getValue()),price.getValue(),orientation.getValue(),internet.getValue(),bathroom.getValue(),kitchen.getValue(),balcony.getValue());
  }

  public String delete() throws RemoteException
  {
    return model.deleteRoom(Integer.parseInt(roomNumber.getValue()));
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
