package Server.Model;

import java.io.Serializable;

public class Customer implements Serializable
{
  private String username,firstName,lastName,phoneNo,paymentInfo;

  public Customer(String username, String firstName, String lastName,
      String phoneNo, String paymentInfo)
  {
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNo = phoneNo;
    this.paymentInfo = paymentInfo;
  }

  public String getUsername()
  {
    return username;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public String getPhoneNo()
  {
    return phoneNo;
  }

  public String getPaymentInfo()
  {
    return paymentInfo;
  }
}
