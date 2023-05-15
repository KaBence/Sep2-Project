package Server.Model;

import java.io.Serializable;

public class Customer extends Person implements Serializable
{
  private String paymentInfo;

  public Customer(String username, String firstName, String lastName,
      String phoneNo, String paymentInfo,String password)
  {
    super(firstName, lastName, password, phoneNo, username);
    this.paymentInfo=paymentInfo;
  }

  public String getUsername()
  {
    return super.getUsername();
  }

  public String getPassword()
  {
    return super.getPassword();
  }

  public String getFirstName()
  {
    return super.getFirstName();
  }

  public String getLastName()
  {
    return super.getLastName();
  }

  public String getPhoneNo()
  {
    return super.getPhoneNo();
  }

  public String getPaymentInfo()
  {
    return paymentInfo;
  }

  public String toString(){
    return super.toString()+paymentInfo;
  }

  public String customerInfo(){
    return super.info()+", PaymentInfo "+ paymentInfo;
  }

}
