package Server.Model;

import java.io.Serializable;

public class Review implements Serializable
{
    private String username;

    private int reviewID;

    private int roomNO;

    private MyDate fromDate;

    private MyDate postedDate;

    private String comment;

    public Review(String username, int reviewID, int roomNO, MyDate fromDate, MyDate postedDate, String comment)
    {
        this.username = username;
        this.reviewID = reviewID;
        this.roomNO = roomNO;
        this.fromDate = fromDate;
        this.postedDate = postedDate;
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public int getRoomNO() {
        return roomNO;
    }

    public void setRoomNO(int roomNO) {
        this.roomNO = roomNO;
    }

    public MyDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(MyDate fromDate) {
        this.fromDate = fromDate;
    }

    public MyDate getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(MyDate postedDate) {
        this.postedDate = postedDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
