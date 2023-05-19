package Server.Model.Hotel.Users;

import java.io.Serializable;

public class LogIn implements States, Serializable
{

  @Override public void logIn(String username)
  {
    throw new RuntimeException(username+" is already logged in");
  }

  @Override public void logOut(String username)
  {
    new LogOut();
  }

  @Override public String getState()
  {
    return "Logged in";
  }
}
