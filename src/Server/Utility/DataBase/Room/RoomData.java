package Server.Utility.DataBase.Room;

import Server.Model.Hotel.Room;

import java.util.ArrayList;

public interface RoomData
{
  String  addNewRoom(int roomNumber,int numberOfBeds, int size,int price, String orientation,boolean internet,boolean bathroom,boolean kitchen,boolean balcony,String status);
  String deleteRoom(int roomNumber);
  String updateRoom(int roomNumber,int numberOfBeds, int size,int price, String orientation,boolean internet,boolean bathroom,boolean kitchen,boolean balcony);

  ArrayList<Room> filterRoom(String room);
  ArrayList<Room> filter(String... attr);
  ArrayList<Room> getAllRooms();
}
