package Server.Model.Hotel.Users;

public interface States
{
  void logIn(String username);
  void logOut(String username);
  String getState();
}
