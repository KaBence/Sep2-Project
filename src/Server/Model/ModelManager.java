package Server.Model;

import Server.Utility.DataBase.Room.RoomData;
import Server.Utility.DataBase.Room.RoomDataImplementation;

import java.beans.PropertyChangeListener;

public class ModelManager implements Model
{
  private RoomData roomData;

  public ModelManager(){
    roomData=new RoomDataImplementation();
  }

  @Override public void addListener(PropertyChangeListener listener)
  {

  }

  @Override public void addRoom(int roomNumber, int numberOfBeds, int size,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony)
  {
   roomData.addNewRoom(roomNumber, numberOfBeds, size, orientation, internet, bathroom, kitchen, balcony);
  }
}
