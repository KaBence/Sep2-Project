package Client.ViewModel;

import Client.Model.Model;
import dk.via.remote.observer.RemotePropertyChangeListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CustomerHomeViewModel  implements PropertyChangeListener
{
  private Model model;

  public CustomerHomeViewModel(Model model)
  {
    this.model = model;
  }




  @Override public void propertyChange(PropertyChangeEvent evt)
  {

  }
}
