package org.example;

import org.example.dao.EstoqueDAO;
import org.example.model.Produto;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EstoqueDAO estoqueDAO = new EstoqueDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== MENU ESTOQUE ===");
            System.out.println("1. Adicionar produto");
            System.out.println("2. Listar produtos");
            System.out.println("3. Deletar produto");
            System.out.println("4. Atualizar produto");
            System.out.println("5. Sair");
            System.out.print("Escolha: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                System.out.print("Nome do produto: ");
                String nome = scanner.nextLine();
                System.out.print("Quantidade: ");
                int quantidade = scanner.nextInt();
                estoqueDAO.adicionarProduto(new Produto(0, nome, quantidade));
            } else if (opcao == 2) {
                List<Produto> produtos = estoqueDAO.listarProdutos();
                if (produtos.isEmpty()) {
                    System.out.println("📦 Nenhum produto no estoque.");
                } else {
                    System.out.println("📋 Produtos no estoque:");
                    for (Produto p : produtos) {
                        System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome() + " | Quantidade: " + p.getQuantidade());
                    }
                }
            } else if (opcao == 3) {
                System.out.print("ID do produto a deletar: ");
                int id = scanner.nextInt();
                estoqueDAO.deletarProduto(id);
            } else if (opcao == 4) {
                System.out.print("ID do produto a atualizar: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Novo nome: ");
                String novoNome = scanner.nextLine();
                System.out.print("Nova quantidade: ");
                int novaQuantidade = scanner.nextInt();
                estoqueDAO.atualizarProduto(id, novoNome, novaQuantidade);
            } else if (opcao == 5) {
                System.out.println("Saindo...");
                break;
            } else {
                System.out.println("Opção inválida.");
            }
        }
        scanner.close();
    }
}
