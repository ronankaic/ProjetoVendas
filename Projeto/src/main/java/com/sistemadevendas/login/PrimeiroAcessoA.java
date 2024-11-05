package com.sistemadevendas.login;

import com.sistemadevendas.LimparTerminal;

import java.util.Random;
import java.util.Scanner;

public class PrimeiroAcessoA {
    LimparTerminal lt = new LimparTerminal();
    Scanner sc = new Scanner(System.in);
    int idA;
    AdminBD adminBD = new AdminBD();


    public void CadastrarAdmin() {

        if (adminBD.obterUltimoId() < 90) {
            System.out.println("==Cadastro de administrador==");
            System.out.print("Digite o nome do administrador: ");
            String nome = sc.nextLine();
            System.out.print("Senha(sequência numérica de 5 digitos): ");
            int senha = sc.nextInt();
            sc.nextLine();
            while (senha < 10000 || senha > 100000) {
                System.out.println("A senha deve ter no mínimo e no máximo 5 digitos.");
                System.out.print("Senha: ");
                senha = sc.nextInt();
            }

            setIdA();

            System.out.println("Id Criado com sucesso ID= " + idA);

            Admin admin = new Admin(nome, senha, idA);
            AdminBD adminBD = new AdminBD();
            adminBD.cadastrarAdmin(admin);
        } else {
            System.out.println("Numero maximo de administradores cadastrados");
            System.out.println("Faça login ou cadastre um novo funcionario");
            Menu menu = new Menu();


        }


    }

    public void setIdA() {
        Random rand = new Random();
        int limite = 100000; // gera um inteiro aleatório entre 0 e 100.000
        int randNumA = rand.nextInt(limite);
        if (randNumA > 10000) { //ID de admin obrigatoriamente < 10.000, ou seja, tem até 4 algarismos
            do {
                randNumA = rand.nextInt(limite);
            } while (randNumA > 10000);
        }
        idA = randNumA;

    }

/*
    private void gerarId() {
        Random rand = new Random();
        int numeroA;

        numeroA = rand.nextInt(10000);
        idA = numeroA;
    }
*/

}
