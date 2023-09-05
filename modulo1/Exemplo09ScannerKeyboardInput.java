import java.util.Scanner;

public class Exemplo09ScannerKeyboardInput {
  public static void main(String[] args) {
    Scanner entrada = new Scanner(System.in);
    while (entrada.hasNextLine()) {
      System.out.println(entrada.nextLine());
    }
    entrada.close();
   }
}