package Server.Model;

import java.beans.PropertyChangeListener;

public interface Model
{
  void addListener(PropertyChangeListener listener);

  void addRoom(int roomNumber, int numberOfBeds, int size, int price,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony);
}
