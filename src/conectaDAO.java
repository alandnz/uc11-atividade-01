
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {

    public Connection connectDB() {
        Connection conn = null;

        try {
            String url = "jdbc:mysql://localhost:3306/uc11atividade2";
            String usuario = "usuario";
            String senha = "senha123";

            conn = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conectado ao banco de dados");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        }
        return conn;
    }

}
