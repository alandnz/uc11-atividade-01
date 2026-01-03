
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import java.util.ArrayList;

public class ProdutosDAO {

    public boolean cadastrarProduto(ProdutosDTO produto) {

        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

        try {
            Connection conn = new conectaDAO().connectDB();
            PreparedStatement prep = conn.prepareStatement(sql);

            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());

            return prep.executeUpdate() == 1;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e.getMessage());
            return false;
        }
    }

    public boolean venderProduto(int id) {
        String sql = "UPDATE produtos SET status = ? WHERE id = ?";

        try {
            Connection conn = new conectaDAO().connectDB();
            PreparedStatement prep = conn.prepareStatement(sql);

            prep.setString(1, "Vendido");
            prep.setInt(2, id);

            // retorna true se achou o id e mudou
            return prep.executeUpdate() == 1;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao vender: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        ArrayList<ProdutosDTO> lista = new ArrayList<>();
        String sql = "SELECT id, nome, valor, status FROM produtos";

        try {
            Connection conn = new conectaDAO().connectDB();
            PreparedStatement prep = conn.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();

            while (rs.next()) {
                ProdutosDTO p = new ProdutosDTO();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setValor(rs.getInt("valor"));
                p.setStatus(rs.getString("status"));
                lista.add(p);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar: " + e.getMessage());
        }

        return lista;
    }

    public ArrayList<ProdutosDTO> listarProdutosVendidos() {

        ArrayList<ProdutosDTO> listaVendidos = new ArrayList<>();
        String sql = "SELECT id, nome, valor, status FROM produtos WHERE status = ?";

        try {
            Connection conn = new conectaDAO().connectDB();
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setString(1, "Vendido");
            ResultSet rs = prep.executeQuery();

            while (rs.next()) {
                ProdutosDTO p = new ProdutosDTO();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setValor(rs.getInt("valor"));
                p.setStatus(rs.getString("status"));
                listaVendidos.add(p);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos vendidos: " + e.getMessage());
        }

        return listaVendidos;
    }

}
