package com.sistemadevendas.login;

import com.sistemadevendas.ConexaoBD;
import com.sistemadevendas.LimparTerminal;

import java.util.Random;
import java.util.Scanner;

public class PrimeiroAcessoF {
    LimparTerminal lt = new LimparTerminal();
    Scanner sc = new Scanner(System.in);
    FuncionarioDB func = new FuncionarioDB();
    int idF;



    public void CadastrarFuncionario(){
        System.out.println("==Cadastro de funcionario==");
        System.out.print("Digite o nome do funcionario: ");
        String nome = sc.nextLine();
        System.out.print("Senha(sequência numérica de 5 digitos): ");
        int senha = sc.nextInt();
        while (senha < 10000 || senha > 100000) {
            System.out.println("A senha deve ter no mínimo e no máximo 5 digitos.");
            System.out.print("Senha: ");
            senha = sc.nextInt();
        }
        setIdF();
        System.out.println("Id Criado com sucesso ID= " + idF);

        Funcionario funcionario = new Funcionario(nome, senha,idF);
        func.cadastrarFuncionario(funcionario);

    }
/*
    private void gerarId() {
        Random rand = new Random();
        int numeroF;

        numeroF = rand.nextInt(10000);
        idF = numeroF;

    }
*/


    public void setIdF(){
        Random rand = new Random();
        int limite = 100000; // gera um inteiro aleatório entre 0 e 100.000
        int randNumF = rand.nextInt(limite);
        if (randNumF < 10000){ //ID de funcioinário obrigatoriamente > 10.000, ou seja, tem 5 algarismos ou +
            do{
                randNumF = rand.nextInt(limite);
            } while(randNumF < 10000);
        }
        idF = randNumF;

    }

}
