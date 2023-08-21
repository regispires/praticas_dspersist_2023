import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Exemplo03 {
  public static void main(String[] args) throws IOException {
    InputStream is = new FileInputStream(args[0]);
    int b;
    int i = 1;
    while ((b = is.read()) != -1) {
      System.out.println(i++ + " " + b + " " + Integer.toHexString(b));
    }
  }
}
