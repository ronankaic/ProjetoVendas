package com.sistemadevendas;

import com.sistemadevendas.login.*;
import com.sistemadevendas.venda.TelaInicial;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        LoginF login = new LoginF();
        PrimeiroAcessoF primeiroAcesso = new PrimeiroAcessoF();
        CadastrarDados cadastrarDados = new CadastrarDados();
        PrimeiroAcessoA acesso = new PrimeiroAcessoA();
        //primeiroAcesso.CadastrarFuncionario();
        //login.loginF();
        acesso.CadastrarAdmin();





    }
}