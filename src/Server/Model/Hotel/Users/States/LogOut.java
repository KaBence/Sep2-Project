package Server.Model.Hotel.Users.States;

import Server.Model.Hotel.Users.Person;
import Server.Model.Hotel.Users.States.LogIn;
import Server.Model.Hotel.Users.States.States;

import java.io.Serializable;

public class LogOut implements States, Serializable
{
  @Override public void logIn(Person user)
  {
    user.setState(new LogIn());
  }

  @Override public void logOut(Person user)
  {
    throw new RuntimeException(user.getUsername()+" is logged out");
  }

  @Override public String getState()
  {
    return "Logged out";
  }
}
