package Server.Utility.DataBase.Room;

import Server.Model.Room;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RoomData
{
  Room addNewRoom(int roomNumber,int numberOfBeds, int size,int price, String orientation,boolean internet,boolean bathroom,boolean kitchen,boolean balcony);
  Room deleteRoom(int roomNumber);
  Room updateRoom(int roomNumber,int numberOfBeds, int size,int price, String orientation,boolean internet,boolean bathroom,boolean kitchen,boolean balcony);
  ArrayList<Room> filter(String room);
  ArrayList<Room> getAllRooms();
}
