package serializacao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serializa {
   public static void main(String [] args) {
      Empregado e = new Empregado();
      e.setNome("João");
      e.setEndereco("Rua J 10");
      e.setCpf("11122233344");
      e.setNumero(101);
      try {
         FileOutputStream fileOut =
         new FileOutputStream("empregado.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
       out.writeObject(e);
         out.close();
         System.out.printf("Serializado");
      }catch(IOException i) {
         i.printStackTrace();
      }
   }
}
