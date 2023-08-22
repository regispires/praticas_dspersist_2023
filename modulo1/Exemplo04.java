import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Exemplo04 {
  public static void main(String[] args) throws IOException {
    InputStream is = new FileInputStream(args[0]);
    //InputStreamReader isr = new InputStreamReader(is);
    //InputStreamReader isr = new InputStreamReader(is, "UTF-8");
    InputStreamReader isr = new InputStreamReader(is, "ISO-8859-1");
    int b;
    int i = 1;
    while ((b = isr.read()) != -1) {
      char c = (char) b;
      System.out.println(i++ + " " + b + " " + Integer.toHexString(b) +
        " " + c );
    }
    isr.close();
  }
}
