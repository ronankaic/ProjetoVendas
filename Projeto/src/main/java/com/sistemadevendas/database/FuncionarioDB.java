package com.sistemadevendas.database;

import com.sistemadevendas.login.Funcionario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.sistemadevendas.database.Conexao.conectar;

public class FuncionarioDB {

    public void cadastrarFuncionario(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario (nome, senha, idF) VALUES (?,?,?)";
        try (PreparedStatement stmt = conectar().prepareStatement(sql)) {
            stmt.setString(1, funcionario.getNome());
            stmt.setInt(2, funcionario.getSenha());
            stmt.setInt(3, funcionario.getIdF());
            stmt.executeUpdate();

            System.out.println("Funcionario cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar funcionario: " + e.getMessage());
        }
    }

    public boolean realizarLogin(String nomeFuncionario, int senhaFuncionario) {
        String sql = "SELECT * FROM funcionario WHERE nome = ? AND senha = ?";
        boolean loginBemSucedido = false;
        try (PreparedStatement stmt = conectar().prepareStatement(sql)) {
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

    public void checarDadosf(int senha) {
        String sql = "SELECT * FROM funcionario where senha=?";

        try (PreparedStatement stmt = conectar().prepareStatement(sql)){
            stmt.setInt(1, senha);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("nome");
                int Id = rs.getInt("idF");
                System.out.printf("Bem vindo %s, seu ID de acesso e %d%n", nome, Id);
            } else {
                System.out.println("Usuario não encontrado");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro ao realizar a pesquisa: " + e.getMessage());
        }

    }

    public boolean pesquisarIdF(int idF) {
        boolean retorno = false;
        String sql = "SELECT * FROM funcionario where idF=?";
        try (PreparedStatement stmt = conectar().prepareStatement(sql)){
            stmt.setInt(1, idF);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                retorno = true;
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return retorno;

    }

    public String pesquisarNomeF(int idF) {
        String sql = "SELECT * FROM funcionario where idF=?";
        String nome = "";
        try (PreparedStatement stmt = conectar().prepareStatement(sql)){
            stmt.setInt(1, idF);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                nome = rs.getString("nome");
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nome;
    }


}

