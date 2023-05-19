package Server.Model.Hotel.Users;

import java.io.Serializable;

public abstract class Person implements Serializable
{
  private String firstName,lastName,password,phoneNo,username;

  public Person(String firstName, String lastName, String password,
      String phoneNo,String username)
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.password = password;
    this.phoneNo = phoneNo;
    this.username= username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public String getPassword()
  {
    return password;
  }

  public String getPhoneNo()
  {
    return phoneNo;
  }

  public String getUsername()
  {
    return username;
  }

  public String toString(){
    return username+" -> "+firstName+" "+lastName+" | "+phoneNo+" | ";
  }

  public String info(){
    String temp=username+" -> , FirstName "+ firstName+", LastName "+lastName+", PhoneNumber "+phoneNo;
    return temp.toLowerCase();
  }
}
