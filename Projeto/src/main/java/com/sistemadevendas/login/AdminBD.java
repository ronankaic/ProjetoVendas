package com.sistemadevendas.login;

import com.sistemadevendas.ConexaoBD;



import java.sql.*;
import java.util.Scanner;

public class AdminBD {

    Connection conexao = ConexaoBD.conectar();
    Scanner sc = new Scanner(System.in);

    private String nomeAdmin;
    private int senhaAdmin;

    public String getNomeAdmin() {
        return nomeAdmin;
    }

    public void setNomeAdmin(String nomeAdmin) {
        this.nomeAdmin = nomeAdmin;
    }

    public int getSenhaAdmin() {
        return senhaAdmin;
    }

    public void setSenhaAdmin(int senhaAdmin) {
        this.senhaAdmin = senhaAdmin;
    }

    public void cadastrarAdmin(Admin admin) {

        String sql = "INSERT INTO admin (nome, senha, idA) VALUES (?,?,?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
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
        try (Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

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

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setInt(2, senha);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    nomeAdmin = rs.getString("nome");
                    senhaAdmin = rs.getInt("senha");
                    loginBemSucedido = true;  //  login encontrado, retorna como true
                }
            }

            conexao.close();
        } catch (SQLException e) {
            System.out.println("Erro ao realizar login: " + e.getMessage());
        }

        return loginBemSucedido;
    }


   public void taxaDebito() {
       double debito = sc.nextDouble();
       String sql = "UPDATE dados SET taxa_debito = ? WHERE id = 1";
       try {
           PreparedStatement stmt = conexao.prepareStatement(sql);
           stmt.setDouble(1, debito);
           stmt.executeUpdate();
           System.out.println("Taxa de Debito atualizada com sucesso!");
       } catch (SQLException e) {
           System.out.println("Erro ao atualizar Taxa de Debito: " + e.getMessage());
       }
   }

    public void taxaCredito(){
        double credito = sc.nextDouble();
        String sql = "UPDATE dados SET taxa_credito = ? WHERE id = 1";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setDouble(1, credito);
            stmt.executeUpdate();
            System.out.println("Taxa de Credito atualizada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar Taxa de Credito: " + e.getMessage());
        }

    }

    public void chavePix() {
        System.out.print("Digite a nova chave Pix: ");
        String pix = sc.nextLine();

        if (pix == null || pix.trim().isEmpty()) {
            System.out.println("Chave Pix inválida. Por favor, tente novamente.");
            return;
        }

        String sql = "UPDATE dados SET pix = ? WHERE id = 1";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
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


}
