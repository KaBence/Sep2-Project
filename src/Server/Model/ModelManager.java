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

  @Override public Room addRoom(int roomNumber, int numberOfBeds, int size,int price,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony)
  {
   return roomData.addNewRoom(roomNumber, numberOfBeds, size, price, orientation, internet, bathroom, kitchen, balcony);
  }

  @Override public String updateRoom(int roomNumber, int numberOfBeds, int size,
      int price, String orientation, boolean internet, boolean bathroom,
      boolean kitchen, boolean balcony)
  {
    return roomData.updateRoom(roomNumber,numberOfBeds,size,price,orientation,internet,bathroom,kitchen,balcony);
  }

  @Override public String deleteRoom(int roomNumber)
  {
    return roomData.deleteRoom(roomNumber);
  }

  @Override public ArrayList<Room> getAllRooms()
  {
    return roomData.getAllRooms();
  }

  @Override public ArrayList<Customer> getAllCustomers()
  {
    return null;
  }

  @Override public ArrayList<Employee> getAllEmployees()
  {
    return null;
  }
}
