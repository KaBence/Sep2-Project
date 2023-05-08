package Server.Model;

import Server.Utility.DataBase.Room.RoomData;
import Server.Utility.DataBase.Room.RoomDataImplementation;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class ModelManager implements Model
{
  private RoomData roomData;

  public ModelManager(){
    roomData=new RoomDataImplementation();
  }

  @Override public void addListener(PropertyChangeListener listener)
  {

  }

  @Override public void addRoom(int roomNumber, int numberOfBeds, int size,int price,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony)
  {
   roomData.addNewRoom(roomNumber, numberOfBeds, size, price, orientation, internet, bathroom, kitchen, balcony);
  }

  @Override public ArrayList<Room> getAllRooms()
  {
    ArrayList<Room> test=roomData.getAllRooms();
    for (int i = 0; i < test.size(); i++)
    {
      System.out.println(test.get(i));
    }
    return roomData.getAllRooms();
  }
}
