package com.sistemadevendas.login;

import com.sistemadevendas.LimparTerminal;

import java.util.Scanner;

public class Menu {
    Scanner sc = new Scanner(System.in);
    PrimeiroAcessoA cadAdmin = new PrimeiroAcessoA();
    PrimeiroAcessoF cadFuncionario = new PrimeiroAcessoF();
    CadastrarDados cadastrarDados = new CadastrarDados();
    LimparTerminal lt = new LimparTerminal();

    public void menuPrimeiraVez() {
        while (true) {
            System.out.println("==Bem vindo==");
            System.out.println("Escolha uma opção abaixo: \n");
            System.out.println("1. Primeiro acesso(cadastro administrador)");
            System.out.println("2. Sair\n");
            int opcao = sc.nextInt();
            switch (opcao) {
                case 1:
                    primeiroAcessoAdmin();//primeiro acesso administrador
                    break;
                case 2:
                    System.out.println("Finalizando programa");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção invalida!");
                    break;
            }
        }
    }

    private void primeiroAcessoAdmin() {
        cadAdmin.CadastrarAdmin();
        cadastrarDados.cadastrarDados();

    }






}
