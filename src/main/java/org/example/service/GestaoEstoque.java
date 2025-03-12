package org.example.service;

import org.example.dao.EstoqueDAO;
import org.example.model.Produto;

import java.util.List;

public class GestaoEstoque {

    private EstoqueDAO estoqueDAO;

    public GestaoEstoque(){
        this.estoqueDAO = new EstoqueDAO();
    }


    public void adicionarProduto(String nome, int quantidade){
        Produto produto = new Produto(0, nome, quantidade);
        estoqueDAO.adicionarProduto(produto);
        System.out.println("Produto adicionado com sucesso!");
    }

    public void listarProdutos(){
        List<Produto> produtos = estoqueDAO.listarProdutos();
        if (produtos.isEmpty()){
            System.out.println("Nenhum produto encontrado.");
        }else {
            produtos.forEach(System.out::println);
        }
    }

}
