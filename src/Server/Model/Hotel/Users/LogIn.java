package Server.Model.Hotel.Users;

import java.io.Serializable;

public class LogIn implements States, Serializable
{

  @Override public void logIn(Person user)
  {
    throw new RuntimeException(user.getUsername()+" is already logged in");
  }

  @Override public void logOut(Person user)
  {
    user.setState(new LogOut());
  }

  @Override public String getState()
  {
    return "Logged in";
  }
}
