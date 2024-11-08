package com.sistemadevendas.telas;

import static com.sistemadevendas.utilitarios.Entrada.lerInt;
import static com.sistemadevendas.utilitarios.LimparTerminal.limparTerminal;

import com.sistemadevendas.database.AdminDB;
import com.sistemadevendas.login.CadastrarDados;
import com.sistemadevendas.login.LoginA;
import com.sistemadevendas.login.LoginF;
import com.sistemadevendas.login.PrimeiroAcessoA;


public class TelaLogin {
    PrimeiroAcessoA primeiroAcessoA = new PrimeiroAcessoA();
    CadastrarDados cadastrarDados = new CadastrarDados();
    TelaPadrao telaPadrao = new TelaPadrao();
    int i = new AdminDB().quantidadeAdmin();
    LoginA loginA = new LoginA();
    LoginF loginF = new LoginF();


    public void EscolhaLogin() {
        limparTerminal();
        System.out.println("==Faça login no sistema==");
        System.out.println("Escolha uma opção abaixo: \n");
        System.out.println("1. Administrador");
        System.out.println("2. Funcionario");
        int opcao = lerInt();
        if (opcao == 1) {
            loginA.loginAdmin();
        } else if (opcao == 2) {
            loginF.loginF();

        } else {
            System.out.println("Opção invalida, escolha 1 ou 2");
            EscolhaLogin();
        }

    }

    public void primeiraExecucao() {
        primeiroAcessoA.CadastrarAdmin();
        if (i == 1) {
            boolean continuar = true;
            do {
                System.out.println("Deseja sobrescrever os dados de venda?");
                System.out.println("1. Sim");
                System.out.println("2. Nao");
                int opc = lerInt();
                switch (opc) {
                    case 1:
                        continuar = false;
                        cadastrarDados.cadastrarDados();
                        telaPadrao.telaAdmin();
                        break;
                    case 2:
                        continuar = false;
                        telaPadrao.telaAdmin();
                        break;
                    default:
                        System.out.println("Opção invalida, escolha 1 ou 2");
                        break;
                }
            } while (continuar);
        }
        cadastrarDados.cadastrarDados();
        telaPadrao.telaAdmin();
    }
}

