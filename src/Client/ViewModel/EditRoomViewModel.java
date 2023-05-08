package Client.ViewModel;

import Client.Model.Model;
import Server.Model.Room;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

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

  public void fill(){
    Room temp=model.getSelectedRoom();
    internet=temp.isInternet();
  }
}
