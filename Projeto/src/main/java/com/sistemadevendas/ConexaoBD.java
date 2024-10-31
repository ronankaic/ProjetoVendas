package com.sistemadevendas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private static final String URL = "jdbc:sqlite:C:\\Users\\Ronan\\Documents\\SistemaDeVendas\\BD\\BancoDeDados.db";

    public static Connection conectar() {
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao Banco de Dados: " + e.getMessage());
        }
        return conexao;
    }


}
