package Server.Utility.DataBase.Customer;

import Server.Model.Hotel.Users.Customer;

import java.util.ArrayList;

public interface CustomerData
{
  String addCustomer(String username, String password, String firstName,
      String lastName, String phoneNO, String paymentInfo);
  String deleteCustomer(String username);
  String editCustomer(String username, String firstName,
      String lastName, String phoneNO, String paymentInfo);
  ArrayList<Customer> getAllCustomers();
  ArrayList<Customer> filterCustomers(String customer);
ArrayList<Customer> filter(String...attr);

}
