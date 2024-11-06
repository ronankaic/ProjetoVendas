package com.sistemadevendas.telas;

import com.sistemadevendas.database.AdminDB;
import com.sistemadevendas.database.FuncionarioDB;
import com.sistemadevendas.login.CadastrarDados;
import com.sistemadevendas.login.PrimeiroAcessoF;

import static com.sistemadevendas.database.Conexao.desconectar;
import static com.sistemadevendas.utilitarios.Entrada.lerInt;
import static com.sistemadevendas.utilitarios.LimparTerminal.limparTerminal;


import com.sistemadevendas.balanco.Balanco;
import com.sistemadevendas.venda.TelaInicial;

public class TelaPadrao {
    AdminDB adminDB = new AdminDB();
    FuncionarioDB funcionarioDB = new FuncionarioDB();
    Balanco balanco = new Balanco();
    CadastrarDados dados = new CadastrarDados();
    PrimeiroAcessoF loginF = new PrimeiroAcessoF();
    AlterarEstoque alterarEstoque = new AlterarEstoque();
    TelaInicial telaInicial = new TelaInicial();


    public void telaAdmin() {
        limparTerminal();
        System.out.println("==Bem vindo==\n");
        System.out.println("1. Acessar balanço");
        System.out.println("2. Alterar dados de vendas");
        System.out.println("3. Cadastrar funcionario");
        System.out.println("4. Alterar estoque");
        System.out.println("5. Acessar sistema de vendas");
        System.out.println("6. Checar dados do usuario");
        System.out.println("7. Sair");
        int op = lerInt();
        switch (op) {
            case 1:
                balanco.acessar();
                telaAdmin();
                break;
            case 2:
                dados.atualizarDados();
                telaAdmin();
                break;
            case 3:
                loginF.CadastrarFuncionario();
                telaAdmin();
                break;
            case 4:
                alterarEstoque.alterarEstoque();
                telaAdmin();
                break;
            case 5:
                telaInicial.mostrarMenu();
                telaAdmin();
                break;
            case 6:
                System.out.print("Digite a sua senha: ");
                int senha = lerInt();
                adminDB.checarDadosA(senha);
                telaAdmin();
                break;
            case 7:
                System.out.println("Obrigado, volte sempre!");
                System.out.println("Finalizando programa");
                desconectar();
                System.exit(0);
                break;
            default:
                System.out.println("Escolha invalida");
                telaAdmin();
                break;

        }
    }

    public void telaFuncionario() {
        limparTerminal();
        System.out.println("==Bem vindo==\n");
        System.out.println("1. Acessar balanço");
        System.out.println("2. Acessar sistema de vendas");
        System.out.println("3. Checar dados do usuario");
        System.out.println("4. Sair");
        int op = lerInt();
        switch (op) {
            case 1:
                balanco.acessar();
                telaFuncionario();
                break;
            case 2:
                telaInicial.mostrarMenu();
                telaFuncionario();
                break;
            case 3:
                System.out.print("Digite a sua senha: ");
                int senha = lerInt();
                funcionarioDB.checarDadosf(senha);
                telaFuncionario();
                break;
            case 4:
                System.out.println("Obrigado, volte sempre!");
                System.out.println("Finalizando programa");
                desconectar();
                System.exit(0);
                break;
            default:
                System.out.println("Escolha invalida");
                telaFuncionario();
                break;
        }
    }
}

