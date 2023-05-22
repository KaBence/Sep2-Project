package Server.Utility.DataBase.Review;

import Server.Model.Hotel.Review;
import Server.Model.MyDate;

import java.util.ArrayList;

public interface ReviewData
{
  String addReview(String username, int roomNO, MyDate fromDate,
      MyDate postedDate, String comment);
  ArrayList<Review> getAllReviews();
}
