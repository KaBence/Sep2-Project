package Client.ViewModel;

import Client.Model.Model;

public class ViewModelFactory
{
  private HomeViewModel homeViewModel;

  public ViewModelFactory(Model model){
    homeViewModel=new HomeViewModel(model);
  }

  public HomeViewModel getHomeViewModel(){
    return homeViewModel;
  }
}
