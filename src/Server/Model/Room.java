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

  public int getRoomNo()
  {
    return roomNo;
  }

  public void setRoomNo(int roomNo)
  {
    this.roomNo = roomNo;
  }

  public int getNoOfBeds()
  {
    return noOfBeds;
  }

  public void setNoOfBeds(int noOfBeds)
  {
    this.noOfBeds = noOfBeds;
  }

  public int getSize()
  {
    return size;
  }

  public void setSize(int size)
  {
    this.size = size;
  }

  public String getOrientation()
  {
    return orientation;
  }

  public void setOrientation(String orientation)
  {
    this.orientation = orientation;
  }

  public boolean isInternet()
  {
    return internet;
  }

  public void setInternet(boolean internet)
  {
    this.internet = internet;
  }

  public boolean isTv()
  {
    return tv;
  }

  public void setTv(boolean tv)
  {
    this.tv = tv;
  }

  public boolean isBathroom()
  {
    return bathroom;
  }

  public void setBathroom(boolean bathroom)
  {
    this.bathroom = bathroom;
  }

  public boolean isKitchenet()
  {
    return kitchenet;
  }

  public void setKitchenet(boolean kitchenet)
  {
    this.kitchenet = kitchenet;
  }

  public boolean isPeopleWithDisabilities()
  {
    return peopleWithDisabilities;
  }

  public void setPeopleWithDisabilities(boolean peopleWithDisabilities)
  {
    this.peopleWithDisabilities = peopleWithDisabilities;
  }

  public boolean isAirConditioning()
  {
    return airConditioning;
  }

  public void setAirConditioning(boolean airConditioning)
  {
    this.airConditioning = airConditioning;
  }

  public boolean isBalcony()
  {
    return balcony;
  }

  public void setBalcony(boolean balcony)
  {
    this.balcony = balcony;
  }
}
