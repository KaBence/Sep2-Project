package Server.View;

import Server.Model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ConsoleView implements PropertyChangeListener
{
  public ConsoleView(Model model)
  {
    model.addListener(this);
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt)
  {
    System.out.println("-->" + evt.getNewValue());
  }
}
