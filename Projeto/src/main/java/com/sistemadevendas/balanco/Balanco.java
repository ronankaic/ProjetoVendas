package com.sistemadevendas.balanco;


import com.sistemadevendas.database.AdminDB;
import com.sistemadevendas.database.BalancoDB;
import com.sistemadevendas.database.FuncionarioDB;


import static com.sistemadevendas.utilitarios.Entrada.lerInt;
import static com.sistemadevendas.utilitarios.Entrada.lerString;
import static com.sistemadevendas.utilitarios.LimparTerminal.limparTerminal;

public class Balanco {

    AdminDB AdminBD = new AdminDB();
    FuncionarioDB FuncBD = new FuncionarioDB();
    BalancoDB BalancoBD = new BalancoDB();
    int mes_selecionado, ano_selecionado;
    String formaPagamento;

    public void acessar() {
        System.out.print("Digite o seu id: ");
        int id = lerInt();
        if (id < 10000) {
            boolean retorno = AdminBD.pesquisarIdA(id);
            String nome = AdminBD.pesquisarNomeA(id);
            if (retorno) {
                System.out.println("Bem vindo " + nome + "!");
                visualizar();
            } else {
                System.out.println("ID não localizado!");
                lerString();
            }
        } else {
            boolean retorno = FuncBD.pesquisarIdF(id);
            String nome = FuncBD.pesquisarNomeF(id);
            if (retorno) {
                System.out.println("Bem vindo " + nome + "!");
                visualizar();
            } else {
                System.out.println("ID não localizado!");
                lerString();
            }
        }
    }

    public void visualizar() {
        lerString();
        limparTerminal();
        BalancoBD.listarSaidas();
        double total;

        System.out.println("\nComo deseja filtrar o balanço?");
        System.out.println("1. Por forma de pagamento");
        System.out.println("2. Por mês/ano");
        System.out.println("3. Por ano");
        System.out.println("4. Por mês/ano e forma de pagamento");
        System.out.println("5. Voltar ao menu");
        int vis = lerInt();

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
                BalancoDB.listarFormaDePagamento(formaPagamento);
                visualizar();

                break;
            case 2:
                mes_selecionado = 0;
                while (mes_selecionado > 12 || mes_selecionado < 1) {
                    System.out.print("Mês desejado: "); //campo de escrita
                    mes_selecionado = lerInt();//coluna de seleção
                    System.out.print("Ano desejado: ");
                    ano_selecionado = lerInt(); //coluna de seleção
                    if (mes_selecionado > 12 || mes_selecionado < 1) {
                        System.out.println("Mês inválido");
                    }
                }
                total = BalancoBD.listarMesAno(mes_selecionado, ano_selecionado);
                System.out.printf("O valor total vendido no mês foi de: R$%.2f%n", total);
                visualizar();

                break;
            case 3:
                System.out.println("Entre o ano desejado"); //campo de escrita
                ano_selecionado = lerInt(); //coluna de seleção
                total = BalancoDB.listarAno(ano_selecionado);
                System.out.printf("O valor total vendido no ano foi de: R$%.2f%n", total);

                visualizar();
                break;
            case 4:
                mes_selecionado = 0;
                while (mes_selecionado > 12 || mes_selecionado < 1 || ano_selecionado < 2017 /*|| ano_selecionado > local.datetime(anotatual) */) {
                    System.out.print("Mês desejado: "); //campo de escrita
                    mes_selecionado = lerInt(); //coluna de seleção
                    System.out.print("Ano desejado: ");
                    ano_selecionado = lerInt(); //coluna de seleção
                    if (mes_selecionado > 12 || mes_selecionado < 1 || ano_selecionado < 2017 /*|| ano_selecionado > local.datetime(anotatual) */) {
                        System.out.println("Mês ou ano inválido");
                    }
                }
                formaPagamento = escolherFormaDePagamento();
                total = BalancoDB.listarMesAnoFormaPagamento(mes_selecionado, ano_selecionado, formaPagamento);
                System.out.printf("O valor total vendido no período foi de: R$%.2f%n", total);

                visualizar();
                break;
            case 5:
                return;
            default:
                System.out.println("Entrada inválida.");
                break;
        }

    }

    private String escolherFormaDePagamento() {
        limparTerminal();
        System.out.println("Escolha a forma de pagamento");
        System.out.println("1. Dinheiro");
        System.out.println("2. Cartão de credito");
        System.out.println("3. Cartão de debito");
        System.out.println("4. Pix");
        int i = lerInt();

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


