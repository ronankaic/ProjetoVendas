package com.sistemadevendas.database;

import com.sistemadevendas.venda.Carrinho;
import com.sistemadevendas.venda.Produto;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

import static com.sistemadevendas.database.Conexao.conectar;
import static com.sistemadevendas.utilitarios.Entrada.*;

public class VendaDB {
    Carrinho carrinho = new Carrinho();
    public double taxaCredito = 0;
    public double taxaDebito = 0;
    public String chavePix;

    public void listarProdutos() {
        String sql = "SELECT * FROM produtos";
        try (Statement stmt = conectar().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Lista de produtos:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                int quantidade = rs.getInt("quantidade");
                System.out.printf("ID: %d, Nome: %s, Preco: %.2f, Quantidade: %d%n", id, nome, preco, quantidade);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }

    }

    public void cadastrarProdutos() {
        listarProdutos();
        String sql = "SELECT MAX(id) AS ultimo_id FROM produtos";
        int ultimoId = 0;

        try (Statement stmt = conectar().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                ultimoId = rs.getInt("ultimo_id");
                if (ultimoId == 0) {
                    ultimoId = 1;
                } else {
                    ultimoId += 1;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao localizar o ultimo ID: " + e.getMessage());
            throw new RuntimeException(e);
        }
        System.out.println("==Cadastro de Produtos==");
        System.out.print("Digite o nome do produto: ");
        String nome = lerString();
        System.out.print("Digite o preço do produto: ");
        double preco = lerDouble();
        System.out.print("Digite a quantidade em estoque do produto: ");
        int quantidade = lerInt();

        Produto produto = new Produto(ultimoId, nome, preco, quantidade);

        String inserirSQL = "INSERT INTO produtos (id, nome, preco, quantidade) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = conectar().prepareStatement(inserirSQL)) {
            pstmt.setInt(1, produto.getId());
            pstmt.setString(2, produto.getNome());
            pstmt.setDouble(3, produto.getPreco());
            pstmt.setInt(4, produto.getQuantidade());
            pstmt.executeUpdate();
            System.out.println("Produto cadastrado com sucesso! Último ID: " + ultimoId);
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void excluirProdutos() {
        listarProdutos();

        System.out.println("==Exluir produtos==");
        System.out.print("Digite o ID do produto que deseja exluir: ");
        int id = lerInt();
        String sql = "DELETE FROM produtos WHERE id = ?";

        try (PreparedStatement pstmt = conectar().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int linhas = pstmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("Produto excluido com sucesso!");
            } else {
                System.out.println("Produto não encontrado!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao excluir produto: " + e.getMessage());
        }
    }



    public void atualizarEstoque(int idProduto, int estoque) {
        String sql = "UPDATE produtos SET quantidade = ? WHERE id = ?;";

        try (PreparedStatement pstmt = conectar().prepareStatement(sql)) {
            pstmt.setInt(1, estoque);
            pstmt.setInt(2, idProduto);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar estoque do produto: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void registrarSaidas(List<Produto> produtos, String formaPagamento) {
        String sql = "INSERT INTO saidas (id_produtos, produtos, preco, quantidade, forma_pagamento, dia, mes, ano) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        LocalDate dataAtual = LocalDate.now();

        try (PreparedStatement pstmt = conectar().prepareStatement(sql)) {
            for (Produto produto : produtos) {
                pstmt.setInt(1, produto.getId());
                pstmt.setString(2, produto.getNome());
                pstmt.setDouble(3, produto.getPreco());
                pstmt.setInt(4, produto.getQuantidade());
                pstmt.setString(5, formaPagamento);
                pstmt.setInt(6, dataAtual.getDayOfMonth());
                pstmt.setInt(7, dataAtual.getMonthValue());
                pstmt.setInt(8, dataAtual.getYear());

                pstmt.executeUpdate();
            }
            System.out.println("Saídas registradas com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao registrar saída: " + e.getMessage());
        }
    }

    public void PesquisarDados() {
        String sql = "SELECT * FROM dados WHERE id=0";
        try (PreparedStatement pst = conectar().prepareStatement(sql);
            ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                taxaCredito = rs.getDouble("taxa_credito");
                taxaDebito = rs.getDouble("taxa_debito");
                chavePix = rs.getString("chava_pix");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao localizar dados de vendas" + e.getMessage());
        }
    }


}
