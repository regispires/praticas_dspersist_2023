package serializacao;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Deserializa {

   public static void main(String [] args) {
      Empregado e = null;
      try {
         FileInputStream fileIn = new FileInputStream("empregado.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
       e = (Empregado) in.readObject();
         in.close();
      }catch(Exception ex) {
         ex.printStackTrace();
      }
      System.out.println(e);
   }
}
