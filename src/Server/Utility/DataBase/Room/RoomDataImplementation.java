package Server.Utility.DataBase.Room;

import Server.Model.Room;

import java.sql.*;
import java.util.ArrayList;

public class RoomDataImplementation implements RoomData
{
  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres?currentSchema= !!!!!!!!!!!",
        "postgres", "!!!!!!!!!");
  }

  @Override public Room addNewRoom(int roomNumber, int numberOfBeds, int size,int price,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(
          "INSERT INTO game(....) VALUES(?,?,?,?,?,?,?,?,?)");
      ps.setInt(1, roomNumber);
      ps.setInt(2, numberOfBeds);
      ps.setInt(3, size);
      ps.setInt(4,price);
      ps.setString(5, orientation);
      ps.setBoolean(6, internet);
      ps.setBoolean(7, bathroom);
      ps.setBoolean(8, kitchen);
      ps.setBoolean(9, balcony);

      ps.executeUpdate();
    }
    catch (SQLException ex)
    {
      System.err.println(ex.getMessage());
    }
    return new Room(roomNumber, numberOfBeds, size,price, orientation, internet,
        bathroom, kitchen, balcony);
  }

  @Override public Room deleteRoom(Room room)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(
          "DELETE FROM (....) WHERE (...) = ?");
      ps.setInt(1, room.getRoomNo());
      ps.executeUpdate();
    }
    catch (SQLException ex)
    {
      System.err.println(ex.getMessage());
    }
    return room;
  }

  @Override public Room updateRoom(Room room, int roomNumber, int numberOfBeds,
      int size,int price, String orientation, boolean internet, boolean bathroom,
      boolean kitchen, boolean balcony)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(
          "UPDATE  (....) SET (...) = ?,(...) = ?,(...) = ?,(...) = ?,(...) = ?,(...) = ?,(...) = ?,(...) = ?,(...) = ?, WHERE (...) = ?");
      ps.setInt(1, roomNumber);
      ps.setInt(2, numberOfBeds);
      ps.setInt(3, size);
      ps.setInt(4,price);
      ps.setString(5, orientation);
      ps.setBoolean(6, internet);
      ps.setBoolean(7, bathroom);
      ps.setBoolean(8, kitchen);
      ps.setBoolean(9, balcony);
      ps.setInt(10, room.getRoomNo());
      ps.executeUpdate();
    }
    catch (SQLException ex)
    {
      System.err.println(ex.getMessage());
    }
    return new Room(roomNumber, numberOfBeds, size, price, orientation, internet,
        bathroom, kitchen, balcony);
  }

  @Override public ArrayList<Room> filter(String room)
  {
    ArrayList<Room> list = getAllRooms();
    ArrayList<Room> filter = new ArrayList<>();
    for (int i = 0; i < list.size(); i++)
    {
      if (list.get(i).toString().contains(room))
      {
        filter.add(list.get(i));
      }
    }
    return filter;
  }

  @Override public ArrayList<Room> getAllRooms()
  {
    ArrayList<Room> list = new ArrayList<>();

    try (Connection connection = getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(
          "Select * from ........");
      ResultSet rs = ps.executeQuery();

      while (rs.next())
      {
        int roomNumber = rs.getInt("");
        int numberOfBeds = rs.getInt("");
        int size = rs.getInt("");
        int price =rs.getInt(" ");
        String orientation = rs.getString("");
        boolean internet = rs.getBoolean("");
        boolean bathroom = rs.getBoolean("");
        boolean kitchen = rs.getBoolean("");
        boolean balcony = rs.getBoolean("");

        list.add(new Room(roomNumber, numberOfBeds, size, price, orientation, internet,
            bathroom, kitchen, balcony));
      }
    }
    catch (SQLException e)
    {
      System.err.println(e.getMessage());
    }
    return list;
  }
}