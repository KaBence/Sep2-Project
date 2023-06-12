package Server.Utility.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class FileLog
{
  private static String homePath = System.getProperty("user.home");
  private static File downloads = new File(homePath, "Downloads/SepLogs");
  private File logFile;
  private static CurrentTime currentTime;
  private static final Map<Types,FileLog> instances=new HashMap<>();

  private FileLog(Types logFile){
    this.logFile=new File(downloads,logFile+".txt");
    currentTime=CurrentTime.getInstance();
  }
  public File getLogFile() {
    return logFile;
  }
  public static synchronized  FileLog getInstance(Types logFile){
    if (!instances.containsKey(logFile)){
      instances.put(logFile,new FileLog(logFile));
    }
    return instances.get(logFile);
  }
  public void log(String message) throws IOException
  {
    try (
        FileWriter fileWriter = new FileWriter(getLogFile(), true);
        PrintWriter writer = new PrintWriter(fileWriter)) {
      String logLine = currentTime.getFormattedTime() + " - " + message;
      writer.println(logLine);
    }
  }
}
