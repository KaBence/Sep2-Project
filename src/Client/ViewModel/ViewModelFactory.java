package Client.ViewModel;

import Client.Model.Model;

public class ViewModelFactory
{
  private HomeViewModel homeViewModel;

  private EmployeeLoginViewModel employeeLoginViewModel;

  private EmployeeHomeViewModel employeeHomeViewModel;

  private CustomerHomeViewModel customerHomeViewModel;
  private AddRoomViewModel addRoomViewModel;
  private EmployeeSignInViewModel employeeSignInViewModel;
  private EditRoomViewModel editRoomViewModel;
  private EditCustomerViewModel editCustomerViewModel;
  private EditEmployeeViewModel editEmployeeViewModel;
  private EditReservationViewModel editReservationViewModel;

  public ViewModelFactory(Model model)
  {
    homeViewModel = new HomeViewModel(model);
    employeeHomeViewModel = new EmployeeHomeViewModel(model);
    employeeLoginViewModel = new EmployeeLoginViewModel(model);
    customerHomeViewModel = new CustomerHomeViewModel(model);
    addRoomViewModel = new AddRoomViewModel(model);
    employeeSignInViewModel = new EmployeeSignInViewModel(model);
    editRoomViewModel = new EditRoomViewModel(model);
    editCustomerViewModel = new EditCustomerViewModel(model);
    editEmployeeViewModel = new EditEmployeeViewModel(model);
    editReservationViewModel=new EditReservationViewModel(model);
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

  public EmployeeSignInViewModel getEmployeeSignInViewModel()
  {
    return employeeSignInViewModel;
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
}
