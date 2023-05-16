package Server.Utility.DataBase.Reservation;

import Server.Model.MyDate;
import Server.Model.Reservation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationDataImplementation implements ReservationData
{

  public ReservationDataImplementation()
  {
    try
    {
      DriverManager.registerDriver(new org.postgresql.Driver());
    }
    catch (SQLException e)
    {
      System.out.println(e.getMessage());
    }
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres?currentSchema=hostelreservation",
        "postgres", "password");
  }

  @Override public Reservation addNewReservation(int roomNumber,
      String username, MyDate fromDate, MyDate toDate, boolean CheckedIn)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(
          "INSERT INTO ReservedBy(roomNo, username, fromDate, toDate, checkedIn) VALUES(?,?,?,?,?)");
      ps.setInt(1, roomNumber);
      ps.setString(2, username);
      ps.setString(3, String.valueOf(fromDate));
      ps.setString(4, String.valueOf(toDate));
      ps.setBoolean(5, CheckedIn);
      ps.executeUpdate();
    }
    catch (SQLException ex)
    {
return null;
    }
    return new Reservation(roomNumber,username,fromDate,toDate,CheckedIn);
  }

  @Override public ArrayList<Reservation> getMyReservation(String username)
  {
    return null;
  }

  @Override public String updateReservation(int roomNumber, String username,
      MyDate fromDate, MyDate toDate)
  {
    return null;
  }

  @Override public ArrayList<Reservation> getAllRooms()
  {
    return null;
  }
}
