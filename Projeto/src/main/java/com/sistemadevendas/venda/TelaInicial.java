package com.sistemadevendas.venda;


import com.sistemadevendas.database.VendaDB;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.sistemadevendas.database.Conexao.conectar;
import static com.sistemadevendas.utilitarios.Entrada.lerInt;
import static com.sistemadevendas.utilitarios.Entrada.lerString;
import static com.sistemadevendas.utilitarios.LimparTerminal.limparTerminal;

public class TelaInicial {


    Carrinho carrinho = new Carrinho();
    VendaDB vendaDB = new VendaDB();

    public void mostrarMenu() {
        limparTerminal();
        System.out.println("==Sistema de Vendas==");
        System.out.println("Escolha uma opção:");
        System.out.println("1. Listar produtos");
        System.out.println("2. Cadastrar produtos");
        System.out.println("3. Excluir produtos");
        System.out.println("4. Adicionar produtos ao carrinho");
        System.out.println("5. Finalizar compra");
        System.out.println("6. Voltar ao menu");


        int option = lerInt();

        switch (option) {
            case 1:
                limparTerminal();
                vendaDB.listarProdutos();
                lerString();
                limparTerminal();
                mostrarMenu();
                break;
            case 2:
                limparTerminal();
                vendaDB.cadastrarProdutos();
                limparTerminal();
                mostrarMenu();
                break;
            case 3:
                limparTerminal();
                vendaDB.excluirProdutos();
                limparTerminal();
                mostrarMenu();
                break;
            case 4:
                limparTerminal();
                adicionarCarrinho();
                limparTerminal();
                mostrarMenu();
                break;
            case 5:
                limparTerminal();
                finalizarCompra();
                lerString();
                mostrarMenu();
                break;
            case 6:
                return;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private void finalizarCompra() {
        vendaDB.PesquisarDados();
        double taxaCredito = vendaDB.taxaCredito;
        double taxaDebito = vendaDB.taxaDebito;
        String chavePix = vendaDB.chavePix;

        if (carrinho.calcularTotal() <= 0) {
            System.out.println("Carrinho vazio. Não é possível finalizar a compra.");
            lerString();
            return;
        }

        double total = carrinho.calcularTotal();
        String formaPagamento;
        System.out.printf("Valor total da compra: R$%.2f%n", total);
        System.out.println("Escolha a forma de pagamento: ");
        System.out.println("1. Dinheiro");
        System.out.println("2. Pix");
        System.out.println("3. Cartão");
        int metodoPagamento = lerInt();

        switch (metodoPagamento) {
            case 1:
                formaPagamento = "Dinheiro";
                vendaDB.registrarSaidas(carrinho.getProdutos(), formaPagamento);
                System.out.printf("Valor da compra R$ %.2f%n", total);
                System.out.println("Obrigado, volte sempre!");
                break;
            case 2:
                formaPagamento = "Pix";
                vendaDB.registrarSaidas(carrinho.getProdutos(), formaPagamento);
                System.out.printf("Valor da compra R$ %.2f%n", total);
                System.out.println("Chave Pix: " + chavePix);
                System.out.println("Obrigado, volte sempre!");
                break;
            case 3:
                limparTerminal();
                System.out.println("1. Cartão de débito");
                System.out.println("2. Cartão de crédito");
                int cartao = lerInt();
                if (cartao == 1) {
                    formaPagamento = "Cartão de débito";
                    total /= taxaDebito;
                    vendaDB.registrarSaidas(carrinho.getProdutos(), formaPagamento);
                    System.out.printf("Taxa de %.2f%% adicionada no valor total\n", taxaDebito);
                } else if (cartao == 2) {
                    formaPagamento = "Cartão de crédito";
                    total /= taxaCredito;
                    vendaDB.registrarSaidas(carrinho.getProdutos(), formaPagamento);
                    System.out.printf("Taxa de %.2f%% adicionada no valor total\n", taxaCredito);
                } else {
                    System.out.println("Escolha inválida.");
                    finalizarCompra();
                }
                System.out.printf("Valor da compra R$ %.2f%n", total);
                System.out.println("Obrigado, volte sempre!");


                break;
            default:
                System.out.println("Escolha inválida.");
                finalizarCompra();
        }

        carrinho.limparCarrinho();

    }


    public void adicionarCarrinho() {
        vendaDB.listarProdutos();
        boolean continuar = true;

        System.out.println("==Adicionar produtos ao carrinho==");

        while (continuar) {
            System.out.print("Digite o ID do produto que deseja adicionar ao carrinho (ou 0 para finalizar): ");
            int idProduto = lerInt();

            if (idProduto == 0) {
                continuar = false;
            } else {
                String sql = "SELECT * FROM produtos WHERE id = " + idProduto + ";";

                try (Statement stmt = conectar().createStatement();
                     ResultSet rs = stmt.executeQuery(sql)) {

                    if (rs.next()) {
                        String nome = rs.getString("nome");
                        double preco = rs.getDouble("preco");
                        int estoque = rs.getInt("quantidade");
                        System.out.print("Digite a quantidade desejada: ");
                        int quantidade = lerInt();

                        if (quantidade > 0 && quantidade <= estoque) {
                            Produto produto = new Produto(idProduto, nome, preco, quantidade);
                            carrinho.adicionarProduto(produto);
                            System.out.printf("Produto adicionado: %s - R$ %.2f - Quantidade: %d%n", nome, preco, quantidade);
                            vendaDB.atualizarEstoque(idProduto, estoque - quantidade);
                        } else {
                            System.out.println("Quantidade inválida ou não disponível em estoque.");
                        }
                    } else {
                        System.out.println("Produto não encontrado.");
                    }

                } catch (SQLException e) {
                    System.err.println("Erro ao adicionar produto ao carrinho: " + e.getMessage());
                }
            }
        }
    }
}

