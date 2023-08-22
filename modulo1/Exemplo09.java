import java.io.InputStream;
import java.util.Scanner;

public class Exemplo09 {
  public static void main(String[] args) {
    Scanner entrada = new Scanner(System.in);
    while (entrada.hasNextLine()) {
      System.out.println(entrada.nextLine());
    }
    entrada.close();
   }
}