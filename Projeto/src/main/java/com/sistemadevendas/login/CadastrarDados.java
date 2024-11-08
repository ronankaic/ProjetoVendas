package com.sistemadevendas.login;

import com.sistemadevendas.database.AdminDB;
import com.sistemadevendas.telas.TelaLogin;

import static com.sistemadevendas.utilitarios.Entrada.*;
import static com.sistemadevendas.utilitarios.LimparTerminal.limparTerminal;

public class CadastrarDados {
    AdminDB db = new AdminDB();


    public void atualizarDados() {
        limparTerminal();

        System.out.println("==Atualizar dados de venda==");
        System.out.println("Escolha uma opção abaixo: \n");
        System.out.println("1. Cadastrar chave pix");
        System.out.println("2. Taxa do cartão de credito");
        System.out.println("3. Taxa do cartão de debito");
        System.out.println("4. Voltar ao menu");
        int opcao = lerInt();
        switch (opcao) {
            case 1:
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
        double debito = lerDouble();
        db.taxaDebito(debito);
        atualizarDados();

    }

    private void taxaCredito() {
        System.out.print("Digite o valor da taxa de credito: ");
        double credito = lerDouble();
        db.taxaCredito(credito);
        atualizarDados();
    }

    private void cadastrarChavePix() {
        System.out.print("Digite a chave pix: ");
        String chave = lerString();
        db.chavePix(chave);
        atualizarDados();
    }

    public void cadastrarDados() {
        System.out.println("==Cadastrar dados de venda==");
        System.out.print("Digite a chave pix: ");
        String chave = lerString();
        db.chavePix(chave);
        System.out.print("Digite a taxa de debito: ");
        double debito = lerDouble();
        db.taxaDebito(debito);
        System.out.print("Digite a taxa de credito: ");
        double credito = lerDouble();
        db.taxaCredito(credito);

    }

}

