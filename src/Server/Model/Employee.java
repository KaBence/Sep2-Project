package Server.Model;

import java.io.Serializable;

public class Employee implements Serializable
{
  private String userID, firstName, lastName, position, phoneNo;
  public Employee(String userID, String firstName, String lastName, String position, String phoneNo)
  {
    this.userID = userID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.position = position;
    this.phoneNo = phoneNo;
  }

  public String getUserID()
  {
    return userID;
  }

  public String getUsername()
  {
    return userID;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public String getPosition()
  {
    return position;
  }

  public String getPhoneNo()
  {
    return phoneNo;
  }

  public String toString()
  {
    return userID + " " + firstName  + " " + lastName + " " + position + " " + phoneNo;
  }
}
