import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Exemplo05ReadFileWithBufferedReader {
  public static void main(String[] args) throws IOException {
    InputStream is = new FileInputStream(args[0]);
    //InputStreamReader isr = new InputStreamReader(is);
    //InputStreamReader isr = new InputStreamReader(is, "UTF-8");
    InputStreamReader isr = new InputStreamReader(is, "ISO-8859-1");
    BufferedReader br = new BufferedReader(isr);
    while (br.ready()) {
      String linha = br.readLine();
      System.out.println(linha);
    }
    br.close();
  }
}
