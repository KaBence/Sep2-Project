package Server.Utility.DataBase.Customer;

import Server.Model.Hotel.Users.Customer;
import Server.Utility.DataBase.DatabaseConnection;

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
      psCustomer.executeUpdate();

      return new Customer(username, firstName, lastName, phoneNO, paymentInfo,
          password);
    }
    catch (SQLException e)
    {
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
      return DatabaseConnection.SUCCESS;
    }
    catch (SQLException e)
    {
      return DatabaseConnection.ERROR;
    }
  }

  @Override public String editCustomer(String username, String firstName,
      String lastName, String phoneNO, String paymentInfo)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement psCustomer = connection.prepareStatement(
          "UPDATE customer SET firstname = ?, lastname = ?, phoneno = ?, paymentinfo = ? WHERE username = ?");
      psCustomer.setString(1, firstName);
      psCustomer.setString(2, lastName);
      psCustomer.setString(3, phoneNO);
      psCustomer.setString(4, paymentInfo);
      psCustomer.setString(5, username);

      if (username.equals("")||firstName.equals("")||lastName.equals("")||phoneNO.equals("")||paymentInfo.equals(""))
      {
        return DatabaseConnection.MANDATORY;
      }
      else
      {
        psCustomer.executeUpdate();
        return DatabaseConnection.SUCCESS;
      }
    }
    catch (SQLException e)
    {
      return DatabaseConnection.ERROR;
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
        String username = rs.getString("username");
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

  @Override public ArrayList<Customer> filterCustomers(String customer)
  {
    ArrayList<Customer> list = getAllCustomers();
    ArrayList<Customer> filter = new ArrayList<>();
    for (int i = 0; i < list.size(); i++)
    {
      if (list.get(i).toString().toLowerCase().contains(customer.toLowerCase()))
      {
        filter.add(list.get(i));
      }
    }
    return filter;
  }

  @Override public ArrayList<Customer> filter(String... attr)
  {
    ArrayList<Customer> list = getAllCustomers();
    ArrayList<Customer> filter = new ArrayList<>();
    if (attr[0] == null)
    {
      return list;
    }
    for (Customer customer : list)
    {
      System.out.println(customer);
      boolean temp = true;
      for (String s : attr)
      {
        if (s == null)
        {
          break;
        }
        if(!customer.customerInfo().contains(s)){
          temp=false;
          break;
        }
      }
      if(temp){
        filter.add(customer);
      }

    }return filter;
  }

}
