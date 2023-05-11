package Server.Utility.DataBase.Employee;

import Server.Model.Employee;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeDataImplementation implements EmployeeData
{
  public EmployeeDataImplementation()
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

  @Override public Employee AddEmployee(String username, String password,
      String firstName, String lastName, String phoneNumber, String position)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement psUser = connection.prepareStatement(
          "INSERT INTO user(username, password) VALUES(?,?)");
      psUser.setString(1, username);
      psUser.setString(2, password);
      psUser.executeUpdate();

      PreparedStatement psEmpoyee = connection.prepareStatement(
          "INSERT INTO employee(username, password,firstName, lastName, String phoneNumber, String position");
      psEmpoyee.setString(1, username);
      psEmpoyee.setString(2,password);
      psEmpoyee.setString(3, firstName);
      psEmpoyee.setString(4, lastName);
      psEmpoyee.setString(5, phoneNumber);
      psEmpoyee.setString(6, position);
      return new Employee(username, password, firstName, lastName, phoneNumber, position);
    }
    catch (SQLException e)
    {
      return null;
    }
  }

  @Override public String editEmployee(String username, String password,
      String firstName, String lastName, String phoneNumber, String position)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(
          "UPDATE employee SET password = ?, firsName=?, lastName =?, phoneNumber =?, position=? ");
      ps.setString(1, password);
      ps.setString(2, firstName);
      ps.setString(3, lastName);
      ps.setString(4, phoneNumber);
      ps.setString(5, position);
      ps.executeUpdate();
      return "success";
    }
    catch (SQLException e)
    {
      System.out.println(e.getMessage());
      return "error";
    }
  }

  @Override public String deleteEmployee(String username)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(
          "DELETE FROM employee WHERE username =?");
      ps.setString(1, username);
      ps.executeUpdate();

      PreparedStatement preparedStatement = connection.prepareStatement(
          "DELETE FROM \"user\" WHERE username=?");
      preparedStatement.setString(1, username);
      preparedStatement.executeUpdate();
      return "success";
    }
    catch (SQLException e)
    {
      System.out.println(e.getMessage());
      return "error";
    }
  }

  @Override public ArrayList<Employee> getAllEmployees()
  {
    ArrayList<Employee> list= new ArrayList<>();
    try(Connection connection=getConnection()){
      PreparedStatement ps= connection.prepareStatement("SELECT * from employee join \"user\" on employee.username = \"user\".username");
      ResultSet rs= ps.executeQuery();
      while (rs.next()){
        String username= rs.getString("username");
        String password=rs.getString("password");
        String firstName= rs.getString("firstName");
        String lastName= rs.getString("lastName");
        String phoneNumber= rs.getString("phoneNumber");
        String position= rs.getString("position");
        list.add(new Employee(username,password,firstName,lastName,phoneNumber,position));
      }
    }
    catch (SQLException e)
    {
      System.out.println(e.getMessage());
    }
    return list;
  }
}




