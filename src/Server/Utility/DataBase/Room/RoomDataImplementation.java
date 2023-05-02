package Server.Utility.DataBase.Room;

import Server.Model.Room;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDataImplementation implements RoomData
{
  @Override public Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres?currentSchema= !!!!!!!!!!!",
        "postgres", "!!!!!!!!!");
  }

  @Override public Room addNewRoom(int roomNumber, int numberOfBeds, int size,
      String orientation, boolean internet, boolean tv, boolean bathroom,
      boolean kitchen, boolean disable, boolean ac, boolean balcony)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement ps =
          connection.prepareStatement("INSERT INTO game(....) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
      ps.setInt(1, roomNumber);
      ps.setInt(2, numberOfBeds);
      ps.setInt(3, size);
      ps.setString(4, orientation);
      ps.setBoolean(5, internet);
      ps.setBoolean(6, tv);
      ps.setBoolean(7, bathroom);
      ps.setBoolean(8, kitchen);
      ps.setBoolean(9, disable);
      ps.setBoolean(10, ac);
      ps.setBoolean(11, balcony);

      ps.executeUpdate();
      return new Room(roomNumber, numberOfBeds, size, orientation, internet, tv,
          bathroom, kitchen, disable, ac, balcony);
    }
    catch (SQLException ex)
    {
      throw new RuntimeException(ex);
    }
  }

  @Override public Room getRoom(Room room)
  {
    return null;
  }

  @Override public ArrayList<Room> getAllRooms()
  {
    return null;
  }
}
