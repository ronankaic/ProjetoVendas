package com.sistemadevendas.database;

import com.sistemadevendas.login.Admin;


import java.sql.*;
import java.util.Scanner;

import static com.sistemadevendas.database.Conexao.conectar;
import static com.sistemadevendas.utilitarios.Entrada.lerString;


public class AdminDB {

    public void cadastrarAdmin(Admin admin) {

        String sql = "INSERT INTO admin (nome, senha, idA) VALUES (?,?,?)";
        try (PreparedStatement stmt = conectar().prepareStatement(sql)) {
            stmt.setString(1, admin.getNome());
            stmt.setInt(2, admin.getSenha());
            stmt.setInt(3, admin.getIdA());
            stmt.executeUpdate();

            System.out.println("Administrador cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar admin: " + e.getMessage());
        }
    }

    public int obterUltimoId() {
        String sql = "SELECT MAX(id) AS ultimo_id FROM admin";
        int ultimoId = 0;
        try (PreparedStatement stmt = conectar().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                ultimoId = rs.getInt("ultimo_id");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao localizar o último ID: " + e.getMessage());
        }
        return ultimoId;
    }

    public boolean realizarlogin(String nome, int senha) {
        String sql = "SELECT * FROM admin WHERE nome = ? AND senha = ?";
        boolean loginBemSucedido = false;

        try (PreparedStatement stmt = conectar().prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setInt(2, senha);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    nome = rs.getString("nome");
                    senha = rs.getInt("senha");
                    loginBemSucedido = true;  //  login encontrado, retorna como true
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao realizar login: " + e.getMessage());
        }

        return loginBemSucedido;
    }


    public void taxaDebito(double debito) {
        String sql = "UPDATE dados SET taxa_debito = ? WHERE id = 0";
        try (PreparedStatement stmt = conectar().prepareStatement(sql)) {
            stmt.setDouble(1, debito);
            stmt.executeUpdate();
            System.out.println("Taxa de Debito atualizada com sucesso!");
            lerString();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar Taxa de Debito: " + e.getMessage());
        }
    }

    public void taxaCredito(double credito) {

        String sql = "UPDATE dados SET taxa_credito = ? WHERE id = 0";
        try (PreparedStatement stmt = conectar().prepareStatement(sql)) {
            stmt.setDouble(1, credito);
            stmt.executeUpdate();
            System.out.println("Taxa de Credito atualizada com sucesso!");
            lerString();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar Taxa de Credito: " + e.getMessage());
        }

    }

    public void chavePix(String pix) {

        if (pix == null || pix.trim().isEmpty()) {
            System.out.println("Chave Pix inválida. Por favor, tente novamente.");
            return;
        }

        String sql = "UPDATE dados SET chava_pix = ? WHERE id = 0";
        try (PreparedStatement stmt = conectar().prepareStatement(sql)) {
            stmt.setString(1, pix);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Chave Pix atualizada com sucesso!");
                lerString();
            } else {
                System.out.println("Nenhuma linha foi atualizada. Verifique se o ID é válido.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar a Chave Pix: " + e.getMessage());
        }
    }

    public void checarDadosA(int senha) {
        String sql = "SELECT * FROM admin where senha=?";

        try (PreparedStatement stmt = conectar().prepareStatement(sql)) {
            stmt.setInt(1, senha);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("nome");
                int Id = rs.getInt("idA");
                System.out.printf("Bem vindo %s, seu ID de acesso e %d%n", nome, Id);
            } else {
                System.out.println("Usuario não encontrado");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro ao realizar a pesquisa: " + e.getMessage());
        }
        lerString();
    }

    public boolean pesquisarIdA(int idA) {
        boolean retorno = false;
        String sql = "SELECT * FROM admin where idA=?";
        try (PreparedStatement stmt = conectar().prepareStatement(sql)) {
            stmt.setInt(1, idA);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                retorno = true;
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("ID não encontrado" + e.getMessage());
        }

        return retorno;

    }

    public String pesquisarNomeA(int idA) {
        String sql = "SELECT * FROM admin where idA=?";
        String nome = "";
        try (PreparedStatement stmt = conectar().prepareStatement(sql)) {
            stmt.setInt(1, idA);
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

    public int quantidadeAdmin() {
        String sql = "SELECT COUNT(*) FROM admin";
        int linhas = 0;
        try (PreparedStatement stmt = conectar().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                linhas = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return linhas;

    }

   /* public void totalAdmin(int totalA){
        String sql = "update dados_usuarios set quantidade_admin = ? where id = 1";
        try(PreparedStatement stmt = conectar().prepareStatement(sql)){
            stmt.setInt(1,totalA);
        }catch (SQLException e) {
            System.out.println("Erro ao atualizar quantidade de admin: " + e.getMessage());
        }
    }

    public void totalFuncionario(int totalF){
        String sql = "update dados_usuarios set quantidade_admin = ? where id = 1";
        try(PreparedStatement stmt = conectar().prepareStatement(sql)){
            stmt.setInt(1,totalF);
        }catch (SQLException e) {
            System.out.println("Erro ao atualizar quantidade de admin: " + e.getMessage());
        }
    }*/

    public void salvarLogin(String nome, int senha) {
        String sql = "update dados_usuarios set nome = ?, senha = ? where id = 1";
        try (PreparedStatement stmt = conectar().prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setInt(2, senha);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar quantidade de admin: " + e.getMessage());
        }
    }


    public String nomeIdA() {
        String sql = "SELECT * FROM dados_usuarios";
        String nome = null;
        int senha = 0;

        try (PreparedStatement stmt = conectar().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                nome = rs.getString("nome");
                senha = rs.getInt("senha");
            } else {
                System.out.println("Nenhum usuário encontrado na tabela dados_usuarios.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nome;
    }

    public int resgatarIdA(String nome) {
        String sql = "SELECT * FROM admin WHERE nome = ?";
        int idA = 0;

        try (PreparedStatement stmt = conectar().prepareStatement(sql)) {
            stmt.setString(1, nome);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int senha = rs.getInt("senha");
                    idA = rs.getInt("idA");
                } else {
                    System.out.println("Administrador não encontrado com o nome especificado.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return idA;
    }

    public void deletarAdmin(int id) {
        String sql = "delete from admin where idA = ?";
        try (PreparedStatement stmt = conectar().prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro ao deletar admin: " + e.getMessage());
        }
    }

    public void deletarFuncionario(int id) {
        String sql = "delete from funcionario where idF = ?";
        try (PreparedStatement stmt = conectar().prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro ao deletar admin: " + e.getMessage());
        }
    }


    public void listarAdmin() {
        String sql = "select * from admin";
        try (PreparedStatement stmt = conectar().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            System.out.println("Lista de funcionarios");
            while (rs.next()) {
                String nomeF = rs.getString("nome");
                int senha = rs.getInt("senha");
                int idA = rs.getInt("idA");
                System.out.printf("Id: %d, Nome: %s, Senha: %d%n", idA, nomeF, senha);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar funcionarios: " + e.getMessage());
        }
    }

    public void listarFuncionarios() {
        String sql = "select * from funcionario";
        try (PreparedStatement stmt = conectar().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            System.out.println("Lista de funcionarios");
            while (rs.next()) {
                String nomeF = rs.getString("nome");
                int senha = rs.getInt("senha");
                int idF = rs.getInt("idF");
                System.out.printf("Id: %d, Nome: %s, Senha: %d%n", idF, nomeF, senha);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar funcionarios: " + e.getMessage());
        }
    }
}

