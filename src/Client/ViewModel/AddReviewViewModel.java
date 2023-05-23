package Client.ViewModel;

import Client.Model.Model;
import Server.Model.Hotel.Review;
import Server.Model.MyDate;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class AddReviewViewModel
{
  private Model model;
  private SimpleStringProperty reviews;

  public AddReviewViewModel(Model model)
  {
    this.model = model;
    reviews = new SimpleStringProperty();
  }

  public void bindReviews(StringProperty property)
  {
    property.bindBidirectional(reviews);

  }

  /*public void update(){
    ArrayList<Review> reviewsList;
    try{
      reviewsList =model.getAllReviews();
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
    ObservableList<Review> reviewObservableList= FXCollections.observableList(reviewsList);

  }*/
  public boolean addReview() throws RemoteException
  {
    try
    {
      model.addReview(model.getCurrentCustomer().getUsername(),model.getSelectedReservation().getRoomNumber(), model.getSelectedReservation().getFromDate(), MyDate.today(),reviews.getValue() );
      //if()
      return true;
    }
    catch (NumberFormatException e){

    }
    return false;
    }
  }
