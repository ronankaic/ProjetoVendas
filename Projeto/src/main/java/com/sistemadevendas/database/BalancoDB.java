package com.sistemadevendas.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.sistemadevendas.database.Conexao.conectar;

public class BalancoDB {
    public void listarSaidas() {
        String sql = "SELECT * FROM saidas ORDER BY ano, mes, dia";
        try (Statement stmt = conectar().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Lista de saidas:");
            while (rs.next()) {
                int id = rs.getInt("id_produto");
                String nome = rs.getString("produto");
                double preco = rs.getDouble("preco");
                int quantidade = rs.getInt("quantidade");
                String formaDePagamento = rs.getString("forma_pagamento");
                int dia = rs.getInt("dia");
                int mes = rs.getInt("mes");
                int ano = rs.getInt("ano");
                System.out.printf("ID: %d, Preco: %.2f, Quantidade: %d, Forma de pagamento: %s, Data: %d/%d/%d%n ", id, preco, quantidade, formaDePagamento, dia, mes, ano);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar saidas: " + e.getMessage());
        }
    }

    public double listarMesAno(int mes, int ano) {
        String sql = "SELECT * from saidas where mes = ? and ano = ? order by dia";
        double total = 0;

        try (PreparedStatement stmt = conectar().prepareStatement(sql)) {
            stmt.setInt(1, mes);
            stmt.setInt(2, ano);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_produto");
                String nome = rs.getString("produto");
                double preco = rs.getDouble("preco");
                int quantidade = rs.getInt("quantidade");
                String formaDePagamento = rs.getString("forma_pagamento");
                int dia = rs.getInt("dia");
                mes = rs.getInt("mes");
                ano = rs.getInt("ano");
                System.out.printf("ID: %d, Preco: %.2f, Quantidade: %d, Forma de pagamento: %s, Data: %d/%d/%d%n ", id, preco, quantidade, formaDePagamento, dia, mes, ano);
                total += preco;
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro ao aplicar o filtro: " + e.getMessage());
        }
        return total;
    }

    public static void listarFormaDePagamento(String formaDePagamento) {
        String sql = "SELECT * from saidas where forma_pagamento = ?";
        try (PreparedStatement stmt = conectar().prepareStatement(sql)) {
            stmt.setString(1, formaDePagamento);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_produto");
                String nome = rs.getString("produto");
                double preco = rs.getDouble("preco");
                int quantidade = rs.getInt("quantidade");
                formaDePagamento = rs.getString("forma_pagamento");
                int dia = rs.getInt("dia");
                int mes = rs.getInt("mes");
                int ano = rs.getInt("ano");
                System.out.printf("ID: %d, Preco: %.2f, Quantidade: %d, Forma de pagamento: %s, Data: %d/%d/%d%n ", id, preco, quantidade, formaDePagamento, dia, mes, ano);

            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro ao aplicar o filtro: " + e.getMessage());
        }

    }

    public static double listarMesAnoFormaPagamento(int mes, int ano, String formaDePagamento) {
        String sql = "SELECT * from saidas where mes = ? and ano = ? and forma_pagamento = ?";
        double total = 0;
        try (PreparedStatement stmt = conectar().prepareStatement(sql)) {
            stmt.setInt(1, mes);
            stmt.setInt(2, ano);
            stmt.setString(3, formaDePagamento);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_produto");
                String nome = rs.getString("produto");
                double preco = rs.getDouble("preco");
                int quantidade = rs.getInt("quantidade");
                formaDePagamento = rs.getString("forma_pagamento");
                int dia = rs.getInt("dia");
                mes = rs.getInt("mes");
                ano = rs.getInt("ano");
                System.out.printf("ID: %d, Preco: %.2f, Quantidade: %d, Forma de pagamento: %s, Data: %d/%d/%d%n ", id, preco, quantidade, formaDePagamento, dia, mes, ano);
                total += preco;
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro ao aplicar o filtro: " + e.getMessage());
        }
        return total;
    }

    public static double listarAno(int ano) {
        String sql = "SELECT * from saidas where ano = ?";
        double total = 0;
        try (PreparedStatement stmt = conectar().prepareStatement(sql)) {
            stmt.setInt(1, ano);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_produto");
                String nome = rs.getString("produto");
                double preco = rs.getDouble("preco");
                int quantidade = rs.getInt("quantidade");
                String formaDePagamento = rs.getString("forma_pagamento");
                int dia = rs.getInt("dia");
                int mes = rs.getInt("mes");
                ano = rs.getInt("ano");
                System.out.printf("ID: %d, Preco: %.2f, Quantidade: %d, Forma de pagamento: %s, Data: %d/%d/%d%n", id, preco, quantidade, formaDePagamento, dia, mes, ano);
                total += preco;
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro ao aplicar o filtro: " + e.getMessage());
        }
        return total;
    }

}
