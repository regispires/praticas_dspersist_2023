import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Exemplo02 {
  public static void main(String[] args) throws IOException {
    InputStream is = new FileInputStream(args[0]);
    int b = is.read();
    System.out.println(b);
    is.close();
  }
}
