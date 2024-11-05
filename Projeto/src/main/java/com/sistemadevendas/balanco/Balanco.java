package com.sistemadevendas.balanco;

import com.sistemadevendas.ConexaoBD;
import com.sistemadevendas.login.Admin;
import com.sistemadevendas.login.Funcionario;
import com.sistemadevendas.login.TelaLogin;
import com.sistemadevendas.login.TelaPadrao;

import java.util.Scanner;

public class Balanco {
    Scanner sc = new Scanner(System.in);
    ConexaoBD bd = new ConexaoBD();

    TelaLogin login = new TelaLogin();
    int mes_selecionado, ano_selecionado;
    String formaPagamento;

    public void acessar() {
        int count = 3;

        System.out.print("Digite o seu id: ");
        int id = sc.nextInt();
        if (id < 10000) {
            boolean retorno = bd.pesquisarIdA(id);
            String nome = bd.pesquisarNomeA(id);
            if (retorno) {
                System.out.println("Bem vindo " + nome + "!");
                visualizar();
            }
        } else {
            boolean retorno = bd.pesquisarIdF(id);
            String nome = bd.pesquisarNomeF(id);
            if (retorno) {
                System.out.println("Bem vindo " + nome + "!");
                visualizar();
            }
        }
    }

    public void visualizar() {
        bd.listarSaidas();
        double total;

        System.out.println("Como deseja filtrar o balanço?");
        System.out.println("Escolha uma opção abaixo");
        System.out.println("1. Por forma de pagamento");
        System.out.println("2. Por mês/ano");
        System.out.println("3. Por ano");
        System.out.println("4. Por mês e forma de pagamento");
        System.out.println("5. Voltar ao menu");
        int vis = sc.nextInt();

        //1. mes / ano
        //> pede mes e ano
        //2. por ano
        //3. forma de pagamento
        //> função pronta
        //4. mes / ano e forma de pagamento
        //> pede mes e ano e depois forma de pagamento(função)

        switch (vis) {
            case 1:
                formaPagamento = escolherFormaDePagamento();
                bd.listarFormaDePagamento(formaPagamento);

                break;
            case 2:
                while (mes_selecionado > 12 || mes_selecionado < 1) {
                    System.out.println("Mês desejado"); //campo de escrita
                    mes_selecionado = sc.nextInt(); //coluna de seleção
                    System.out.println("Ano desejado: ");
                    ano_selecionado = sc.nextInt(); //coluna de seleção
                    if (mes_selecionado > 12 || mes_selecionado < 1) {
                        System.out.println("Mês inválido");
                    }
                }
                total = bd.listarMesAno(mes_selecionado, ano_selecionado);
                System.out.printf("O valor total vendido no mês foi de: R$%.2f%n", total);

                break;
            case 3:
                System.out.println("Entre o ano desejado"); //campo de escrita
                ano_selecionado = sc.nextInt(); //coluna de seleção
                total = bd.listarAno(ano_selecionado);
                System.out.printf("O valor total vendido no ano foi de: R$%.2f%n", total);
                break;
            case 4:
                while (mes_selecionado > 12 || mes_selecionado < 1 || ano_selecionado < 2017 /*|| ano_selecionado > local.datetime(anotatual) */) {
                    System.out.print("Mês desejado: "); //campo de escrita
                    mes_selecionado = sc.nextInt(); //coluna de seleção
                    System.out.print("Ano desejado: ");
                    ano_selecionado = sc.nextInt(); //coluna de seleção
                    if (mes_selecionado > 12 || mes_selecionado < 1 || ano_selecionado < 2017 /*|| ano_selecionado > local.datetime(anotatual) */) {
                        System.out.println("Mês ou ano inválido");
                    }
                }
                formaPagamento = escolherFormaDePagamento();
                total = bd.listarMesAnoFormaPagamento(mes_selecionado, ano_selecionado, formaPagamento);
                System.out.printf("O valor total vendido no período foi de: R$%.2f%n", total);
                break;
            case 5:
                return;
            default:
                System.out.println("Entrada inválida.");
                break;
        }

    }

    private String escolherFormaDePagamento() {

        System.out.println("1. Dinheiro");
        System.out.println("2. Cartão de credito");
        System.out.println("3. Cartão de debito");
        System.out.println("4. Pix");
        int i = sc.nextInt();

        if (i == 1) {
            formaPagamento = "Dinheiro";
        } else if (i == 2) {
            formaPagamento = "Cartão de crédito";
        } else if (i == 3) {
            formaPagamento = "Cartão de débito";
        } else if (i == 4) {
            formaPagamento = "Pix";
        } else {
            System.out.println("Opção invalida!");
            escolherFormaDePagamento();
        }

        return formaPagamento;
    }


}
