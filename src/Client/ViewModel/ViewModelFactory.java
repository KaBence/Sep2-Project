package Client.ViewModel;

import Client.Model.Model;

public class ViewModelFactory
{
  private HomeViewModel homeViewModel;

  private EmployeeLoginViewModel employeeLoginViewModel;

  private EmployeeHomeViewModel employeeHomeViewModel;

  private CustomerHomeViewModel customerHomeViewModel;
  private AddRoomViewModel addRoomViewModel;
  private RegisterEmployeeViewModel registerEmployeeViewModel;
  private EditRoomViewModel editRoomViewModel;
  private EditCustomerViewModel editCustomerViewModel;
  private EditEmployeeViewModel editEmployeeViewModel;
  private EditReservationViewModel editReservationViewModel;
  private AddReviewViewModel addReviewViewModel;

  public ViewModelFactory(Model model)
  {
    homeViewModel = new HomeViewModel(model);
    employeeHomeViewModel = new EmployeeHomeViewModel(model);
    employeeLoginViewModel = new EmployeeLoginViewModel(model);
    customerHomeViewModel = new CustomerHomeViewModel(model);
    addRoomViewModel = new AddRoomViewModel(model);
    registerEmployeeViewModel = new RegisterEmployeeViewModel(model);
    editRoomViewModel = new EditRoomViewModel(model);
    editCustomerViewModel = new EditCustomerViewModel(model);
    editEmployeeViewModel = new EditEmployeeViewModel(model);
    editReservationViewModel=new EditReservationViewModel(model);
    addReviewViewModel= new AddReviewViewModel(model);
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

  public RegisterEmployeeViewModel getRegisterEmployeeViewModel()
  {
    return registerEmployeeViewModel;
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
}
