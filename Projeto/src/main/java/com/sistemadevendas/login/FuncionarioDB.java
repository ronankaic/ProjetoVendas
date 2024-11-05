package com.sistemadevendas.login;

import com.sistemadevendas.ConexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FuncionarioDB {
    Connection conexao = ConexaoBD.conectar();
    private String nomeFuncionario;
    private int senhaFuncionario;



    public void cadastrarFuncionario(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario (nome, senha, idF) VALUES (?,?,?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getNome());
            stmt.setInt(2, funcionario.getSenha());
            stmt.setInt(3, funcionario.getIdF());
            stmt.executeUpdate();

            System.out.println("Funcionario cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar funcionario: " + e.getMessage());
        }
    }

    boolean realizarLogin(String nomeFuncionario, int senhaFuncionario) {
        String sql = "SELECT * FROM funcionario WHERE nome = ? AND senha = ?";
        boolean loginBemSucedido = false;
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nomeFuncionario);
            stmt.setInt(2, senhaFuncionario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    nomeFuncionario = rs.getString("nome");
                    senhaFuncionario = rs.getInt("senha");
                    loginBemSucedido = true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return loginBemSucedido;
    }


}
