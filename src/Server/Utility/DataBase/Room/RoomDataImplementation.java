package Server.Utility.DataBase.Room;

import Server.Model.Hotel.Room;
import Server.Utility.DataBase.DatabaseConnection;

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

  @Override public String  addNewRoom(int roomNumber, int numberOfBeds, int size,int price,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony,String status) {
    if (orientation==null)
      return DatabaseConnection.MANDATORY;
    try (Connection connection = getConnection()) {
      PreparedStatement ps = connection.prepareStatement(
          "INSERT INTO room(roomNo, noBeds, size,price, orientation, internet, bathroom, kitchen, balcony,status) VALUES(?,?,?,?,?,?,?,?,?,?)");
      ps.setInt(1, roomNumber);
      ps.setInt(2, numberOfBeds);
      ps.setInt(3, size);
      ps.setInt(4,price);
      ps.setString(5, orientation);
      ps.setBoolean(6, internet);
      ps.setBoolean(7, bathroom);
      ps.setBoolean(8, kitchen);
      ps.setBoolean(9, balcony);
      ps.setString(10,status);

      ps.executeUpdate();
    }
    catch (SQLException ex) {
      return DatabaseConnection.ERROR;
    }
    return DatabaseConnection.SUCCESS;
  }

  @Override public String deleteRoom(int roomNumber)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(
          "DELETE FROM room WHERE roomNo = ?");
      ps.setInt(1, roomNumber);
      ps.executeUpdate();
      return DatabaseConnection.SUCCESS;
    }
    catch (SQLException ex)
    {
      return DatabaseConnection.ERROR;
    }
  }

  @Override public String updateRoom(int roomNumber, int numberOfBeds,
      int size,int price, String orientation, boolean internet, boolean bathroom,
      boolean kitchen, boolean balcony)
  {
    if (numberOfBeds<1||size<1)
      return DatabaseConnection.MANDATORY;
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
      return DatabaseConnection.SUCCESS;
    }
    catch (SQLException ex)
    {
      return DatabaseConnection.ERROR;
    }
  }

  @Override public ArrayList<Room> filterRoom(String room)
  {
    ArrayList<Room> list = filter((String) null);
    ArrayList<Room> filter = new ArrayList<>();
    for (Room item : list)
    {
      if (item.toString().contains(room))
      {
        filter.add(item);
      }
    }
    return filter;
  }

  @Override public ArrayList<Room> filter(String... attr)
  {
    ArrayList<Room> list = getAllRooms();
    ArrayList<Room> filter = new ArrayList<>();

    if (attr[0]==null)
      return list;
    for (Room room : list)
    {
      boolean temp=true;
      for (String s : attr)
      {
        if (s==null)
          break;
        if (!room.roomInfo().contains(s)){
          temp=false;
          break;
        }
      }
      if (temp)
        filter.add(room);
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
        String status=rs.getString("status");

        list.add(new Room(roomNumber, numberOfBeds, size, price, orientation, internet,
            bathroom, kitchen, balcony,status));
      }
    }
    catch (SQLException e)
    {
      return null;
    }
    return list;
  }
}
