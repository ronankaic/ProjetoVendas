package com.sistemadevendas;


import com.sistemadevendas.login.Admin;
import com.sistemadevendas.login.Funcionario;

import java.sql.*;

public class ConexaoBD {

    private static final String URL = "jdbc:sqlite:C:\\Users\\Ronan\\Documents\\SistemaDeVendas\\BD\\BancoDeDados.db";

    private static Connection conexao = null;

    public static Connection conectar() {
        if (conexao == null) { // Verifica se a conexão já existe
            try {
                conexao = DriverManager.getConnection(URL);

                // Define o tempo de espera para bloqueios no SQLite
                try (Statement stmt = conexao.createStatement()) {
                    stmt.execute("PRAGMA busy_timeout = 3000"); // Aguarda até 3 segundos se o banco estiver ocupado
                }

                System.out.println("Conexão com o banco de dados estabelecida com sucesso.");

            } catch (SQLException e) {
                System.err.println("Erro ao conectar ao Banco de Dados: " + e.getMessage());
            }
        }
        return conexao;
    }




    public boolean pesquisarIdA(int idA) {
        boolean retorno = false;
        String sql = "SELECT * FROM admin where idA=?";
        try {
            PreparedStatement stmt = conectar().prepareStatement(sql);
            stmt.setInt(1, idA);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                retorno = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return retorno;

    }

    public boolean pesquisarIdF(int idF) {
        boolean retorno = false;
        String sql = "SELECT * FROM funcionario where idF=?";
        try {
            PreparedStatement stmt = conectar().prepareStatement(sql);
            stmt.setInt(1, idF);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                retorno = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return retorno;

    }

    public String pesquisarNomeA(int idA) {
        String sql = "SELECT * FROM admin where idA=?";
        String nome = "";
        try {
            PreparedStatement stmt = conectar().prepareStatement(sql);
            stmt.setInt(1, idA);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                nome = rs.getString("nome");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nome;
    }

    public String pesquisarNomeF(int idF) {
        String sql = "SELECT * FROM funcionario where idF=?";
        String nome = "";
        try {
            PreparedStatement stmt = conectar().prepareStatement(sql);
            stmt.setInt(1, idF);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                nome = rs.getString("nome");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nome;
    }


    public void listarSaidas() {
        String sql = "SELECT * FROM saidas ORDER BY ano,mes,dia";
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

        try {
            PreparedStatement stmt = conectar().prepareStatement(sql);
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


        } catch (SQLException e) {
            System.out.println("Erro ao aplicar o filtro: " + e.getMessage());
        }
        return total;
    }

    public void listarFormaDePagamento(String formaDePagamento) {
        String sql = "SELECT * from saidas where forma_pagamento = ?";
        try {
            PreparedStatement stmt = conectar().prepareStatement(sql);
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

        } catch (SQLException e) {
            System.out.println("Erro ao aplicar o filtro: " + e.getMessage());
        }

    }

    public double listarMesAnoFormaPagamento(int mes, int ano, String formaDePagamento) {
        String sql = "SELECT * from saidas where mes = ? and ano = ? and forma_pagamento = ?";
        double total = 0;
        try {
            PreparedStatement stmt = conectar().prepareStatement(sql);
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
        } catch (SQLException e) {
            System.out.println("Erro ao aplicar o filtro: " + e.getMessage());
        }
        return total;
    }

    public double listarAno(int ano) {
        String sql = "SELECT * from saidas where ano = ?";
        double total = 0;
        try {
            PreparedStatement stmt = conectar().prepareStatement(sql);
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
                System.out.printf("ID: %d, Preco: %.2f, Quantidade: %d, Forma de pagamento: %s, Data: %d/%d/%d%n ", id, preco, quantidade, formaDePagamento, dia, mes, ano);
                total += preco;
            }


        } catch (SQLException e) {
            System.out.println("Erro ao aplicar o filtro: " + e.getMessage());
        }
        return total;
    }

    public void checarDadosf(int senha) {
        String sql = "SELECT * FROM funcionario where senha=?";

        try {
            PreparedStatement stmt = conectar().prepareStatement(sql);
            stmt.setInt(1, senha);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("nome");
                int Id = rs.getInt("idF");
                System.out.printf("Bem vindo %s, seu ID de acesso e %d%n", nome, Id);
            } else {
                System.out.println("Usuario não encontrado");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao realizar a pesquisa: " + e.getMessage());
        }

    }

    public void checarDadosA(int senha) {
        String sql = "SELECT * FROM admin where senha=?";

        try {
            PreparedStatement stmt = conectar().prepareStatement(sql);
            stmt.setInt(1, senha);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("nome");
                int Id = rs.getInt("idA");
                System.out.printf("Bem vindo %s, seu ID de acesso e %d%n", nome, Id);
            } else {
                System.out.println("Usuario não encontrado");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao realizar a pesquisa: " + e.getMessage());
        }

    }

}