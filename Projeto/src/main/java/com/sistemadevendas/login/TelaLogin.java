package com.sistemadevendas.login;

import com.sistemadevendas.ConexaoBD;

import java.util.Scanner;

public class TelaLogin {
    Scanner sc = new Scanner(System.in);
    ConexaoBD usuario = new ConexaoBD();
    LoginA loginA = new LoginA();
    LoginF loginF = new LoginF();

    public void EscolhaLogin() {
        System.out.println("==Faça login no sistema==");
        System.out.println("Escolha uma opção abaixo: \n");
        System.out.println("1. Administrador");
        System.out.println("2. Funcionario");
        int opcao = sc.nextInt();
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
        System.out.println("==Bem vindo==");
        PrimeiroAcessoA primeiroAcessoA = new PrimeiroAcessoA();
        CadastrarDados cadastrarDados = new CadastrarDados();
        TelaPadrao telaPadrao = new TelaPadrao();
        primeiroAcessoA.CadastrarAdmin();
        cadastrarDados.cadastrarDados();
        telaPadrao.telaAdmin();

    }

}
