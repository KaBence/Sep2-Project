package Server.Utility.DataBase.Review;

import Server.Model.Hotel.Review;
import Server.Model.MyDate;
import Server.Utility.DataBase.DatabaseConnection;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ReviewDataImplementation implements ReviewData
{

  public ReviewDataImplementation()
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

  private Date convertToSQLDate(String date) throws ParseException
  {
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
    java.util.Date date1 = sdf1.parse(date);
    return new Date(date1.getTime());
  }

  @Override public String addReview(String username, int roomNO,
      MyDate fromDate, MyDate postedDate, String comment)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(
          "INSERT INTO Review(username, roomNo,fromDate, postedDate,comment) VALUES(?,?,?,?,?)");
      ps.setString(1, username);
      ps.setInt(2, roomNO);
      ps.setDate(3, convertToSQLDate(fromDate.toString()));
      ps.setDate(4, convertToSQLDate(postedDate.toString()));
      ps.setString(5, comment);
      ps.executeUpdate();
    }
    catch (SQLException e)
    {
      return DatabaseConnection.ERROR;
    }
    catch (ParseException e)
    {
      System.out.println(e.getMessage());
    }
    return DatabaseConnection.SUCCESS;
  }

  @Override public ArrayList<Review> getAllReviews()
  {
    ArrayList<Review> list = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(
          "Select * FROM Review");
      ResultSet rs = ps.executeQuery();
      while(rs.next()){
        String username= rs.getString("username");
        //ignore ID
        int roomNo= rs.getInt("roomNo");
        MyDate fromDate= MyDate.stringToDate(rs.getString("fromDate"));
        MyDate postedDate= MyDate.stringToDate(rs.getString("postedDate"));
        String review= rs.getString("comment");
        list.add(new Review(username,roomNo,fromDate,postedDate,review));
      }
    }
    catch (SQLException ex)
    {
      throw new RuntimeException(ex);
    }
    return  list;
  }
}
