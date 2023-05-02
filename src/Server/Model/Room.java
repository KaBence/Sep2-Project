package Server.Model;

public class Room
{
  private int roomNo,noOfBeds,size;
  private String orientation;
  private boolean internet,tv,bathroom,kitchenet,peopleWithDisabilities,airConditioning,balcony;

  public Room(int roomNo, int noOfBeds, int size, String orientation,
      boolean internet, boolean tv, boolean bathroom, boolean kitchenet,
      boolean peopleWithDisabilities, boolean airConditioning, boolean balcony)
  {
    this.roomNo = roomNo;
    this.noOfBeds = noOfBeds;
    this.size = size;
    this.orientation = orientation;
    this.internet = internet;
    this.tv = tv;
    this.bathroom = bathroom;
    this.kitchenet = kitchenet;
    this.peopleWithDisabilities = peopleWithDisabilities;
    this.airConditioning = airConditioning;
    this.balcony = balcony;
  }
}
