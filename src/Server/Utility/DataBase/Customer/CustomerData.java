package Server.Utility.DataBase.Customer;

import Server.Model.Customer;

import java.util.ArrayList;

public interface CustomerData
{
  Customer addCustomer(String username, String password, String firstName,
      String lastName, String phoneNO, String paymentInfo);
  String editCustomer();
  String deleteCustomer();
  ArrayList<Customer> getAllCustomers();
}
