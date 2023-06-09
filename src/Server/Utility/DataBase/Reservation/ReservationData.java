package Server.Utility.DataBase.Reservation;

import Server.Model.MyDate;
import Server.Model.Hotel.Reservation;

import java.util.ArrayList;

public interface ReservationData
{
  String  addNewReservation(int roomNumber, String username, MyDate fromDate, MyDate toDate, boolean CheckedIn);
  ArrayList<Reservation> getMyReservation(String username);

  String updateReservation(int roomNumber, String username, MyDate fromDate, MyDate toDate,int oldRoomNo,String oldUsername,MyDate oldFromDate,MyDate oldToDate);
  String deleteReservation(int roomNumber,String username,
      MyDate fromDate);
  ArrayList<Reservation> getAllReservations();
  ArrayList<Reservation> getAllUpcomingReservations();
  ArrayList<Reservation> getFilteredReservations(String state,MyDate fromDate,MyDate toDate);

  ArrayList<Reservation> getFilteredWithDateChecker(MyDate from,MyDate to);

  String checkIn(int roomNumber, String username, MyDate fromDate);
  String checkOut(int roomNumber, String username, MyDate fromDate);

  //ArrayList<Reservation> SimpleFilteredReservation(String reservation);
}
