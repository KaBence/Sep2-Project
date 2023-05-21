package Server.Model.Hotel.Users;

public interface States
{
  void logIn(Person user);
  void logOut(Person user);
  String getState();
}
