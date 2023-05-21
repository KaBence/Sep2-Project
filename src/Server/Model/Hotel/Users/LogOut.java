package Server.Model.Hotel.Users;

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
