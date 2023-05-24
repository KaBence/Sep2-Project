package Client.Utility;

import javafx.scene.control.Alert;

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

  public void showAndWait()
  {
    if (!alertType.equals(Alert.AlertType.NONE))
    {
      Alert x = new Alert(alertType);
      x.setHeaderText(headerText);
      x.setContentText(contentText);
      x.showAndWait();
    }
  }

  public String toString()
  {
    return alertType.toString()+", "+getHeaderText()+", "+getContentText();
  }
}
