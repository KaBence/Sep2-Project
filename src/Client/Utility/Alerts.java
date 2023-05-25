package Client.Utility;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Alerts
{
  private Alert.AlertType alertType;
  private String headerText;
  private String contentText;

  public Alerts(Alert.AlertType alertType, String headerText,
      String contentText)
  {
    this.alertType = alertType;
    this.headerText = headerText;
    this.contentText = contentText;
  }

  public Alert.AlertType getAlertType()
  {
    return alertType;
  }


  public String getHeaderText()
  {
    return headerText;
  }

  public String getContentText()
  {
    return contentText;
  }

  public Alert showAndWait()
  {
    if (!alertType.equals(Alert.AlertType.NONE))
    {
      Alert x = new Alert(alertType);
      x.setHeaderText(headerText);
      x.setContentText(contentText);
      x.showAndWait();
      return x;
    }
    return null;
  }

  public ButtonType getResult()
  {
    return showAndWait().getResult();
  }

  @Override public boolean equals(Object obj)
  {
    if (obj == null || !obj.getClass().equals(getClass()))
    {
      return false;
    }
    Alerts other = (Alerts) obj;
    return other.alertType.equals(alertType) && other.contentText.equals(contentText) && other.headerText.equals(headerText);
  }

  public String toString()
  {
    return alertType.toString()+", "+getHeaderText()+", "+getContentText();
  }
}
