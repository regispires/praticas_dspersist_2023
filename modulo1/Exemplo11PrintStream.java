import java.io.IOException;
import java.io.PrintStream;

public class Exemplo11PrintStream {

  public static void main(String[] args) throws IOException {
    PrintStream ps = new PrintStream("saida-win.txt");
    ps.println("UFC Quixadá");
    ps.close();
  }

}
