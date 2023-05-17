package Server.Model;

import java.io.Serializable;
import java.time.LocalDate;

public class MyDate implements Serializable
{
  private int day;
  private int month;
  private int year;


  /**
   * This is a constructor that takes 3 parameters and sets the day, month and year of the MyDate object.
   * @param day int
   * @param month int
   * @param year int
   */
  public MyDate(int day, int month, int year) {
    this.day = day;
    this.month = month;
    this.year = year;
  }


  /**
   * This function returns the day of the month.
   * @return The day of the month.
   */
  public int getDay() {
    return day;
  }

  /**
   * It takes a date and time in the form of a string, and returns a MyDate object
   * @param date The date in the format dd/mm/yyyy
   * @return A new MyDate object with the given parameters.
   */
  public static MyDate stringToDate(String date) {
    String[] temp = date.split("-");
    int tempYear = Integer.valueOf(temp[0]);
    int tempMonth = Integer.valueOf(temp[1]);
    int tempDay = Integer.valueOf(temp[2]);
    return new MyDate(tempDay, tempMonth, tempYear);
  }

  public static MyDate LocalDateToMyDate(LocalDate date){
    return new MyDate(date.getDayOfMonth(),date.getMonthValue(), date.getYear());
  }


  /**
   * This function sets the day of the month to the value of the parameter day.
   * @param day The day of the month
   */
  public void setDay(int day) {
    this.day = day;
  }

  /**
   * This function returns the month of the year.
   * @return The month of the date.
   */
  public int getMonth() {
    return month;
  }

  /**
   * This function sets the month of the year.
   * @param month The month of the year
   */
  public void setMonth(int month) {
    this.month = month;
  }

  /**
   * This function returns the year of the date.
   * @return The year of the date.
   */
  public int getYear() {
    return year;
  }

  /**
   * This function sets the year of the date.
   * @param year The year of the date.
   */
  public void setYear(int year) {
    this.year = year;
  }



  /**
   * This method checks whether provided parameter is equals to date and time or not.
   * @param obj The object to compare
   * @return true if obj is equals to the current date and time otherwise false.
   */
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    MyDate other =(MyDate) obj;
    return day== other.getDay() && month== other.getMonth() && year== other.getYear();

  }

  /**
   * If the hour and minute are -1, then return the date, otherwise return the date and time
   * @return The date and time in the string format.
   */
  public String toString() {
    return day + "-" + month + "-" + year ;
  }

  /**
   * Create a new MyDate object with the current date.
   * @return A new MyDate object with the current date.
   */
  public static MyDate today() {
    LocalDate current = LocalDate.now();
    return new MyDate(current.getDayOfMonth(), current.getMonthValue(), current.getYear());
  }

  /**
   * This function converts the date object to a LocalDate object
   * @return The method is returning a LocalDate object.
   */
  public  LocalDate convertToLocalDate(){
    return LocalDate.of(year,month,day);
  }


  /*public boolean isBefore(MyDate date2){
    if ((year<date2.year) || (day<date2.day && (month<date2.month || year<date2.year)) || (day>date2.day && (month<date2.month || year<date2.year)) || (month<date2.month && year==date2.year || month>date2.month && year<date2.year) || (day < date2.day)){
      return true;
    }
    else return false;*/

  /**
   * If the year is less than the year of the other date, return true. If the year is the same, but the month is less,
   * return true. If the year and month are the same, but the day is less, return true. Otherwise, return false
   * @param date2 The date to compare to.
   * @return A boolean value.
   */
  public boolean isBefore(MyDate date2) {
    if (year < date2.year) {
      return true;
    } else if (year == date2.year && month < date2.month) {
      return true;
    } else if (year == date2.year && month == date2.month && day < date2.day) {
      return true;
    } else {
      return false;
    }
  }
}

