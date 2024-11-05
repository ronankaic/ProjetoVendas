package com.sistemadevendas.venda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class RegistroSaida {

    public void registrarSaidas(Connection conexao, List<Produto> produtos, String formaPagamento) {
        String sql = "INSERT INTO saidas (id_produto, produto, preco, quantidade, forma_pagamento, dia, mes, ano) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        LocalDate dataAtual = LocalDate.now();

        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
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

}
