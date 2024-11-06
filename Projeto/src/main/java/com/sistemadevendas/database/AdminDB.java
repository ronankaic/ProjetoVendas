package com.sistemadevendas.database;

import com.sistemadevendas.login.Admin;


import java.sql.*;
import java.util.Scanner;

import static com.sistemadevendas.database.Conexao.conectar;

public class AdminDB {
    Scanner sc = new Scanner(System.in);

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
        String sql = "UPDATE dados SET taxa_debito = ? WHERE id = 1";
        try (PreparedStatement stmt = conectar().prepareStatement(sql)) {
            stmt.setDouble(1, debito);
            stmt.executeUpdate();
            System.out.println("Taxa de Debito atualizada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar Taxa de Debito: " + e.getMessage());
        }
    }

    public void taxaCredito(double credito) {

        String sql = "UPDATE dados SET taxa_credito = ? WHERE id = 1";
        try (PreparedStatement stmt = conectar().prepareStatement(sql)) {
            stmt.setDouble(1, credito);
            stmt.executeUpdate();
            System.out.println("Taxa de Credito atualizada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar Taxa de Credito: " + e.getMessage());
        }

    }

    public void chavePix(String pix) {

        if (pix == null || pix.trim().isEmpty()) {
            System.out.println("Chave Pix inválida. Por favor, tente novamente.");
            return;
        }

        String sql = "UPDATE dados SET chave_pix = ? WHERE id = 0";
        try (PreparedStatement stmt = conectar().prepareStatement(sql)) {
            stmt.setString(1, pix);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Chave Pix atualizada com sucesso!");
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
        sc.nextLine();
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
            throw new RuntimeException(e);
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


}

