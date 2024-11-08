package com.sistemadevendas.login;


import com.sistemadevendas.database.FuncionarioDB;

import java.util.Random;

import static com.sistemadevendas.utilitarios.Entrada.lerInt;
import static com.sistemadevendas.utilitarios.Entrada.lerString;
import static com.sistemadevendas.utilitarios.LimparTerminal.limparTerminal;

public class PrimeiroAcessoF {
    FuncionarioDB func = new FuncionarioDB();
    int idF;


    public void CadastrarFuncionario() {
        limparTerminal();
        System.out.println("==Cadastro de funcionario==");
        System.out.print("Digite o nome do funcionario: ");
        String nome = lerString();
        System.out.print("Senha(sequência numérica de 5 digitos): ");
        int senha = lerInt();
        while (senha < 10000 || senha > 100000) {
            System.out.println("A senha deve ter no mínimo e no máximo 5 digitos.");
            System.out.print("Senha: ");
            senha = lerInt();
        }
        setIdF();
        System.out.println("Id Criado com sucesso ID= " + idF);
        lerString();
        Funcionario funcionario = new Funcionario(nome, senha, idF);
        func.cadastrarFuncionario(funcionario);

    }


    public void setIdF() {
        Random rand = new Random();
        int limite = 100000; // gera um inteiro aleatório entre 0 e 100.000
        int randNumF = rand.nextInt(limite);
        if (randNumF < 10000) { //ID de funcioinário obrigatoriamente > 10.000, ou seja, tem 5 algarismos ou +
            do {
                randNumF = rand.nextInt(limite);
            } while (randNumF < 10000);
        }
        idF = randNumF;

    }

}

