package Server.Model;

import Server.Model.Hotel.Reservation;
import Server.Model.Hotel.Review;
import Server.Model.Hotel.Room;
import Server.Model.Hotel.Users.Customer;
import Server.Model.Hotel.Users.Employee;
import Server.Model.Hotel.Users.Person;
import Server.Utility.DataBase.Customer.CustomerData;
import Server.Utility.DataBase.Customer.CustomerDataImplementation;
import Server.Utility.DataBase.DatabaseConnection;
import Server.Utility.DataBase.Employee.EmployeeData;
import Server.Utility.DataBase.Employee.EmployeeDataImplementation;
import Server.Utility.DataBase.Reservation.ReservationData;
import Server.Utility.DataBase.Reservation.ReservationDataImplementation;
import Server.Utility.DataBase.Review.ReviewData;
import Server.Utility.DataBase.Review.ReviewDataImplementation;
import Server.Utility.DataBase.Room.RoomData;
import Server.Utility.DataBase.Room.RoomDataImplementation;
import Server.Utility.Log.FileLog;
import Server.Utility.Log.Types;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ModelManager implements Model
{
  private RoomData roomData;
  private CustomerData customerData;
  private EmployeeData employeeData;
  private ReviewData reviewData;
  private ReservationData reservationData;
  private ArrayList<Person> loggedInUsers;
  private PropertyChangeSupport support;

  private static String homePath = System.getProperty("user.home");
  private static File downloads = new File(homePath, "Downloads/SepLogs");


  private FileLog logInLogOut=FileLog.getInstance(new File(downloads,"LogInOut.txt"));
  private FileLog customerLog=FileLog.getInstance(new File(downloads,"CustomerLog.txt"));
  private FileLog employeeLog=FileLog.getInstance(new File(downloads,"EmployeeLog.txt"));
  private FileLog reservationLog=FileLog.getInstance(new File(downloads,"ReservationLog.txt"));
  private FileLog reviewLog=FileLog.getInstance(new File(downloads,"ReviewLog.txt"));
  private FileLog roomLog=FileLog.getInstance(new File(downloads,"RoomLog.txt"));


  public ModelManager()
  {
    roomData = new RoomDataImplementation();
    customerData = new CustomerDataImplementation();
    reservationData = new ReservationDataImplementation();
    employeeData = new EmployeeDataImplementation();
    reviewData= new ReviewDataImplementation();
    loggedInUsers = new ArrayList<>();
    support = new PropertyChangeSupport(this);
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

  public void log(Types type,String message)
  {
    try
    {
      switch (type){
        case logInOut -> logInLogOut.log(message);
        case Review -> reviewLog.log(message);
        case Room -> roomLog.log(message);
        case Customer -> customerLog.log(message);
        case Employee -> employeeLog.log(message);
        case Reservation -> reservationLog.log(message);
      }
      support.firePropertyChange("log",null,message);
    }
    catch (IOException e){
      e.printStackTrace();
    }
  }

  @Override public Person logIn(Person user)
  {
    ArrayList<Person> list = getAllRegisteredUsers();
    for (int i = 0; i < list.size(); i++)
    {
      if (user.equals(list.get(i)))
      {
        list.get(i).logIn();
        loggedInUsers.add(list.get(i));
        log(Types.logInOut,list.get(i)+" is logged in");
        return list.get(i);
      }
    }
    return null;
  }

  @Override public Person logOut(Person user)
  {
    ArrayList<Person> list = getAllRegisteredUsers();
    for (int i = 0; i < list.size(); i++)
    {
      if (user.equals(list.get(i)))
      {
        list.get(i).logOut();
        loggedInUsers.remove(user);
        log(Types.logInOut,list.get(i)+" is logged out");
        return list.get(i);
      }
    }
    return null;
  }

  @Override public ArrayList<Person> getAllRegisteredUsers()
  {
    ArrayList<Person> x = new ArrayList<>();
    ArrayList<Employee> emp = getAllEmployees();
    ArrayList<Customer> cus = getAllCustomers();
    for (int i = 0; i < emp.size(); i++)
    {
      x.add(emp.get(i));
    }
    for (int i = 0; i < cus.size(); i++)
    {
      x.add(cus.get(i));
    }
    return x;
  }

  @Override public String addRoom(int roomNumber, int numberOfBeds, int size,
      int price, String orientation, boolean internet, boolean bathroom,
      boolean kitchen, boolean balcony)
  {
    String state=roomData.addNewRoom(roomNumber, numberOfBeds, size, price, orientation, internet, bathroom, kitchen, balcony, "Free");
    if (state.equals(DatabaseConnection.SUCCESS))
      log(Types.Room,roomNumber+" is added to the system");
    return state;
  }

  @Override public String addReservation(int roomNumber, String username,
      MyDate fromDate, MyDate toDate, boolean CheckedIn)
  {
    String state=reservationData.addNewReservation(roomNumber, username, fromDate,
        toDate, CheckedIn);
    if (state.equals(DatabaseConnection.SUCCESS))
      log(Types.Reservation,roomNumber+" ->"+ fromDate+" / "+toDate+" is added");
    return state;
  }

  @Override public Employee getNewEmployee()
  {
    return employeeData.getNewEmployee();
  }

  @Override public String addEmployee(String firstName, String lastName,
      String position, String phoneNo, String password)
  {
    String state=employeeData.AddEmployee(password, firstName, lastName, phoneNo,
        position);
    if (state.equals(DatabaseConnection.SUCCESS))
      log(Types.Employee,firstName+" "+lastName+" is added to the system");
    return state;
  }

  @Override public String addReview(String username, int roomNO,
      MyDate fromDate, MyDate postedDate, String comment)
  {
    String state=reviewData.addReview(username, roomNO, fromDate, postedDate, comment) ;
    if (state.equals(DatabaseConnection.SUCCESS))
      log(Types.Review,roomNO+" -> "+fromDate+" / "+postedDate+" added");
    return state;
  }

  @Override public String updateRoom(int roomNumber, int numberOfBeds, int size,
      int price, String orientation, boolean internet, boolean bathroom,
      boolean kitchen, boolean balcony)
  {
    String state=roomData.updateRoom(roomNumber, numberOfBeds, size, price,
        orientation, internet, bathroom, kitchen, balcony);
    if (state.equals(DatabaseConnection.SUCCESS))
      log(Types.Room,roomNumber+" has been updated");
    return state;
  }

  @Override public String updateCustomer(String username, String firstName,
      String lastName, String phoneNumber, String payment)
  {
    String state=customerData.editCustomer(username, firstName, lastName, phoneNumber,
        payment);
    if (state.equals(DatabaseConnection.SUCCESS))
      log(Types.Customer,firstName+" "+lastName+" has been updated");
    return state;
  }

  @Override public String updateEmployee(String username, String firstName,
      String lastName, String position, String phoneNo)
  {
    String state=employeeData.editEmployee(username, firstName, lastName, position,
        phoneNo);
    if (state.equals(DatabaseConnection.SUCCESS))
      log(Types.Employee,firstName+" "+lastName+" has been updated");
    return state;
  }

  @Override public String deleteRoom(int roomNumber)
  {
    String state=roomData.deleteRoom(roomNumber);
    if (state.equals(DatabaseConnection.SUCCESS))
      log(Types.Room,roomNumber+" has been deleted from the database");
    return state;
  }

  @Override public String deleteSelectedCustomer(String username)

  {
    String state=customerData.deleteCustomer(username);
    if (state.equals(DatabaseConnection.SUCCESS))
      log(Types.Customer,username+" has been deleted");
    return state;
  }

  @Override public String deleteEmployee(String userID)
  {
    String state=employeeData.deleteEmployee(userID);
    if (state.equals(DatabaseConnection.SUCCESS))
      log(Types.Employee,userID+" has been deleted");
    return state;
  }

  @Override public String deleteReservation(int roomNo, String username,
      MyDate fromDate)
  {
    String state=reservationData.deleteReservation(roomNo, username, fromDate);
    if (state.equals(DatabaseConnection.SUCCESS))
      log(Types.Reservation,roomNo+" ->"+ fromDate+" has been deleted");
    return state;
  }

  @Override public ArrayList<Room> getAllRooms()
  {
    return roomData.getAllRooms();
  }

  @Override public ArrayList<Review> getAllReviews()
  {
    return reviewData.getAllReviews();
  }

  @Override public ArrayList<Reservation> getAllMyReservation(String username)
  {
    return reservationData.getMyReservation(username);
  }

  @Override public ArrayList<Room> getSimpleFilteredRooms(String room)
  {
    return roomData.filterRoom(room);
  }


  @Override public ArrayList<Room> getFilteredRooms(MyDate from,MyDate to,String... attr)
  {
    if (from==null&&to==null)
      return roomData.filter(attr);
    ArrayList<Reservation> occupied=reservationData.getFilteredWithDateChecker(from,to);
    ArrayList<Room> filtered=roomData.filter(attr);
    ArrayList<Room> finale=new ArrayList<>();
    for (int i = 0; i < filtered.size(); i++)
    {
      if (occupied.isEmpty())
        return filtered;
      boolean temp=false;
      for (int j = 0; j < occupied.size(); j++)
      {
        if (filtered.get(i).getRoomNo()==occupied.get(j).getRoomNumber()){
          temp=true;
          break;
        }
      }
      if (!temp)
        finale.add(filtered.get(i));
    }
    return finale;
  }

  @Override public ArrayList<Customer> getFilteredCustomers(String... attr)
  {
    return customerData.filter(attr);
  }

  @Override public ArrayList<Customer> getCustomersFromDatabase()
  {
    return customerData.getAllCustomers();
  }

  @Override public ArrayList<Customer> getAllCustomers()
  {
    ArrayList<Customer> all = getCustomersFromDatabase();
    ArrayList<Customer> updated = new ArrayList<>(all.size());
    for (int i = 0; i < all.size(); i++)
    {
      boolean flag = true;
      for (int j = 0; j < loggedInUsers.size(); j++)
      {
        if (all.get(i).equals(loggedInUsers.get(j)))
        {
          flag = false;
          updated.add((Customer) loggedInUsers.get(j));
        }
      }
      if (flag)
      {
        updated.add(all.get(i));
      }
    }
    return updated;
  }

  @Override public ArrayList<Customer> filterCustomer(String employee)
  {
    return customerData.filterCustomers(employee);
  }

  @Override public ArrayList<Employee> getEmployeesFromDatabase()
  {
    return employeeData.getAllEmployees();
  }

  @Override public ArrayList<Employee> getAllEmployees()
  {
    ArrayList<Employee> all = getEmployeesFromDatabase();
    ArrayList<Employee> updated = new ArrayList<>(all.size());
    for (int i = 0; i < all.size(); i++)
    {
      boolean flag = true;
      for (int j = 0; j < loggedInUsers.size(); j++)
      {
        if (all.get(i).equals(loggedInUsers.get(j)))
        {
          flag = false;
          updated.add((Employee) loggedInUsers.get(j));
        }
      }
      if (flag)
      {
        updated.add(all.get(i));
      }
    }
    return updated;
  }

  @Override public ArrayList<Employee> filterEmployee(String employee)
  {
    return employeeData.filterEmployee(employee);
  }

  @Override public ArrayList<Employee> getFilteredEmployee(String... attr)
  {
    return employeeData.filter(attr);
  }

  @Override public ArrayList<Reservation> getAllReservations()
  {
    return reservationData.getAllUpcomingReservations();
  }

  @Override public ArrayList<Reservation> getFilteredReservations(String state,
      MyDate fromDate, MyDate toDate)
  {
    return reservationData.getFilteredReservations(state, fromDate, toDate);
  }

  @Override public String updateReservation(int roomNumber, String username,
      MyDate fromDate, MyDate toDate, int oldRoomNo, String oldUsername,
      MyDate oldFromDate,MyDate oldToDate)
  {
    String state=reservationData.updateReservation(roomNumber, username, fromDate,
        toDate, oldRoomNo, oldUsername, oldFromDate,oldToDate);
    if (state.equals(DatabaseConnection.SUCCESS))
      log(Types.Reservation,roomNumber+" -> "+fromDate+" "+toDate+" has been updated");
    return state;
  }

  @Override public String addCustomer(String username, String password,
      String firstName, String lastName, String phoneNo, String paymentInfo)

  {
    String state=customerData.addCustomer(username,password,firstName,lastName,phoneNo,paymentInfo);
    if (state.equals(DatabaseConnection.SUCCESS))
      log(Types.Customer,firstName+" "+lastName+" has been added");
    return state;
  }

  @Override public String checkIn(int roomNumber, String username,
      MyDate fromDate)
  {
    String state=reservationData.checkIn(roomNumber, username, fromDate);
    if (state.equals(DatabaseConnection.SUCCESS))
      log(Types.Reservation,roomNumber+" -> "+username+" checked in");
    return state;
  }

  @Override public String checkOut(int roomNumber, String username,
      MyDate fromDate)
  {
    String state=reservationData.checkOut(roomNumber, username, fromDate);
    if (state.equals(DatabaseConnection.SUCCESS))
      log(Types.Reservation,roomNumber+" -> "+username+" checked out");
    return state;
  }
}
