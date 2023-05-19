package Server.Utility.DataBase.Reservation;

import Server.Model.Hotel.Users.Employee;
import Server.Model.MyDate;
import Server.Model.Hotel.Reservation;
import Server.Utility.DataBase.DatabaseConnection;
import Server.Utility.IllegalDateException;

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

  @Override public synchronized String addNewReservation(int roomNumber,
      String username, MyDate fromDate, MyDate toDate, boolean CheckedIn)
  {
    dateChecker(roomNumber, fromDate, toDate);
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
      return DatabaseConnection.SUCCESS;
    }
    catch (SQLException ex)
    {
      System.err.println(ex.getMessage());
      return DatabaseConnection.ERROR;
    }
    catch (ParseException e)
    {
      throw new RuntimeException(e);
    }

  }

  private synchronized void dateChecker(int roomNumber, MyDate from, MyDate to)
  {
    ArrayList<Reservation> all = getAllReservations();
    ArrayList<Reservation> specific = new ArrayList<>();

    for (Reservation item : all)
    {
      if (item.getRoomNumber() == roomNumber)
        specific.add(item);
    }
    for (Reservation item : specific)
    {
      //dates are the same
      if (from.equals(item.getFromDate()) && to.equals(item.getToDate()))
      {
        throw new IllegalDateException(2);
      }
      //the dates are outside a reservation
      if (from.isBefore(item.getFromDate()) && item.getToDate().isBefore(to))
        throw new IllegalDateException(1);
      //either dates are the same
      if (from.equals(item.getFromDate()) || to.equals(item.getToDate()))
        throw new IllegalDateException(3);
      //from date in between and to date is after
      if (item.getFromDate().isBefore(from) && item.getToDate().isBefore(to)
          && from.isBefore(item.getToDate()))
        throw new IllegalDateException(4);
      //Both between
      if (item.getFromDate().isBefore(from) && to.isBefore(item.getToDate()))
        throw new IllegalDateException(5);
      //from is before and return in between
      if (from.isBefore(item.getFromDate()) && to.isBefore(item.getToDate())
          && !to.isBefore(item.getFromDate()))
        throw new IllegalDateException(6);
      //from is before and to is equals
      if (from.isBefore(item.getFromDate()) && to.equals(item.getToDate()))
        throw new IllegalDateException(7);
    }
  }

  @Override public ArrayList<Reservation> getMyReservation(String username)
  {
    ArrayList<Reservation> list = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(
          "SELECT * from ReservedBy WHERE username= ?");
      ResultSet rs = ps.executeQuery();
      while (rs.next())
      {
        int roomNumber = rs.getInt("roomNo");
        username = rs.getString("username");
        MyDate fromDate = MyDate.stringToDate(rs.getString("fromDate"));
        MyDate toDate = MyDate.stringToDate(rs.getString("toDate"));
        Boolean CheckedIn = rs.getBoolean("checkedIn");
        list.add(
            new Reservation(roomNumber, username, fromDate, toDate, CheckedIn));
      }
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    return list;
  }

  @Override public String updateReservation(int roomNumber, String username,
      MyDate fromDate, MyDate toDate, int oldRoomNo, String oldUsername,
      MyDate oldFromDate)
  {
    if (username.equals("") || fromDate == null || toDate == null)
      return DatabaseConnection.MANDATORY;
    try (Connection connection = getConnection())
    {

      PreparedStatement ps = connection.prepareStatement(
          "UPDATE ReservedBy SET roomNo =?, username=?, fromDate=?, toDate=? WHERE roomNo=? and username=? and fromDate=?");
      ps.setInt(1, roomNumber);
      ps.setString(2, username);
      ps.setDate(3, convertToSQLDate(fromDate.toString()));
      ps.setDate(4, convertToSQLDate(toDate.toString()));
      ps.setInt(5, oldRoomNo);
      ps.setString(6, oldUsername);
      ps.setDate(7, convertToSQLDate(oldFromDate.toString()));
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
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
    java.util.Date date1 = sdf1.parse(date);
    return new Date(date1.getTime());
  }

  @Override public String deleteReservation(int roomNumber, String username,
      MyDate fromDate)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(
          "DELETE FROM ReservedBy WHERE roomNo = ? AND username = ? AND fromDate = ?");
      ps.setInt(1, roomNumber);
      ps.setString(2, username);
      ps.setDate(3, convertToSQLDate(fromDate.toString()));
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

  @Override public synchronized ArrayList<Reservation> getAllReservations()
  {
    ArrayList<Reservation> list = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(
          "SELECT * from ReservedBy");
      ResultSet rs = ps.executeQuery();
      while (rs.next())
      {
        int roomNumber = rs.getInt("roomNo");
        String username = rs.getString("username");
        MyDate fromDate = MyDate.stringToDate(rs.getString("fromDate"));
        MyDate toDate = MyDate.stringToDate(rs.getString("toDate"));
        Boolean CheckedIn = rs.getBoolean("checkedIn");

        list.add(
            new Reservation(roomNumber, username, fromDate, toDate, CheckedIn));

      }
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    return list;
  }

  @Override public ArrayList<Reservation> filterReservation(String reservation)
  {
    ArrayList<Reservation> list = getAllReservations();
    ArrayList<Reservation> filter = new ArrayList<>();
    for (int i = 0; i < list.size(); i++)
    {
      if (list.get(i).toString().toLowerCase()
          .contains(reservation.toLowerCase()))
      {
        filter.add(list.get(i));
      }
    }
    return filter;
  }

  @Override public ArrayList<Reservation> getFilteredReservations(String state,
      MyDate fromDate, MyDate toDate)
  {
    ArrayList<Reservation> all = getAllReservations();
    ArrayList<Reservation> filtered = new ArrayList<>();
    boolean flag = true;
    if (fromDate == null && toDate == null)
      flag = false;
    for (Reservation item : all)
    {
      boolean temp = true;
      if (!state.equals("all"))
      {
        if (!item.getState().equals(state) || item.getState()
            .equals("In The Past"))
        {
          temp = false;
        }
      }
      if (flag)
      {
        if (!(item.getFromDate().isBefore(fromDate) && toDate.isBefore(
            item.getToDate())))
        {
          temp = false;
        }
      }
      if (temp)
        filtered.add(item);
    }
    return filtered;
  }

  @Override public String checkIn(int roomNumber, String username,
      MyDate fromDate)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(
          "UPDATE ReservedBy SET checkedin = ? WHERE roomNo=? and username=? and fromDate=?");

      ps.setBoolean(1, true);
      ps.setInt(2, roomNumber);
      ps.setString(3, username);
      ps.setDate(4, convertToSQLDate(fromDate.toString()));
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

  @Override public String checkOut(int roomNumber, String username,
      MyDate fromDate)
  {
    try (Connection connection = getConnection())
    {

      PreparedStatement ps = connection.prepareStatement(
          "UPDATE ReservedBy SET checkedin = ? WHERE roomNo=? and username=? and fromDate=?");
      ps.setObject(1, null);
      ps.setInt(2, roomNumber);
      ps.setString(3, username);
      ps.setDate(4, convertToSQLDate(fromDate.toString()));
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

}
