//package Balanco;
//
//import Login.Admin;
//import Login.Funcionario;
//import java.util.Scanner;
//
//public class Acesso {
//
//    Admin ad = new Admin();
//    Funcionario func = new Funcionario();
//    Scanner ler = new Scanner(System.in);
//    int mes_selecionado, ano_selecionado;
//
//    public void acessar(){
//
//        System.out.print("Seu ID: ");
//        int id = ler.nextInt();
//        this.ad.idA = id;
//        //consulta no BD pra verificar de quem é o id e printar o nome, na parte abaixo
//
//        if (id == ad.idA){ //verificação no BD
//            System.out.printf("Olá, "+ad.getNomeA()+ ". ");
//            visualizacao();
//        } else if (id == func.idF){ //verificação no BD
//            System.out.printf("Olá, "+func.getNomeF()+ ". ");
//            visualizacao();
//        } else {
//            System.out.println("ID não reconhecido.");
//            //voltar p tela inicial
//        }
//    }
//
//    public void formaPagamento(){
//        System.out.println("1 - Dinheiro\n2 - Pix\n3 - Cartão"); //abrir como coluna  a ser selecionada
//        int fpag = ler.nextInt();
//        if (fpag == 1){
//            /*SELECT * FROM saidas
//            WHERE forma_pagamento = 'Dinheiro'
//            ORDER BY ano, mes, dia; */
//        } else if (fpag == 2){
//            /*SELECT * FROM saidas
//            WHERE forma_pagamento = 'Pix'
//            ORDER BY ano, mes, dia; */
//        } else if (fpag == 3){
//            /*SELECT * FROM saidas
//            WHERE forma_pagamento = 'Cartão'
//            ORDER BY ano, mes, dia; */
//        }
//    }
//
//    public void visualizacao(){
//
//        int vis;
//
//        /*comando para realizar a consulta de todas as vendas
//         *  SELECT * saidas
//            ORDER BY ano, mes, dia;
//        */
//
//        System.out.println("Como deseja filtrar o balanço?");
//        System.out.println("1 - Por forma de pagamento\n2 - Por mês\n3 - Por ano\n4 - Por mês e forma de pagamento"); //botões
//        vis = ler.nextInt();
//
//        //1. mes / ano
//        //> pede mes e ano
//        //2. por ano
//        //3. forma de pagamento
//        //> função pronta
//        //4. mes / ano e forma de pagamento
//        //> pede mes e ano e depois forma de pagamento(função)
//
//        switch (vis) {
//            case 1:
//                formaPagamento();
//                break;
//            case 2:
//                while (mes_selecionado > 12 || mes_selecionado < 1) {
//                    System.out.println("Mês desejado"); //campo de escrita
//                    mes_selecionado = ler.nextInt(); //coluna de seleção
//                    System.out.println("Ano desejado: ");
//                    ano_selecionado = ler.nextInt(); //coluna de seleção
//                    if (mes_selecionado > 12 || mes_selecionado < 1){
//                        System.out.println("Mês inválido");
//                    }
//                }
//                //try:
//                /*SELECT * FROM saidas
//                WHERE ano = "?" and mes = "?"
//                ORDER BY dia, forma_pagamento */
//                System.out.println("O valor total vendido no mês foi de: "); //+valorTotal
//                //catch (exception: tal mês não existe no bd){
//                //sout("Mês sem entradas")
//                //}
//                break;
//            case 3:
//                System.out.println("Entre o ano desejado"); //campo de escrita
//                ano_selecionado = ler.nextInt(); //coluna de seleção
//                //try:
//                /*SELECT * FROM saidas
//                WHERE ano = "?"
//                ORDER BY mes, dia, forma_pagamento */
//                System.out.println("O valor total vendido no ano foi de: "); //+valorTotal
//                //catch (exception: tal ano não existe no bd){
//                //sout("Ano sem entradas")
//                //}
//                break;
//            case 4:
//                //acredito n ser possível usar a função de forma de pagamento pq vai dar erro ao pedir mes e ano pra fazer uma consulta e depois pedir
//                //forma de pag pra realizar outra consulta, pq n tem como colocar o mes/ano que a pessoa digitar dentro da função, só pelo comando escrito diretamente
//                while (mes_selecionado > 12 || mes_selecionado < 1 || ano_selecionado < 2017 /*|| ano_selecionado > local.datetime(anotatual) */) {
//                    System.out.print("Mês desejado: "); //campo de escrita
//                    mes_selecionado = ler.nextInt(); //coluna de seleção
//                    System.out.print("Ano desejado: ");
//                    ano_selecionado = ler.nextInt(); //coluna de seleção
//                    if (mes_selecionado > 12 || mes_selecionado < 1 || ano_selecionado < 2017 /*|| ano_selecionado > local.datetime(anotatual) */){
//                        System.out.println("Mês ou ano inválido");
//                    }
//                }
//
//                System.out.println("Agora deseja filtrar por:\n1 - Dinheiro\n2 - Pix\n3 - Cartão"); //abrir como coluna  a ser selecionada
//                int fpag = ler.nextInt();
//                if (fpag == 1){
//                    /*SELECT * FROM saidas
//                    WHERE ano = '?' and mes = '?' and forma_pagamento = 'Dinheiro'
//                    ORDER BY ano, mes, dia; */
//                } else if (fpag == 2){
//                    /*SELECT * FROM saidas
//                    WHERE ano = '?' and mes = '?' and forma_pagamento = 'Pix'
//                    ORDER BY ano, mes, dia; */
//                } else if (fpag == 3){
//                    /*SELECT * FROM saidas
//                    WHERE ano = '?' and mes = '?' and forma_pagamento = 'Cartão'
//                    ORDER BY ano, mes, dia; */
//                }
//                break;
//            default:
//                System.out.println("Entrada inválida.");
//                break;
//        }
//    }
//
//    public void main(String[] args){
//        Acesso ac = new Acesso();
//        ac.acessar();
//    }
//}