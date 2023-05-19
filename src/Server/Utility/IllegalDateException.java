package Server.Utility;

import java.io.IOException;

public class IllegalDateException extends RuntimeException
{
  private int check;
  public IllegalDateException(int check){
    super("problem");
    this.check=check;
  }

  public String message(){
    return switch (check){
      case 1-> "the dates are outside a reservation";
      case 2-> "The dates are the same";
      case 3-> "Either dates are the same";
      case 4-> "From date in between and to date is after";
      case 5-> "Both between";
      case 6-> "From is before and return in between";
      case 7-> "From is before and to is equals";
      default -> throw new IllegalStateException("Unexpected value: " + check);
    };
  }

  public int getCheck()
  {
    return check;
  }
}
