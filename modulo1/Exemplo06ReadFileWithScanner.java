import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Exemplo06ReadFileWithScanner {
  public static void main(String[] args) throws IOException {
    InputStream is = new FileInputStream(args[0]);

    //Scanner entrada = new Scanner(is);
    Scanner entrada = new Scanner(is, "UTF-16");
    while (entrada.hasNextLine()) {
      System.out.println(entrada.nextLine());
    }
    entrada.close();
  }
}
