package Server.Utility.DataBase.Room;

import Server.Model.Room;
import Server.Utility.DataBase.DatabaseConnection;

import java.util.ArrayList;

public interface RoomData
{
  Room addNewRoom(int roomNumber,int numberOfBeds, int size,int price, String orientation,boolean internet,boolean bathroom,boolean kitchen,boolean balcony);
  String deleteRoom(int roomNumber);
  String updateRoom(int roomNumber,int numberOfBeds, int size,int price, String orientation,boolean internet,boolean bathroom,boolean kitchen,boolean balcony);

  ArrayList<Room> filterRoom(String room);
  ArrayList<Room> filter(String... attr);
  ArrayList<Room> getAllRooms();
}
