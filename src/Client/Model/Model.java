package Client.Model;

import Server.Model.*;
import Server.Model.Hotel.Review;
import Server.Model.Hotel.Users.Customer;
import Server.Model.Hotel.Users.Employee;
import Server.Model.Hotel.Reservation;
import Server.Model.Hotel.Room;
import Server.Model.Hotel.Users.Person;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Model extends ModelCustomerSide,ModelEmployeeSide
{
  boolean getCurrent();


}
