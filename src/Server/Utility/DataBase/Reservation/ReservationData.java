package Server.Utility.DataBase.Reservation;

import Server.Model.MyDate;
import Server.Model.Hotel.Reservation;

import java.util.ArrayList;

public interface ReservationData
{
  Reservation addNewReservation(int roomNumber, String username, MyDate fromDate, MyDate toDate, boolean CheckedIn);
  ArrayList<Reservation> getMyReservation(String username);

  String updateReservation(int roomNumber, String username, MyDate fromDate, MyDate toDate,int oldRoomNo,String oldUsername,MyDate oldFromDate);
  String deleteReservation(int roomNumber,String username,
      MyDate fromDate);
  ArrayList<Reservation> getAllReservations();
  ArrayList<Reservation>  filterReservation(String reservation);
  ArrayList<Reservation> getFilteredReservations(String state,MyDate fromDate,MyDate toDate);

  String checkIn(int roomNumber, String username, MyDate fromDate);
  String checkOut(int roomNumber, String username, MyDate fromDate);

  //ArrayList<Reservation> SimpleFilteredReservation(String reservation);
}
