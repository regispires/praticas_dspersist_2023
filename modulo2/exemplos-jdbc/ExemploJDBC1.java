import java.sql.*;

public class ExemploJDBC1 {
  public static void main(String[] args) {
    try {
      Connection con =
      DriverManager.getConnection("jdbc:postgresql://localhost/teste",
                                   "postgres","postgres");
      System.out.println("Conectado");
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
