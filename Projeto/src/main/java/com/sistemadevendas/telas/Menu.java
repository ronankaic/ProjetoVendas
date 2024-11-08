package com.sistemadevendas.telas;

import com.sistemadevendas.login.CadastrarDados;
import com.sistemadevendas.login.PrimeiroAcessoA;

import static com.sistemadevendas.database.Conexao.desconectar;
import static com.sistemadevendas.utilitarios.Entrada.lerInt;
import static com.sistemadevendas.utilitarios.LimparTerminal.limparTerminal;

public class Menu {
    PrimeiroAcessoA cadAdmin = new PrimeiroAcessoA();
    CadastrarDados cadastrarDados = new CadastrarDados();
    TelaLogin telaLogin = new TelaLogin();

    public void menuPrimeiraVez() {
        while (true) {
            limparTerminal();
            System.out.println("==Bem vindo==");
            System.out.println("Escolha uma opção abaixo: \n");
            System.out.println("1. Primeiro acesso(cadastro administrador)");
            System.out.println("2. Sair\n");
            int opcao = lerInt();
            switch (opcao) {
                case 1:
                    telaLogin.primeiraExecucao();//primeiro acesso administrador
                    break;
                case 2:
                    System.out.println("Finalizando programa");
                    desconectar();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção invalida!");
                    break;
            }
        }
    }








}

