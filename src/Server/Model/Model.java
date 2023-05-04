package Server.Model;

import java.beans.PropertyChangeListener;

public interface Model
{
  void addListener(PropertyChangeListener listener);

  void addRoom(int roomNumber, int numberOfBeds, int size,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony);
}
