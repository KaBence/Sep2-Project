package Client.ViewModel.Customer;

import Client.Model.Model;
import Client.Utility.Alerts;
import Server.Model.MyDate;
import Server.Utility.DataBase.DatabaseConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
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

  public void bindReviews(StringProperty property)
  {
    property.bindBidirectional(reviews);
  }

  public void previousScene(boolean b)
  {
    model.setPreviousView(b);
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
  public Alerts addReview() throws RemoteException
  {
    try
    {
      String state = model.addReview(model.getCurrentCustomer().getUsername(),
          model.getSelectedReservation().getRoomNumber(),
          model.getSelectedReservation().getFromDate(), MyDate.today(),
          reviews.getValue());
      if (state.equals(DatabaseConnection.SUCCESS))
      {
        return new Alerts(Alert.AlertType.INFORMATION,"Success","New review has been added successfully.\nThank you ;)");
      }
      else
      {
        return new Alerts(Alert.AlertType.ERROR,"Error","Please select a reservation for your review");
      }
    }
    catch (NumberFormatException e)
    {
      return new Alerts(Alert.AlertType.ERROR,"Error","Sorry. Some Error Occurred :(");
    }
  }
}


