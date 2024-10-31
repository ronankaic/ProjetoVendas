package com.sistemadevendas.login;

import com.sistemadevendas.LimparTerminal;

import java.util.Scanner;

public class Menu {
    Scanner sc = new Scanner(System.in);
    PrimeiroAcessoA cadAdmin = new PrimeiroAcessoA();
    PrimeiroAcessoF cadFuncionario = new PrimeiroAcessoF();
    LimparTerminal lt = new LimparTerminal();
    public int usuario = 0;

    public void menuPrimeiraVez() {
        while (true) {
            System.out.println("==Bem vindo==");
            System.out.println("Escolha uma opção abaixo: ");
            System.out.println("1. Primeiro acesso(cadastro administrador)");
            System.out.println("2. Cadastro funcionario");
            System.out.println("3. Acessar sistema");
            System.out.println("4. Sair");
            int opcao = sc.nextInt();
            switch (opcao) {
                case 1:
                    primeiroAcessoAdmin();//primeiro acesso administrador
                    break;
                case 2:
                    usuario = 1;
                    primeiroAcessoFuncionario();//primeiro acesso funcionario
                    break;
                case 3:
                    acessarSistema();
                    break;
                case 4:
                    System.out.println("Finalizando programa");
                    System.exit(0);
                default:
                    System.out.println("Opção invalida!");
                    break;
            }

        }

    }

    private void primeiroAcessoAdmin() {
        lt.limparTerminal();
        cadAdmin.CadastrarAdmin();
        System.out.println("Deseja cadastrar os dados da venda?");
        System.out.println("1. Sim");
        System.out.println("2. Não");
        int opcao = sc.nextInt();
        switch (opcao) {
            case 1:
                cadastrarDados();
                break;
            case 2:
                acessarSistema();
                break;
        }

    }

    public void primeiroAcessoFuncionario() {
        lt.limparTerminal();
        cadFuncionario.CadastrarFuncionario();

    }

    public void acessarSistema() {


    }

    private void cadastrarDados() {
    }

    public void acessarSaidas() {

    }


}
