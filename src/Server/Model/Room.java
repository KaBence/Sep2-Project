package Server.Model;

import java.io.Serializable;

public class Room implements Serializable
{
  private int roomNo,noOfBeds,size,price;
  private String orientation;
  private boolean internet,bathroom,kitchenet,balcony;

  public Room(int roomNo, int noOfBeds, int size, int price, String orientation,
      boolean internet, boolean bathroom, boolean kitchenet, boolean balcony)
  {
    this.price=price;
    this.roomNo = roomNo;
    this.noOfBeds = noOfBeds;
    this.size = size;
    this.orientation = orientation;
    this.internet = internet;
    this.bathroom = bathroom;
    this.kitchenet = kitchenet;
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

  public boolean isBalcony()
  {
    return balcony;
  }

  public void setBalcony(boolean balcony)
  {
    this.balcony = balcony;
  }

  public int getPrice()
  {
    return price;
  }

  public void setPrice(int price)
  {
    this.price = price;
  }

  public String hasInternet()
  {
    if (internet)
    {
      return "internet, ";
    }
    return "";
  }
 public String hasBathroom()
 {
   if (bathroom)
   {
     return "bathroom, ";
   }
   return "";
 }

  public String hasKichenet()
  {
   if (kitchenet)
   {
     return "kichenet, ";
   }
   return "";
  }
  public String hasBalcony()
  {
    if (balcony)
    {
      return "balcony";
    }
    return "";
  }

  public String roomInfo()
  {
    return toString()+" "+hasInternet()+hasBathroom()+hasKichenet()+hasBalcony();
  }

  public String toString()
  {
   return "RoomNo: "+roomNo+", NoBeds: "+noOfBeds+", Size: "+size+"m², Orientation: "+orientation;
  }
}
