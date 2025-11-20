package br.unipar.stockthis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioDAO {


    public UsuarioDAO() {
        try (Connection conn = Database.getConnection();
             Statement st = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS usuario (" +
                    "id IDENTITY PRIMARY KEY," +
                    "nome VARCHAR(100) NOT NULL," +
                    "email VARCHAR(120) NOT NULL UNIQUE," +
                    "senha VARCHAR(100) NOT NULL" +
                    ")";
            st.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean cadastrar(String nome, String email, String senha) {
        String sql = "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nome);
            ps.setString(2, email);
            ps.setString(3, senha);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }

    public Usuario login(String email, String senha) {
        String sql = "SELECT id, nome, email, senha FROM usuario WHERE email = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String senhaBanco = rs.getString("senha");

                if (senhaBanco.equals(senha)) {
                    int id = rs.getInt("id");
                    String nome = rs.getString("nome");
                    String emailBanco = rs.getString("email");
                    return new Usuario(id, nome, emailBanco, senhaBanco);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean alterarSenha(String email, String novaSenha) {
        String sql = "UPDATE usuario SET senha = ? WHERE email = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, novaSenha);
            ps.setString(2, email);
            int linhas = ps.executeUpdate();
            return linhas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
