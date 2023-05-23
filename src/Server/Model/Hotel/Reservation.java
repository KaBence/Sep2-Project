package Server.Model.Hotel;

import Server.Model.MyDate;

import java.io.Serializable;

public class Reservation implements Serializable
{
  private int roomNumber;
  private String username;
  private MyDate fromDate;
  private MyDate toDate;
  private Boolean CheckedIn;

  public Reservation(int roomNumber, String username, MyDate fromDate,
      MyDate toDate, Boolean CheckedIn)
  {
    this.roomNumber = roomNumber;
    this.username = username;
    this.fromDate = fromDate;
    this.toDate = toDate;
    this.CheckedIn=CheckedIn;
  }

  public int getRoomNumber()
  {
    return roomNumber;
  }

  public void setRoomNumber(int roomNumber)
  {
    this.roomNumber = roomNumber;
  }

  public String getUsername()
  {
    return username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  public MyDate getFromDate()
  {
    return fromDate;
  }

  public void setFromDate(MyDate fromDate)
  {
    this.fromDate = fromDate;
  }

  public MyDate getToDate()
  {
    return toDate;
  }

  public void setToDate(MyDate toDate)
  {
    this.toDate = toDate;
  }

  public Boolean isCheckedIn()
  {
    return CheckedIn;
  }

  public void setCheckedIn(Boolean checkedIn)
  {
    CheckedIn = checkedIn;
  }

  public String getState()
  {
    if (CheckedIn==null)
    {
      return "In The Past";
    }
    else if (CheckedIn)
    {
      return "Booked";
    }
    else
    {
      return "Reserved";
    }
  }

  public String reservationInfo(){
    return "RoomNo: " + roomNumber +", Username: " + username +", FromDate: " +fromDate+", ToDate: "+ toDate;
  }
  public String toString(){
    return roomNumber +", " + username +", " +fromDate+" -- "+ toDate;
  }
}
