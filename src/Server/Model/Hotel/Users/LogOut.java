package Server.Model.Hotel.Users;

import java.io.Serializable;

public class LogOut implements States, Serializable
{
  @Override public void logIn(String username)
  {
    new LogIn();
  }

  @Override public void logOut(String username)
  {
    throw new RuntimeException(username+" is logged out");
  }

  @Override public String getState()
  {
    return "Logged out";
  }
}
