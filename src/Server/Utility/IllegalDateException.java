package Server.Utility;

public class IllegalDateException extends RuntimeException
{
  private int check;
  public IllegalDateException(int check){
    super("problem");
    this.check=check;
  }

  public String message(){
    return switch (check){
      case 1-> "In between the dates you want to reserve there is already pending reservation.\nPlease change the dates.";
      case 2-> "The dates you have chosen, for the beginning and the and of your stay, are identical.\nPlease change the dates.";
      case 3-> "One of the dates that you have chosen is the same as one of the reservation already created.\nPlease change the dates.";
      case 4-> "The chosen date for the beginning of your stay is during somebody else's reservation and the chosen day for the end of it is okay.\nPlease change the first date.";
      case 5-> "Your whole reservation is during somebody else's stay.\nPlease change the room or the dates.";
      case 6-> "The chosen date for the beginning of your stay is during somebody else's reservation.\nPlease change that date.";
      case 7-> "The chosen date for the end of your stay needs to be at least a day before.";
      case 8 ->"Please, fill in both dates.";
      case 9 ->"It is not possible to create reservations in the past.";
      case 10 -> "The date for the end of your stay is before the date for the beginning of your stay.";
      default -> throw new IllegalStateException("Unexpected value: " + check);
    };
  }

  public int getCheck()
  {
    return check;
  }
}
