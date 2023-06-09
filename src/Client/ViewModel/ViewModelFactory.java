package Client.ViewModel;

import Client.Model.Model;
import Client.ViewModel.Customer.AddCustomerViewModel;
import Client.ViewModel.Customer.AddReviewViewModel;
import Client.ViewModel.Customer.CustomerEditReservationViewModel;
import Client.ViewModel.Customer.CustomerHomeViewModel;
import Client.ViewModel.Employee.*;
import Client.ViewModel.Shared.HomeViewModel;

public class ViewModelFactory
{
  private AdminViewModel adminViewModel;
  private HomeViewModel homeViewModel;

  private EmployeeLoginViewModel employeeLoginViewModel;

  private EmployeeHomeViewModel employeeHomeViewModel;

  private CustomerHomeViewModel customerHomeViewModel;
  private AddRoomViewModel addRoomViewModel;
  private EditRoomViewModel editRoomViewModel;
  private EditCustomerViewModel editCustomerViewModel;
  private EditEmployeeViewModel editEmployeeViewModel;
  private EditReservationViewModel editReservationViewModel;
  private AddReviewViewModel addReviewViewModel;
  private AddCustomerViewModel addCustomerViewModel;
  private CustomerEditReservationViewModel customerEditReservationViewModel;

  public ViewModelFactory(Model model)
  {
    homeViewModel = new HomeViewModel(model);
    employeeHomeViewModel = new EmployeeHomeViewModel(model);
    employeeLoginViewModel = new EmployeeLoginViewModel(model);
    customerHomeViewModel = new CustomerHomeViewModel(model);
    addRoomViewModel = new AddRoomViewModel(model);
    editRoomViewModel = new EditRoomViewModel(model);
    editCustomerViewModel = new EditCustomerViewModel(model);
    editEmployeeViewModel = new EditEmployeeViewModel(model);
    editReservationViewModel=new EditReservationViewModel(model);
    adminViewModel=new AdminViewModel(model);
    addReviewViewModel= new AddReviewViewModel(model);
    addCustomerViewModel = new AddCustomerViewModel(model);
    customerEditReservationViewModel = new CustomerEditReservationViewModel(model);
  }

  public AdminViewModel getAdminViewModel()
  {
    return adminViewModel;
  }

  public EditReservationViewModel getEditReservationViewModel()
  {
    return editReservationViewModel;
  }

  public HomeViewModel getHomeViewModel()
  {
    return homeViewModel;
  }

  public EmployeeLoginViewModel getEmployeeLoginViewModel()
  {
    return employeeLoginViewModel;
  }

  public AddReviewViewModel getAddReviewViewModel(){
    return addReviewViewModel;
  }

  public EmployeeHomeViewModel getEmployeeHomeViewModel()
  {
    return employeeHomeViewModel;
  }

  public CustomerHomeViewModel getCustomerHomeViewModel()
  {
    return customerHomeViewModel;
  }

  public EditRoomViewModel getEditRoomViewModel()
  {
    return editRoomViewModel;
  }

  public AddRoomViewModel getAddRoomViewModel()
  {
    return addRoomViewModel;
  }

  public EditCustomerViewModel getEditCustomerViewModel()
  {
    return editCustomerViewModel;
  }

  public EditEmployeeViewModel getEditEmployeeViewModel()
  {
    return editEmployeeViewModel;
  }

  public AddCustomerViewModel getAddCustomerViewModel()
  {
    return addCustomerViewModel;
  }

  public CustomerEditReservationViewModel getCustomerEditReservationViewModel()
  {
    return customerEditReservationViewModel;
  }
}
