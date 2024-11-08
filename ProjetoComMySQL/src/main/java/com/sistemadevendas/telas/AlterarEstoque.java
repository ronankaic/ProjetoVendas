package com.sistemadevendas.telas;

import com.sistemadevendas.database.VendaDB;
import com.sistemadevendas.venda.TelaInicial;

import java.util.Scanner;


import static com.sistemadevendas.utilitarios.LimparTerminal.limparTerminal;

public class AlterarEstoque {
    TelaInicial tela = new TelaInicial();
    VendaDB venda = new VendaDB();

    Scanner sc = new Scanner(System.in);

    public void alterarEstoque() {
        limparTerminal();
        venda.listarProdutos();
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
                venda.atualizarEstoque( id, quantidade);
                System.out.println("Estoque alterado com sucesso!");
                System.out.println();
            }
        }
    }
}

