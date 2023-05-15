package Server.Utility.DataBase.Room;

import Server.Model.Room;

import java.util.ArrayList;

public interface RoomData
{

  static final String SUCCESS="success";
  static final String ERROR="error";

  static final String MANDATORY="mandatory";
  Room addNewRoom(int roomNumber,int numberOfBeds, int size,int price, String orientation,boolean internet,boolean bathroom,boolean kitchen,boolean balcony);
  String deleteRoom(int roomNumber);
  String updateRoom(int roomNumber,int numberOfBeds, int size,int price, String orientation,boolean internet,boolean bathroom,boolean kitchen,boolean balcony);
  ArrayList<Room> filter(String... attr);
  ArrayList<Room> getAllRooms();
}
