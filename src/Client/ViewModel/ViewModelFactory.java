package Client.ViewModel;

import Client.Model.Model;

public class ViewModelFactory
{
  private HomeViewModel homeViewModel;

  private EmployeeLoginViewModel employeeLoginViewModel;

  private EmployeeHomeViewModel employeeHomeViewModel;

  private CustomerHomeViewModel customerHomeViewModel;
  private AddRoomViewModel addRoomViewModel;
  private EditRoomViewModel editRoomViewModel;

  public ViewModelFactory(Model model){
    homeViewModel=new HomeViewModel(model);
    employeeHomeViewModel=new EmployeeHomeViewModel(model);
    employeeLoginViewModel=new EmployeeLoginViewModel(model);
    customerHomeViewModel=new CustomerHomeViewModel(model);
    addRoomViewModel=new AddRoomViewModel(model);
    editRoomViewModel=new EditRoomViewModel(model);
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

  public EditRoomViewModel getEditRoomViewModel()
  {
    return editRoomViewModel;
  }

  public AddRoomViewModel getAddRoomViewModel()
  {
    return addRoomViewModel;
  }
}
