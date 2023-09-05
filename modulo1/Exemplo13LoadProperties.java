import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Exemplo13LoadProperties {
  public static void main(String[] args) throws IOException {
    Properties prop = new Properties();

      prop.load(new FileInputStream("config.properties"));
      // get the property value and print it out
      System.out.println(prop.getProperty("database"));
      System.out.println(prop.getProperty("dbuser"));
      System.out.println(prop.getProperty("dbpassword"));

  } 
}
