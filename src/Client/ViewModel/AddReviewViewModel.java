package Client.ViewModel;

import Client.Model.Model;
import javafx.beans.property.SimpleStringProperty;

import java.rmi.RemoteException;

public class AddReviewViewModel
{
  private Model model;
  private SimpleStringProperty reviews;

  public AddReviewViewModel(Model model)
  {
    this.model = model;
    reviews = new SimpleStringProperty();
  }

  public void bindReviews(SimpleStringProperty property)
  {
    property.bindBidirectional(reviews);

  }

  public boolean addReview() throws RemoteException
  {
    try
    {
     // model.addReview();
      return true;
    }
    catch (NumberFormatException e){

    }
    return false;
    }
  }
