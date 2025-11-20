package br.unipar.stockthis;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public ProdutoDAO() {
        try (Connection conn = Database.getConnection();
             Statement st = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS produto (" +
                    "id IDENTITY PRIMARY KEY," +
                    "usuario_id INT NOT NULL," +
                    "codigo INT NOT NULL," +
                    "nome VARCHAR(150) NOT NULL," +
                    "categoria VARCHAR(100)," +
                    "quantidade INT NOT NULL," +
                    "preco DOUBLE NOT NULL" +
                    ")";
            st.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void inserir(int usuarioId, Produto p) {
        String sql = "INSERT INTO produto (usuario_id, codigo, nome, categoria, quantidade, preco) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, usuarioId);
            ps.setInt(2, p.getCodigo());
            ps.setString(3, p.getNome());
            ps.setString(4, p.getCategoria());
            ps.setInt(5, p.getQuantidade());
            ps.setDouble(6, p.getPreco());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Produto> listarPorUsuario(int usuarioId) {
        List<Produto> lista = new ArrayList<>();

        String sql = "SELECT codigo, nome, categoria, quantidade, preco " +
                "FROM produto WHERE usuario_id = ? ORDER BY codigo";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, usuarioId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String nome = rs.getString("nome");
                String categoria = rs.getString("categoria");
                int quantidade = rs.getInt("quantidade");
                double preco = rs.getDouble("preco");

                Produto p = new Produto(codigo, nome, categoria, quantidade, preco);
                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void atualizarQuantidade(int usuarioId, int codigo, int novaQuantidade) {
        String sql = "UPDATE produto SET quantidade = ? WHERE usuario_id = ? AND codigo = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, novaQuantidade);
            ps.setInt(2, usuarioId);
            ps.setInt(3, codigo);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarPreco(int usuarioId, int codigo, double novoPreco) {
        String sql = "UPDATE produto SET preco = ? WHERE usuario_id = ? AND codigo = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, novoPreco);
            ps.setInt(2, usuarioId);
            ps.setInt(3, codigo);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int usuarioId, int codigo) {
        String sql = "DELETE FROM produto WHERE usuario_id = ? AND codigo = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, usuarioId);
            ps.setInt(2, codigo);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}