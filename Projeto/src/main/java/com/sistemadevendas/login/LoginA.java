package com.sistemadevendas.login;

import java.util.Scanner;

public class LoginA {
    Scanner sc = new Scanner(System.in);
    Admin ad = new Admin();

    public void loginAdmin() {
        System.out.println("Login de administrador");
        System.out.print("Login: ");
        String login = sc.nextLine();
        System.out.print("Senha: ");
        int senha = sc.nextInt();

        AdminBD adminBD = new AdminBD();
        int count = 3;
        boolean loginBemSucedido = adminBD.realizarlogin(login, senha);

        while (!loginBemSucedido && count > 1) {
            count--;
            System.out.println("Login ou senha incorretos. Você possui " + count + " tentativa(s).");
            sc.nextLine();
            System.out.print("Login: ");
            login = sc.nextLine();
            System.out.print("Senha: ");
            senha = sc.nextInt();
            loginBemSucedido = adminBD.realizarlogin(login, senha);
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
