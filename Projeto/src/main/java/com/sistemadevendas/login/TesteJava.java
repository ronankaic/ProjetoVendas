package com.sistemadevendas.login;

import com.sistemadevendas.ConexaoBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TesteJava {

    public void testeConexaoF() {
        ConexaoBD con = new ConexaoBD();
        ConexaoBD.conectar();
        String sql = "SELECT * from funcionario";
        try (
                PreparedStatement stmt = ConexaoBD.conectar().prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int senha = rs.getInt("senha");
                int idF = rs.getInt("idF");
                System.out.printf("ID : %d, Nome: %s, Senha: %d, IdF: %d\n", id, nome, senha, idF);
            }
            ConexaoBD.conectar().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void inserirAdmin() {
        ConexaoBD con = new ConexaoBD();
        ConexaoBD.conectar();
        String sql = "INSERT INTO admin (nome,senha,idA) VALUES (?,?,?)";
        try (PreparedStatement stmt = ConexaoBD.conectar().prepareStatement(sql)){
            stmt.setString(1, "admin");
            stmt.setInt(2, 1);
            stmt.setInt(3, 10);
            stmt.executeUpdate();
            ConexaoBD.conectar().close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void testeConexaoA() {
        ConexaoBD con = new ConexaoBD();
        ConexaoBD.conectar();
        String sql = "SELECT * from admin";
        try (
                PreparedStatement stmt = ConexaoBD.conectar().prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int senha = rs.getInt("senha");
                int idF = rs.getInt("idA");
                System.out.printf("ID : %d, Nome: %s, Senha: %d, IdF: %d\n", id, nome, senha, idF);
            }
            ConexaoBD.conectar().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


}
