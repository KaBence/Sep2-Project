package Server.Utility.DataBase.Room;

import Server.Model.Room;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RoomData
{
  Connection getConnection() throws SQLException;
  Room addNewRoom(int roomNumber,int numberOfBeds, int size, String orientation,boolean internet,boolean bathroom,boolean kitchen,boolean balcony);
  ArrayList<Room> filter(String room);
  ArrayList<Room> getAllRooms();
}
