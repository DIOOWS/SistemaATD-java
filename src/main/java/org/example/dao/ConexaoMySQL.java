package org.example.dao;

import java.sql.*;

public class ConexaoMySQL {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USUARIO = "root"; // Troque pelo seu usuário do MySQL
    private static final String SENHA = "1234";   // Troque pela sua senha do MySQL
    private static final String BANCO = "estoqueatd";

    // Criação do banco se não existir
    public static void criarBancoSeNaoExistir() {
        try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
             Statement stmt = conexao.createStatement()) {
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + BANCO);
            System.out.println("Banco de dados '" + BANCO + "' verificado/criado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao criar o banco: " + e.getMessage());
        }
    }

    // Conexão com o banco de dados
    public static Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL + BANCO, USUARIO, SENHA);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Erro ao conectar ao banco: " + e.getMessage());
            return null;
        }
    }

    // Testando a criação do banco e a conexão
    public static void main(String[] args) {
        criarBancoSeNaoExistir(); // Criar/verificar banco
        try (Connection conexao = conectar()) {
            if (conexao != null) {
                System.out.println("Conexão bem-sucedida!");
            } else {
                System.err.println("Falha na conexão com o banco de dados.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão: " + e.getMessage());
        }
    }
}
