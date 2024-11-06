package com.sistemadevendas.login;


import com.sistemadevendas.database.AdminDB;
import com.sistemadevendas.telas.TelaPadrao;
//import com.sistemadevendas.telas.TelaPadrao;

import static com.sistemadevendas.database.Conexao.desconectar;
import static com.sistemadevendas.utilitarios.Entrada.lerInt;
import static com.sistemadevendas.utilitarios.Entrada.lerString;

public class LoginA {
    Admin ad = new Admin();
    AdminDB adminBD = new AdminDB();

    public void loginAdmin() {
        System.out.println("Login de administrador");
        System.out.print("Login: ");
        String login = lerString();
        System.out.print("Senha: ");
        int senha = lerInt();


        int count = 3;
        boolean loginBemSucedido = adminBD.realizarlogin(login, senha);

        while (!loginBemSucedido && count > 1) {
            count--;
            System.out.println("Login ou senha incorretos. Você possui " + count + " tentativa(s).");
            System.out.print("Login: ");
            login = lerString();
            System.out.print("Senha: ");
            senha = lerInt();
            loginBemSucedido = adminBD.realizarlogin(login, senha);
        }

        if (loginBemSucedido) {
            System.out.println("Login realizado com sucesso!");
            System.out.println("==Bem vindo==");
            TelaPadrao telaPadrao = new TelaPadrao();
            telaPadrao.telaAdmin();
        } else {
            System.out.println("Login negado. Número de tentativas excedido.");
            desconectar();
            System.exit(0);
            //recomeçar programa
        }
    }

}



