package Server.Model;

import Server.Model.Hotel.Users.Customer;
import Server.Model.Hotel.Users.Employee;
import Server.Model.Hotel.Reservation;
import Server.Model.Hotel.Room;
import Server.Model.Hotel.Users.Person;
import Server.Utility.DataBase.Customer.CustomerData;
import Server.Utility.DataBase.Customer.CustomerDataImplementation;
import Server.Utility.DataBase.Employee.EmployeeData;
import Server.Utility.DataBase.Employee.EmployeeDataImplementation;
import Server.Utility.DataBase.Reservation.ReservationData;
import Server.Utility.DataBase.Reservation.ReservationDataImplementation;
import Server.Utility.DataBase.Room.RoomData;
import Server.Utility.DataBase.Room.RoomDataImplementation;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class ModelManager implements Model
{
  private RoomData roomData;
  private CustomerData customerData;

  private EmployeeData employeeData;

  private ReservationData reservationData;
  private PropertyChangeSupport support;

  public ModelManager(){
    roomData=new RoomDataImplementation();
    customerData=new CustomerDataImplementation();
    reservationData=new ReservationDataImplementation();
    employeeData=new EmployeeDataImplementation();
    support=new PropertyChangeSupport(this);
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }


  @Override public Person logIn(Person user)
  {
    ArrayList<Person> list = getAllRegisteredUsers();
    for (int i = 0; i < list.size(); i++)
    {
      if (user.equals(list.get(i)))
      {
        list.get(i).logIn();
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


  @Override public String addRoom(int roomNumber, int numberOfBeds, int size,int price,
      String orientation, boolean internet, boolean bathroom, boolean kitchen,
      boolean balcony)
  {
   return roomData.addNewRoom(roomNumber, numberOfBeds, size, price, orientation, internet, bathroom, kitchen, balcony,"Free");
  }

  @Override public String addReservation(int roomNumber, String username,
      MyDate fromDate, MyDate toDate, boolean CheckedIn)
  {
    return reservationData.addNewReservation(roomNumber,username,fromDate,toDate,CheckedIn);
  }

  @Override public Employee addEmployee(String firstName, String lastName,
      String position, String phoneNo, String password)
  {
    return employeeData.AddEmployee(password,firstName,lastName,phoneNo,position);
  }

  @Override public String updateRoom(int roomNumber, int numberOfBeds, int size,
      int price, String orientation, boolean internet, boolean bathroom,
      boolean kitchen, boolean balcony)
  {
    return roomData.updateRoom(roomNumber,numberOfBeds,size,price,orientation,internet,bathroom,kitchen,balcony);
  }

  @Override public String updateCustomer(String username, String firstName,
      String lastName, String phoneNumber, String payment)
  {
    return customerData.editCustomer(username,firstName, lastName, phoneNumber,payment);
  }

  @Override public String updateEmployee(String username, String firstName,
      String lastName, String position, String phoneNo)
      throws RemoteException
  {
    return employeeData.editEmployee(username, firstName, lastName,position,phoneNo);
  }

  @Override public String deleteRoom(int roomNumber)
  {
    return roomData.deleteRoom(roomNumber);
  }

  @Override public String deleteSelectedCustomer(String username)
  {
    return customerData.deleteCustomer(username);
  }

  @Override public String deleteEmployee(String userID)
  {
    return employeeData.deleteEmployee(userID);
  }

  @Override public String deleteReservation(int roomNo, String username,
      MyDate fromDate)
  {
    return reservationData.deleteReservation(roomNo, username,fromDate);
  }

  @Override public ArrayList<Room> getAllRooms()
  {
    return roomData.getAllRooms();
  }



  @Override public ArrayList<Room> getSimpleFilteredRooms(String room)
  {
    return roomData.filterRoom(room);
  }


  @Override public ArrayList<Room> getFilteredRooms(String... attr)
  {
    return roomData.filter(attr);
  }

  @Override public ArrayList<Customer> getFilteredCustomers(String... attr)
  {
    return customerData.filter(attr);
  }

  @Override public ArrayList<Customer> getAllCustomers()
  {
    return customerData.getAllCustomers();
  }

  @Override public ArrayList<Customer> filterCustomer(String employee)
  {
    return customerData.filterCustomers(employee);
  }

  @Override public ArrayList<Employee> getAllEmployees()
  {
    return employeeData.getAllEmployees();
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
    return reservationData.getAllReservations();
  }

  @Override public ArrayList<Reservation> getFilteredReservations(String state,
      MyDate fromDate, MyDate toDate)
  {
    return reservationData.getFilteredReservations(state, fromDate, toDate);
  }

  @Override public String updateReservation(int roomNumber, String username,
      MyDate fromDate, MyDate toDate,int oldRoomNo,String oldUsername,MyDate oldFromDate)
  {
    return reservationData.updateReservation(roomNumber, username, fromDate, toDate,oldRoomNo,oldUsername,oldFromDate);
  }


  @Override public String checkIn(int roomNumber, String username,
      MyDate fromDate)
  {
    System.out.println("manager");
    return reservationData.checkIn(roomNumber, username, fromDate);
  }

  @Override public String checkOut(int roomNumber, String username,
      MyDate fromDate)
  {
    return reservationData.checkOut(roomNumber, username, fromDate);
  }
}
