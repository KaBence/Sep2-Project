package Server.Utility.DataBase.Reservation;

import Server.Model.MyDate;
import Server.Model.Reservation;
import Server.Utility.DataBase.DatabaseConnection;
import org.mockito.internal.matchers.GreaterThan;

import java.sql.*;
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
    return new Reservation(roomNumber, username, fromDate, toDate, CheckedIn);
  }

  @Override public ArrayList<Reservation> getMyReservation(String username)
  {
    ArrayList<Reservation> list=new ArrayList<>();
    try(Connection connection=getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(
          "SELECT * from ReservedBy WHERE username= ?"
      );
      ResultSet rs=ps.executeQuery();
      while (rs.next()){
        int roomNumber= rs.getInt("roomNo");
         username= rs.getString("username");
        MyDate fromDate= MyDate.stringToDate(rs.getString("fromDate"));
        MyDate toDate= MyDate.stringToDate(rs.getString("toDate"));
        Boolean CheckedIn= rs.getBoolean("checkedIn");
        list.add(new Reservation(roomNumber,username,fromDate,toDate,CheckedIn));
      }
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    return list;
  }


  @Override public String updateReservation(int roomNumber, String username,
      MyDate fromDate, MyDate toDate, boolean CheckedIn)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(
          "UPDATE ReservedBy SET roomNo =?, username=?, fromDate=?, toDate=?, checkedIn=? WHERE roomNo=? and username=? and fromDate=?");
      ps.setInt(1, roomNumber );
      ps.setString(2,username);
      ps.setString(3, String.valueOf(fromDate));
      ps.setString(4, String.valueOf(toDate));
      ps.setBoolean(5, CheckedIn);
      ps.executeUpdate();
      return DatabaseConnection.SUCCESS;
    }
    catch (SQLException e)
    {
      return DatabaseConnection.ERROR;
    }
  }

  @Override public ArrayList<Reservation> getAllRooms()
  {
    ArrayList<Reservation> list=new ArrayList<>();
    try(Connection connection=getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(
          "SELECT * from ReservedBy"
      );
      ResultSet rs=ps.executeQuery();
      while (rs.next()){
        int roomNumber= rs.getInt("roomNo");
        String username= rs.getString("username");
        MyDate fromDate= MyDate.stringToDate(rs.getString("fromDate"));
        MyDate toDate= MyDate.stringToDate(rs.getString("toDate"));
        Boolean CheckedIn= rs.getBoolean("checkedIn");
        list.add(new Reservation(roomNumber,username,fromDate,toDate,CheckedIn));
      }
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    return list;
  }
}
