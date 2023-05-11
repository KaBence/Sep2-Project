package Server.Utility.DataBase.Customer;

import Server.Model.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDataImplementation implements CustomerData
{
  public CustomerDataImplementation()
  {
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

  @Override public Customer addCustomer(String username, String password,
      String firstName, String lastName, String phoneNO, String paymentInfo)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement psUser = connection.prepareStatement(
          "INSERT INTO \"user\"(username, password) VALUES(?,?)");
      psUser.setString(1, username);
      psUser.setString(2, password);
      psUser.executeUpdate();

      PreparedStatement psCustomer = connection.prepareStatement(
          "INSERT INTO customer(username, firstName, lastName, phoneNo, paymentInfo) VALUES(?,?,?,?,?)");
      psCustomer.setString(1, username);
      psCustomer.setString(2, firstName);
      psCustomer.setString(3, lastName);
      psCustomer.setString(4, phoneNO);
      psCustomer.setString(5, paymentInfo);
      return new Customer(username, firstName, lastName, phoneNO, paymentInfo,
          password);
    }
    catch (SQLException e)
    {
      //throw new RuntimeException(e);
      return null;
    }
  }

  @Override public String deleteCustomer(String username)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement psCustomer = connection.prepareStatement(
          "DELETE FORM customer WHERE username = ?");
      psCustomer.setString(1, username);
      psCustomer.executeUpdate();

      PreparedStatement psUser = connection.prepareStatement(
          "DELETE FORM \"user\" WHERE username = ?");
      psUser.setString(1, username);
      psUser.executeUpdate();
      return "success";
    }
    catch (SQLException e)
    {
      //throw new RuntimeException(e);
      return "error";
    }
  }

  @Override public String editCustomer(String username, String password,
      String firstName, String lastName, String phoneNO, String paymentInfo)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement psCustomer = connection.prepareStatement(
          "UPDATE customer SET firstName = ?, lastName = ?, phoneNo = ?, paymentInfo = ? WHERE username = ?");
      psCustomer.setString(1, firstName);
      psCustomer.setString(2, lastName);
      psCustomer.setString(3, phoneNO);
      psCustomer.setString(4, paymentInfo);
      psCustomer.setString(5, username);
      psCustomer.executeUpdate();

      PreparedStatement psUser = connection.prepareStatement(
          "UPDATE \"user\" SET password = ? WHERE username = ?");
      psUser.setString(1, password);
      psUser.setString(2, username);
      return "success";
    }
    catch (SQLException e)
    {
      //throw new RuntimeException(e);
      return "error";
    }
  }

  @Override public ArrayList<Customer> getAllCustomers()
  {
    ArrayList<Customer> list = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(
          "SELECT * FROM customer JOIN \"user\" ON customer.username = \"user\".username");
      ResultSet rs = ps.executeQuery();
      while (rs.next())
      {
        String username = rs.getString("customer.username");
        String firstName = rs.getString("firstName");
        String lastName = rs.getString("lastName");
        String phoneNO = rs.getString("phoneNo");
        String paymentInfo = rs.getString("paymentInfo");
        String password = rs.getString("password");
        list.add(
            new Customer(username, firstName, lastName, phoneNO, paymentInfo,
                password));
      }
      return list;
    }
    catch (SQLException e)
    {
      return null;

    }
  }
}
