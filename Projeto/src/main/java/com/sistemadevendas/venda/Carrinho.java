package com.sistemadevendas.venda;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private final List<Produto> produtos;

    public Carrinho() {
        this.produtos = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
        System.out.println("Produto adicionado ao carrinho: " + produto.getNome());
    }

    public double calcularTotal() {
        return produtos.stream()
                .mapToDouble(produto -> produto.getPreco() * produto.getQuantidade())
                .sum();
    }

    public List<Produto> getProdutos() {
        return produtos;
    }


    public void limparCarrinho() {
        produtos.clear();
    }
}

