package Client.ViewModel;

import Client.Model.Model;

public class ViewModelFactory
{
  private HomeViewModel homeViewModel;

  private EmployeeLoginViewModel employeeLoginViewModel;

  private EmployeeHomeViewModel employeeHomeViewModel;

  private CustomerHomeViewModel customerHomeViewModel;
  private AddRoomViewModel addRoomViewModel;

  public ViewModelFactory(Model model){
    homeViewModel=new HomeViewModel(model);
    employeeHomeViewModel=new EmployeeHomeViewModel(model);
    employeeLoginViewModel=new EmployeeLoginViewModel(model);
    customerHomeViewModel=new CustomerHomeViewModel(model);
  }

  public HomeViewModel getHomeViewModel(){
    return homeViewModel;
  }

  public EmployeeLoginViewModel getEmployeeLoginViewModel()
  {
    return employeeLoginViewModel;
  }

  public EmployeeHomeViewModel getEmployeeHomeViewModel()
  {
    return employeeHomeViewModel;
  }

  public CustomerHomeViewModel getCustomerHomeViewModel()
  {
    return customerHomeViewModel;
  }

  public AddRoomViewModel getAddRoomViewModel()
  {
    return addRoomViewModel;
  }
}
