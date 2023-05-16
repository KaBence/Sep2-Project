package Server.Utility.DataBase.Reservation;

import Server.Model.MyDate;
import Server.Model.Reservation;

import java.util.ArrayList;

public interface ReservationData
{
  Reservation addNewReservation(int roomNumber, String username, MyDate fromDate, MyDate toDate, boolean CheckedIn);
  ArrayList<Reservation> getMyReservation(String username);

  String updateReservation(int roomNumber, String username, MyDate fromDate, MyDate toDate, boolean CheckedIn);
  ArrayList<Reservation> getAllRooms();

}
