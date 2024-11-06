package com.sistemadevendas.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static Connection conexao;

    public static Connection conectar() {
        if (conexao == null) {
            String url = "jdbc:mysql://root:jQgyRLYCDykUOlYryLVZCwyUxrWQycyE@junction.proxy.rlwy.net:20467/railway";
            String usuario = "root";
            String senha = "jQgyRLYCDykUOlYryLVZCwyUxrWQycyE";

            try {
                conexao = DriverManager.getConnection(url, usuario, senha);
                System.out.println("Conectado com sucesso!");

            } catch (SQLException e) {
                System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            }
        }
        return conexao;
    }

    public static void desconectar() {
        if (conexao != null) {
            try {
                conexao.close();
                System.out.println("Conexão com o banco de dados encerrada com sucesso!");
                conexao = null; // Garante que a próxima chamada a conectar() possa criar uma nova conexão
            } catch (SQLException e) {
                System.out.println("Erro ao desconectar banco de dados: " + e.getMessage());
                e.printStackTrace(); // Exibe o stack trace completo
            }
        }
    }

}
