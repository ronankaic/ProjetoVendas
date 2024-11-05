package com.sistemadevendas.venda;

import com.sistemadevendas.ConexaoBD;

import java.sql.*;

public class ResgatarDados {
    Connection conexao = ConexaoBD.conectar();


    public double taxaCredito;
    public double taxaDebito;
    public String chavePix;


    public void PesquisarDados(){
        String sql = "SELECT * FROM dados WHERE id=1";
        try (
                PreparedStatement pst = conexao.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                taxaCredito = rs.getDouble("taxa_credito");
                taxaDebito = rs.getDouble("taxa_debito");
                chavePix = rs.getString("pix");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao localizar dados de vendas");
        }
    }



}
