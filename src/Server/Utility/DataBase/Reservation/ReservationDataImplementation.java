package Server.Utility.DataBase.Reservation;

import Server.Model.MyDate;
import Server.Model.Hotel.Reservation;
import Server.Utility.DataBase.DatabaseConnection;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
      ps.setDate(3, convertToSQLDate(fromDate.toString()));
      ps.setDate(4, convertToSQLDate(toDate.toString()));
      ps.setBoolean(5, CheckedIn);
      ps.executeUpdate();
    }
    catch (SQLException ex)
    {
      System.err.println(ex.getMessage());
      return null;
    }
    catch (ParseException e)
    {
      throw new RuntimeException(e);
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
      MyDate fromDate, MyDate toDate)
  {
    try (Connection connection = getConnection())
    {

      PreparedStatement ps = connection.prepareStatement(
          "UPDATE ReservedBy SET roomNo =?, username=?, fromDate=?, toDate=? WHERE roomNo=? and username=? and fromDate=?");
      ps.setInt(1, roomNumber );
      ps.setString(2,username);
      ps.setDate(3,convertToSQLDate(fromDate.toString()));
      ps.setDate(4, convertToSQLDate(toDate.toString()));
      ps.setInt(5, roomNumber);
      ps.setString(6,username);
      ps.setDate(7,convertToSQLDate(fromDate.toString()));
      ps.executeUpdate();
      return DatabaseConnection.SUCCESS;
    }
    catch (SQLException e)
    {
      System.out.println(e.getMessage());
      return DatabaseConnection.ERROR;
    }
    catch (ParseException e)
    {
      throw new RuntimeException(e);
    }
  }

  private Date convertToSQLDate(String date) throws ParseException
  {
    SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
    java.util.Date date1=sdf1.parse(date);
    return new Date(date1.getTime());
  }

  @Override public String deleteReservation(int roomNumber,String username,
      MyDate fromDate)
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(
          "DELETE FROM ReservedBy WHERE roomNo = ? AND username = ? AND fromDate = ?");
      ps.setInt(1, roomNumber);
      ps.setString(2,username);
      Date x = new Date(fromDate.getYear(), fromDate.getMonth(), fromDate.getDay());
      ps.setDate(3,x);
      ps.executeUpdate();
     return DatabaseConnection.SUCCESS;
    }
    catch (SQLException e)
    {
      System.out.println(e.getMessage());
      return DatabaseConnection.ERROR;
    }
  }

  @Override public ArrayList<Reservation> getAllReservations()
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

  @Override public ArrayList<Reservation> getFilteredReservations(String state,
      MyDate fromDate, MyDate toDate)
  {
    ArrayList<Reservation> all=getAllReservations();
    ArrayList<Reservation> filtered=new ArrayList<>();
    boolean flag=true;
    if (state.equals("all"))
      return all;
    if (fromDate==null&&toDate==null)
      flag=false;
    for (Reservation item: all){
      boolean temp=true;
      if (!item.getState().equals(state)){
        temp=false;
        break;
      }
      if (flag){
        if (!(item.getFromDate().isBefore(fromDate)&& toDate.isBefore(item.getToDate()))){
          temp=false;
          break;
        }
      }
      if (temp)
        filtered.add(item);
    }
    return filtered;
  }

}
