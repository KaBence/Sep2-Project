package Server.Model.Hotel.Users.States;

import Server.Model.Hotel.Users.Person;

public interface States
{
  void logIn(Person user);
  void logOut(Person user);
  String getState();
}
