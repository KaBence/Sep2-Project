package Server.Utility.DataBase.Customer;

import Server.Model.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    try(Connection connection = getConnection())
    {
      PreparedStatement psUser = connection.prepareStatement("INSERT INTO user(username, password) VALUES(?,?)");
      psUser.setString(1,username);
      psUser.setString(2,password);
      psUser.executeUpdate();

      PreparedStatement psCustomer = connection.prepareStatement("INSERT INTO employee(username, firstName, lastName, phoneNo, paymentInfo) VALUES(?,?,?,?,?)");
      psCustomer.setString(1,username);
      psCustomer.setString(2,firstName);
      psCustomer.setString(3,lastName);
      psCustomer.setString(4,phoneNO);
      psCustomer.setString(5,paymentInfo);
      return new Customer(username,firstName,lastName,phoneNO,paymentInfo);
    }
    catch (SQLException e)
    {
      //throw new RuntimeException(e);
      return null;
    }

  }

  @Override public String editCustomer()
  {
    return null;
  }

  @Override public String deleteCustomer()
  {
    return null;
  }

  @Override public ArrayList<Customer> getAllCustomers()
  {
    return null;
  }
}
