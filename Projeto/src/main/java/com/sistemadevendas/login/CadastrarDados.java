package com.sistemadevendas.login;

import com.sistemadevendas.ConexaoBD;

import java.sql.Connection;
import java.util.Scanner;

public class CadastrarDados {
    Scanner sc = new Scanner(System.in);
    Connection conexao = ConexaoBD.conectar();
    TelaPadrao tela = new TelaPadrao();


    public void cadastrarDados() {


        System.out.println("Cadastrar dados de venda");
        System.out.println("Escolha uma opção abaixo: ");
        System.out.println("1. Taxa do cartão de debito");
        System.out.println("2. Taxa do cartão de credito");
        System.out.println("3. Cadastrar chave pix");
        System.out.println("4. Voltar ao menu");
        int opcao = sc.nextInt();
        switch (opcao) {
            case 1:
                taxaDebito();
                break;
            case 2:
                taxaCredito();
                break;
            case 3:
                cadastrarChavePix();
                break;
            case 4:
                tela.teste();
                break;
            default:
                System.out.println("Opção Invalida");
                cadastrarDados();
                break;
        }
    }


    private void taxaDebito() {
    }

    private void taxaCredito() {

    }

    private void cadastrarChavePix() {

    }

}
