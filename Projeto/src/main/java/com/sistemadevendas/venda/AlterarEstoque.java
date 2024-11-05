package com.sistemadevendas.venda;

import com.sistemadevendas.ConexaoBD;

import java.sql.Connection;
import java.util.Scanner;

public class AlterarEstoque {
    TelaInicial tela = new TelaInicial();
    Connection conexao = ConexaoBD.conectar();
    Scanner sc = new Scanner(System.in);

    public void alterarEstoque() {
        tela.listarProdutos(conexao);
        int id = 1;
        while (id != 0) {
            System.out.println("==Alterar os dados do estoque==");
            while (true) {
                System.out.print("Digite o ID do produto (ou 0 para finalizar): ");
                id = sc.nextInt();
                if(id == 0){
                    break;
                }
                System.out.print("Digite a quantidade: ");
                int quantidade = sc.nextInt();
                tela.atualizarEstoque(conexao, id, quantidade);
                System.out.println("Estoque alterado com sucesso!");
                System.out.println();
            }


        }
    }
}
