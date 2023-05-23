package Server.Utility.DataBase.Employee;


import Server.Model.Hotel.Users.Employee;

import java.util.ArrayList;

public interface EmployeeData
{
  Employee getNewEmployee();
  String AddEmployee(String password,String firstName, String lastName, String phoneNumber, String position);
  String editEmployee(String username, String firstName, String lastName, String phoneNumber, String position);
  String deleteEmployee(String username);
  ArrayList<Employee> filter(String... attr);
  ArrayList<Employee> filterEmployee(String employee);
  ArrayList<Employee> getAllEmployees();
}
