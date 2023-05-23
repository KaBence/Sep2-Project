package Server.Utility.DataBase.Employee;

import Server.Model.Hotel.Users.Employee;
import Server.Utility.DataBase.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeDataImplementation implements EmployeeData
{
  private Employee newEmp;
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

  @Override public Employee getNewEmployee()
  {
    return newEmp;
  }

  @Override public String AddEmployee(String password,
      String firstName, String lastName, String phoneNumber, String position)
  {
    Employee newEmployee = new Employee(firstName,lastName,position,phoneNumber,password);
    String username = newEmployee.getUsername();
    newEmp = newEmployee;
    try (Connection connection = getConnection())
    {
      PreparedStatement psUser = connection.prepareStatement(
          "INSERT INTO \"user\"(username, password) VALUES(?,?)");
      psUser.setString(1, username);
      psUser.setString(2, password);
      psUser.executeUpdate();

      PreparedStatement psEmpoyee = connection.prepareStatement(
          "INSERT INTO employee(username,firstName, lastName, phoneno,position) values(?,?,?,?,?)");
      psEmpoyee.setString(1, username);
      psEmpoyee.setString(2, firstName);
      psEmpoyee.setString(3, lastName);
      psEmpoyee.setString(4, phoneNumber);
      psEmpoyee.setString(5, position);
      psEmpoyee.executeUpdate();
      return DatabaseConnection.SUCCESS;
    }
    catch (SQLException e)
    {
      return DatabaseConnection.ERROR;
    }
  }

  @Override public String editEmployee(String username, String firstName,
      String lastName, String phoneNumber, String position)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(
          "UPDATE employee SET firstName=?, lastName =?, phoneNo =?, position=? WHERE username = ? ");
      //ps.setString(1, password);
      ps.setString(1, firstName);
      ps.setString(2, lastName);
      ps.setString(4, phoneNumber);
      ps.setString(3, position);
      ps.setString(5, username);
      if (username.equals("")||firstName.equals("")||lastName.equals("")||phoneNumber.equals("")||position.equals(""))
      {
        return DatabaseConnection.MANDATORY;
      }
      else
      {
        ps.executeUpdate();
        return DatabaseConnection.SUCCESS;
      }
    }
    catch (SQLException e)
    {
      return DatabaseConnection.ERROR;
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
      return DatabaseConnection.SUCCESS;
    }
    catch (SQLException e)
    {
      return DatabaseConnection.ERROR;
    }
  }

  @Override public ArrayList<Employee> filter(String... attr)
  {
    ArrayList<Employee> list = getAllEmployees();
    ArrayList<Employee> filter = new ArrayList<>();

    if (attr[0]==null)
      return list;
    for (Employee employee : list)
    {
      boolean temp=true;
      for (String s : attr)
      {
        if (s==null)
          break;
        if (!employee.employeeInfo().contains(s)){
          temp=false;
          break;
        }
      }
      if (temp)
        filter.add(employee);
    }
    return filter;
  }

  @Override public ArrayList<Employee> filterEmployee(String employee)
  {
    ArrayList<Employee> list = getAllEmployees();
    ArrayList<Employee> filter = new ArrayList<>();
    for (int i = 0; i < list.size(); i++)
    {
      if (list.get(i).toString().toLowerCase().contains(employee.toLowerCase()))
      {
        filter.add(list.get(i));
      }
    }
    return filter;
  }

  @Override public ArrayList<Employee> getAllEmployees()
  {
    ArrayList<Employee> list = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      PreparedStatement ps = connection.prepareStatement(
          "SELECT * from employee join \"user\" on employee.username = \"user\".username");
      ResultSet rs = ps.executeQuery();
      while (rs.next())
      {
        String username = rs.getString("username");
        String password = rs.getString("password");
        String firstName = rs.getString("firstName");
        String lastName = rs.getString("lastName");
        String phoneNumber = rs.getString("phoneNo");
        String position = rs.getString("position");
        list.add(
            new Employee(username, firstName, lastName, position, phoneNumber,
                password));
      }
    }
    catch (SQLException e)
    {
      return null;
    }
    return list;
  }
}




