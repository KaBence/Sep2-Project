package Server.Utility.DataBase.Room;

import Server.Model.Room;

import java.sql.*;
import java.util.ArrayList;

public class RoomDataImplementation implements RoomData
{

  public RoomDataImplementation(){
    try
    {
      DriverManager.registerDriver(new org.postgresql.Driver());
    }
    catch (SQLException e)
    {
      System.err.println(e.getMessage());
    }
  }
  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres?currentSchema=hostelreservation",
        "postgres", "password");
  }

  @Override public Room addNewRoom(int roomNumber, int numberOfBeds, int size,int price,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(
          "INSERT INTO room(roomNo, noBeds, size,price, orientation, internet, bathroom, kitchen, balcony) VALUES(?,?,?,?,?,?,?,?,?)");
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
      return null;
    }
    return new Room(roomNumber, numberOfBeds, size,price, orientation, internet,
        bathroom, kitchen, balcony);
  }

  @Override public String deleteRoom(int roomNumber)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(
          "DELETE FROM room WHERE roomNo = ?");
      ps.setInt(1, roomNumber);
      ps.executeUpdate();
      return "success";
    }
    catch (SQLException ex)
    {
      System.err.println(ex.getMessage());
      return "error";
    }
  }

  @Override public String updateRoom(int roomNumber, int numberOfBeds,
      int size,int price, String orientation, boolean internet, boolean bathroom,
      boolean kitchen, boolean balcony)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(
          "UPDATE  room SET noBeds = ?,size = ?,price = ?,orientation = ?,internet = ?,bathroom = ?,kitchen = ?,balcony = ? WHERE roomNo = ?");
      ps.setInt(1, numberOfBeds);
      ps.setInt(2, size);
      ps.setInt(3,price);
      ps.setString(4, orientation);
      ps.setBoolean(5, internet);
      ps.setBoolean(6, bathroom);
      ps.setBoolean(7, kitchen);
      ps.setBoolean(8, balcony);
      ps.setInt(9, roomNumber);
      ps.executeUpdate();
      return "success";
    }
    catch (SQLException ex)
    {
      System.err.println(ex.getMessage());
      return "error";
    }
  }

  @Override public ArrayList<Room> filter(String room)
  {
    ArrayList<Room> list = getAllRooms();
    ArrayList<Room> filter = new ArrayList<>();
    for (int i = 0; i < list.size(); i++)
    {
      if (list.get(i).roomInfo().contains(room))
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
          "Select * from room");
      ResultSet rs = ps.executeQuery();
      while (rs.next())
      {
        int roomNumber = rs.getInt("roomNo");
        int numberOfBeds = rs.getInt("noBeds");
        int size = rs.getInt("size");
        int price =rs.getInt("price");
        String orientation = rs.getString("orientation");
        boolean internet = rs.getBoolean("internet");
        boolean bathroom = rs.getBoolean("bathroom");
        boolean kitchen = rs.getBoolean("kitchen");
        boolean balcony = rs.getBoolean("balcony");

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
