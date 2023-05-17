package Server.Model.Hotel.Users;

import java.io.Serializable;
import java.util.Random;

public class Employee extends Person implements Serializable
{
  private String position;

  public Employee(String firstName, String lastName, String position,
      String phoneNo, String password)
  {
    super(firstName, lastName, password, phoneNo, "new");
    this.position = position;
    super.setUsername(employeeUsername());
  }

  public Employee(String userId,String firstName, String lastName, String position,
      String phoneNo, String password)
  {
    super(firstName, lastName, password, phoneNo, userId);
    this.position = position;
  }

  public String employeeUsername()
  {
    String pos = getPosition().toLowerCase();
    char position = pos.charAt(0);
    Random random = new Random();
    int num = random.nextInt(899) + 100;
    return position+"."+ getLastName().toUpperCase() +"_"+ num;
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
    return super.info() + ", Position " + position;
  }
}

