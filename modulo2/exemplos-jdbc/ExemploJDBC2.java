import java.sql.*;

public class ExemploJDBC2 {

  public static void main(String[] args) {
    Connection c = null;
    try {
      c = DriverManager.getConnection("jdbc:postgresql://localhost/teste",
                                   "postgres","postgres");
      PreparedStatement ps = c.prepareStatement(
                       "select nome from teste order by nome");
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        String nome = rs.getString("nome");
        System.out.println(nome);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
        if (c != null) {
            try {
                c.close();
                System.out.println("Conexão fechada");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
  }
}
