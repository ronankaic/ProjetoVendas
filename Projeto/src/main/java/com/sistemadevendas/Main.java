package com.sistemadevendas;


import com.sistemadevendas.login.LoginF;
import com.sistemadevendas.login.TelaLogin;
import com.sistemadevendas.login.TelaPadrao;
import com.sistemadevendas.login.TesteJava;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        TelaLogin telaLogin = new TelaLogin();
        TelaPadrao telaPadrao = new TelaPadrao();
        LoginF login = new LoginF();
        //telaLogin.EscolhaLogin();
        TesteJava testeJava = new TesteJava();
        testeJava.inserirAdmin();
        testeJava.testeConexaoA();
        testeJava.testeConexaoF();



    }
}