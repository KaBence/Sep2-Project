package Client.ViewModel;

import Client.Model.Model;
import Server.Model.Room;

import java.rmi.RemoteException;

public class AddRoomViewModel
{
  private Model model;

  public AddRoomViewModel(Model model)
  {
    this.model = model;
  }

  public void addRoom(int roomNumber, int numberOfBeds, int size,int price,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony) throws RemoteException
  {
    model.addRoom(roomNumber,numberOfBeds,size,price,orientation,internet,bathroom,kitchen,balcony);
  }
}
