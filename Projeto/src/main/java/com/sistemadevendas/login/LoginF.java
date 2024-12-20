package com.sistemadevendas.login;


import com.sistemadevendas.database.FuncionarioDB;
import com.sistemadevendas.telas.TelaPadrao;

import static com.sistemadevendas.database.Conexao.desconectar;
import static com.sistemadevendas.utilitarios.Entrada.lerInt;
import static com.sistemadevendas.utilitarios.Entrada.lerString;
import static com.sistemadevendas.utilitarios.LimparTerminal.limparTerminal;


public class LoginF {
    public void loginF() {
        Funcionario funcionario = new Funcionario();
        PrimeiroAcessoF primeiroAcesso = new PrimeiroAcessoF();
        FuncionarioDB funcionarioDB = new FuncionarioDB();

        limparTerminal();
        System.out.println("Login Funcionario");
        System.out.print("Login: ");
        String login = lerString();
        System.out.print("Senha: ");
        int senha = lerInt();


        int count = 3;
        boolean loginBemSucedido = false;
        loginBemSucedido = funcionarioDB.realizarLogin(login, senha);


        while (!loginBemSucedido && count > 1) {
            count--;
            System.out.println("Login ou senha incorretos. Você possui " + count + " tentativa(s).");
            System.out.print("Login: ");
            login = lerString();
            System.out.print("Senha: ");
            senha = lerInt();
            loginBemSucedido = funcionarioDB.realizarLogin(login, senha);
        }
        if (loginBemSucedido) {
            System.out.println("Login realizado com sucesso!");
            TelaPadrao telaPadrao = new TelaPadrao();
            telaPadrao.telaFuncionario();
        } else {
            System.out.println("Login negado. Número de tentativas excedido.");
            desconectar();
            System.exit(0);
        }


    }


}
