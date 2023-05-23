package Client.ViewModel;

import Client.Model.Model;
import Server.Model.Hotel.Review;
import Server.Model.MyDate;
import Server.Utility.DataBase.DatabaseConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

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

        String state = model.addReview(model.getCurrentCustomer().getUsername(),
            model.getSelectedReservation().getRoomNumber(),
            model.getSelectedReservation().getFromDate(), MyDate.today(),
            reviews.getValue());

        if (state.equals(DatabaseConnection.SUCCESS))
        {
          Alert alert = new Alert(Alert.AlertType.INFORMATION,
              "Successfully added a new review", ButtonType.OK);
          alert.setTitle("Success");
          alert.setHeaderText(null);
          alert.showAndWait();

          return true;
        }
        if (state.equals(DatabaseConnection.ERROR))
        {
          Alert alert = new Alert(Alert.AlertType.ERROR,
              "Please select a reservation for your review", ButtonType.OK);
          alert.setTitle("Error");
          alert.setHeaderText(null);
          alert.showAndWait();
        }




    }
    catch (NumberFormatException e)
    {

    } return false;

  }

  public boolean logOut()
  {
    try
    {
      model.logOut();
      model.setGuest();
      return true;
    }
    catch (RemoteException e)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText("Logging out error");
      alert.setContentText(
          "Contact the developers of the system\nPhone number: +45 8755 4243\nPhone number: +45 8755 4222");
      alert.showAndWait();
      return false;
    }
  }
}


