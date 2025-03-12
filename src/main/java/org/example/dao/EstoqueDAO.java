package org.example.dao;

import org.example.model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstoqueDAO {
    public EstoqueDAO() {
        criarTabela();
    }

    public void criarTabela() {
        String sql = """
                CREATE TABLE IF NOT EXISTS estoque (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    nome VARCHAR(255),
                    quantidade INT
                )
                """;
        try (Connection conn = ConexaoMySQL.conectar();
             Statement smt = conn.createStatement()) {
            smt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela: " + e.getMessage());
        }
    }

    public void adicionarProduto(Produto produto) {
        String sql = "INSERT INTO estoque (nome, quantidade) VALUES (?, ?)";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getQuantidade());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar produto: " + e.getMessage());
        }
    }

    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM estoque";
        try (Connection conn = ConexaoMySQL.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                produtos.add(new Produto(rs.getInt("id"), rs.getString("nome"), rs.getInt("quantidade")));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }
        return produtos;
    }

    public void deletarProduto(int id) {
        String sql = "DELETE FROM estoque WHERE id = ?";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("🗑️ Produto removido com sucesso!");
            } else {
                System.out.println("⚠️ Nenhum produto encontrado com esse ID.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao deletar produto: " + e.getMessage());
        }
    }

    public void atualizarProduto(int id, String novoNome, int novaQuantidade) {
        String sql = "UPDATE estoque SET nome = ?, quantidade = ? WHERE id = ?";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, novoNome);
            stmt.setInt(2, novaQuantidade);
            stmt.setInt(3, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Produto atualizado com sucesso!");
            } else {
                System.out.println("⚠️ Nenhum produto encontrado com esse ID.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar produto: " + e.getMessage());
        }
    }
}
