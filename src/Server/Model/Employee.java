package Server.Model;

import java.io.Serializable;

public class Employee extends Person implements Serializable
{
  private String position;

  public Employee(String userID, String firstName, String lastName, String position, String phoneNo,
      String password)
  {
    super(firstName, lastName, password, phoneNo, userID);
    this.position = position;
  }

  public String getUsername()
  {
    return super.getUsername();
  }

  public String getFirstName()
  {
    return super.getFirstName();
  }

  public String getLastName()
  {
    return super.getLastName();
  }

  @Override public String getPassword()
  {
    return super.getPassword();
  }

  public String getPosition()
  {
    return position;
  }

  public String getPhoneNo()
  {
    return super.getPhoneNo();
  }

  public String toString()
  {
    return super.toString() + position;
  }

  public String employeeInfo()
  {
    return super.info()+", Position "+position;
  }
}

