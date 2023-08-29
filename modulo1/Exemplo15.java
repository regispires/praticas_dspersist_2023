import java.io.IOException;
import java.util.Properties;

public class Exemplo15 {
  public static void main(String[] args) {
    String delimitador = ",";
    String s = "1,João,Rua J 10,88999346556";
    String[] campos = s.split(delimitador);
    for (String campo : campos) {
      System.out.println(campo);
    }
  }
}