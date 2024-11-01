package com.sistemadevendas.login;

import java.util.Scanner;

public class LoginF {
    public void loginF() {
        Scanner sc = new Scanner(System.in);
        Funcionario funcionario = new Funcionario();
        PrimeiroAcessoF primeiroAcesso = new PrimeiroAcessoF();

        System.out.println("Login Funcionario");
        System.out.print("Login: ");
        String login = sc.nextLine();
        System.out.print("Senha: ");
        int senha = sc.nextInt();

        FuncionarioDB funcionarioDB = new FuncionarioDB();
        int count = 3;
        boolean loginBemSucedido = funcionarioDB.realizarLogin(login,senha);

        while (!loginBemSucedido && count > 1) {
            count--;
            System.out.println("Login ou senha incorretos. Você possui " + count + " tentativa(s).");
            sc.nextLine();
            System.out.print("Login: ");
            login = sc.nextLine();
            System.out.print("Senha: ");
            senha = sc.nextInt();
            loginBemSucedido = funcionarioDB.realizarLogin(login,senha);
        }
        if (loginBemSucedido) {
            System.out.println("Login realizado com sucesso!");
            System.out.println("==Bem vindo==");
        } else {
            System.out.println("Login negado. Número de tentativas excedido.");
            //recomeçar programa
        }


    }


}
