import java.io.FileOutputStream;
import java.util.Properties;

public class Exemplo12 {
  public static void main(String[] args) throws Exception {
    Properties prop = new Properties();

    prop.setProperty("database", "localhost");
    prop.setProperty("dbuser", "mkyong");
    prop.setProperty("dbpassword", "password");

    prop.store(new FileOutputStream("config.properties"),
                null);
  }
}
