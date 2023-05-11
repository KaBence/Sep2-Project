package Server.Utility.DataBase.Employee;


import Server.Model.Employee;

import java.util.ArrayList;

public interface EmployeeData
{
  Employee AddEmployee(String username, String password,String firstName, String lastName, String phoneNumber, String position);
  String editEmployee(String username, String password,String firstName, String lastName, String phoneNumber, String position);
  String deleteEmployee(String username);
  ArrayList<Employee> getAllEmployees();
}
