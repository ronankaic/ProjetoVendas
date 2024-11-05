package com.sistemadevendas.login;



import com.sistemadevendas.LimparBuffers;

import java.util.Scanner;

public class CadastrarDados {
    Scanner sc = new Scanner(System.in);
    TelaLogin login = new TelaLogin();
    AdminBD db = new AdminBD();
    LimparBuffers l = new LimparBuffers();


    public void atualizarDados() {


        System.out.println("==Atualizar dados de venda==");
        System.out.println("Escolha uma opção abaixo: \n");
        System.out.println("1. Cadastrar chave pix");
        System.out.println("2. Taxa do cartão de credito");
        System.out.println("3. Taxa do cartão de debito");
        System.out.println("4. Voltar ao menu");
        int opcao = sc.nextInt();
        switch (opcao) {
            case 1:
                l.limparBuffer(sc);
                cadastrarChavePix();
                break;
            case 2:
                taxaCredito();
                break;
            case 3:
                taxaDebito();
                break;
            case 4:

                return;
            default:
                System.out.println("Opção Invalida");
                atualizarDados();
                break;
        }
    }


    private void taxaDebito() {
        System.out.print("Digite o valor da taxa de debito: ");
        db.taxaDebito();
        atualizarDados();

    }

    private void taxaCredito() {
        System.out.print("Digite o valor da taxa de credito: ");
        db.taxaCredito();
        atualizarDados();
    }

    private void cadastrarChavePix() {
        System.out.print("Digite a chave pix: ");
        db.chavePix();
        atualizarDados();
    }

    public void cadastrarDados() {
        System.out.println("==Cadastrar dados de venda==");
        System.out.print("Digite a chave pix: ");
        l.limparBuffer(sc);
        db.chavePix();
        System.out.print("Digite a taxa de debito: ");
        db.taxaDebito();
        System.out.print("Digite a taxa de credito: ");
        db.taxaCredito();

    }

}
