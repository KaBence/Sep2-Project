package Server.Utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class FileLog
{
  private File logFile;
  private static CurrentTime currentTime;
  private static final Map<File,FileLog> instances=new HashMap<>();


  private FileLog(File logFile){
    this.logFile=logFile;
    currentTime=CurrentTime.getInstance();
  }
  public File getLogFile() {
    return logFile;
  }

  public static synchronized  FileLog getInstance(File logFile){
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
