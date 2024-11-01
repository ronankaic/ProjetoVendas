package com.sistemadevendas.venda;

import com.sistemadevendas.ConexaoBD;
import com.sistemadevendas.LimparTerminal;

import java.sql.*;
import java.util.Scanner;

public class TelaInicial {
    Scanner sc = new Scanner(System.in);
    Connection conexao = ConexaoBD.conectar();
    Carrinho carrinho = new Carrinho();
    LimparTerminal LT = new LimparTerminal();

    public void mostrarMenu() {

        while (true) {

            LT.limparTerminal();

            System.out.println("==Sistema de Vendas==");
            System.out.println("Escolha uma opção:");
            System.out.println("1. Listar produtos");
            System.out.println("2. Cadastrar produtos");
            System.out.println("3. Excluir produtos");
            System.out.println("4. Adicionar produtos ao carrinho");
            System.out.println("5. Finalizar compra");
            System.out.println("6. Sair");

            int option = sc.nextInt();

            switch (option) {
                case 1:
                    listarProdutos(conexao);
                    break;
                case 2:
                    cadastrarProdutos(conexao);
                    break;
                case 3:
                    excluirProdutos(conexao);
                    break;
                case 4:
                    adicionarCarrinho(conexao);
                    break;
                case 5:
                    finalizarCompra(conexao);
                    break;
                case 6:
                    System.out.println("Finalizando programa.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    public void listarProdutos(Connection conexao) {
        LT.limparTerminal();
        String sql = "SELECT * FROM produtos";
        try (Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Lista de produtos:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                int quantidade = rs.getInt("quantidade");
                System.out.printf("ID: %d, Nome: %s, Preco: %.2f, Quantidade: %d%n", id, nome, preco, quantidade);
            }

        } catch (SQLException var13) {
            SQLException e = var13;
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }
        sc.nextLine();
        sc.nextLine();
    }

    public void cadastrarProdutos(Connection conexao) {
        LT.limparTerminal();
        listarProdutos(conexao);

        String sql = "SELECT MAX(id) AS ultimo_id FROM produtos";
        int ultimoId = 0;

        try (Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                ultimoId = rs.getInt("ultimo_id");
                if (ultimoId == 0) {
                    ultimoId = 1;
                } else {
                    ultimoId += 1;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao localizar o ultimo ID: " + e.getMessage());
            throw new RuntimeException(e);
        }
        System.out.println("==Cadastro de Produtos==");
        System.out.print("Digite o nome do produto: ");
        String nome = sc.nextLine();
        System.out.print("Digite o preço do produto: ");
        double preco = sc.nextDouble();
        System.out.print("Digite a quantidade em estoque do produto: ");
        int quantidade = sc.nextInt();

        Produto produto = new Produto(ultimoId, nome, preco, quantidade);

        String inserirSQL = "INSERT INTO produtos (id, nome, preco, quantidade) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = conexao.prepareStatement(inserirSQL)) {
            pstmt.setInt(1, produto.getId());
            pstmt.setString(2, produto.getNome());
            pstmt.setDouble(3, produto.getPreco());
            pstmt.setInt(4, produto.getQuantidade());
            pstmt.executeUpdate();
            System.out.println("Produto cadastrado com sucesso! Último ID: " + ultimoId);
            sc.nextLine();
            sc.nextLine();
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void excluirProdutos(Connection conexao) {
        LT.limparTerminal();
        listarProdutos(conexao);

        System.out.println("==Exluir produtos==");
        System.out.print("Digite o ID do produto que deseja exluir: ");
        int id = sc.nextInt();
        String sql = "DELETE FROM produtos WHERE id = ?";

        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int linhas = pstmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("Produto excluido com sucesso!");
                sc.nextLine();
                sc.nextLine();
            } else {
                System.out.println("Produto não encontrado!");
                sc.nextLine();
                sc.nextLine();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao excluir produto: " + e.getMessage());
        }

    }

    private void adicionarCarrinho(Connection conexao) {
        LT.limparTerminal();
        listarProdutos(conexao);
        boolean continuar = true;

        System.out.println("==Adicionar produtos ao carrinho==");

        while (continuar) {
            System.out.print("Digite o ID do produto que deseja adicionar ao carrinho (ou 0 para finalizar): ");
            int idProduto = sc.nextInt();

            if (idProduto == 0) {
                continuar = false;
            } else {
                String sql = "SELECT * FROM produtos WHERE id = " + idProduto + ";";

                try {
                    Statement stmt = conexao.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);

                    if (rs.next()) {
                        String nome = rs.getString("nome");
                        double preco = rs.getDouble("preco");
                        int estoque = rs.getInt("quantidade");
                        System.out.print("Digite a quantidade desejada: ");
                        int quantidade = sc.nextInt();

                        if (quantidade > 0 && quantidade <= estoque) {
                            Produto produto = new Produto(idProduto, nome, preco, quantidade);
                            carrinho.adicionarProduto(produto);
                            System.out.printf("Produto adicionado: %s - R$ %.2f - Quantidade: %d%n", nome, preco, quantidade);
                            atualizarEstoque(conexao, idProduto, estoque - quantidade);
                        } else {
                            System.out.println("Quantidade inválida ou não disponível em estoque.");
                        }
                    } else {
                        System.out.println("Produto não encontrado.");
                    }

                    rs.close();
                    stmt.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao adicionar produto ao carrinho: " + e.getMessage());
                }
            }
        }
    }

    private void atualizarEstoque(Connection conexao, int idProduto, int estoque) {
        String sql = "UPDATE produtos SET quantidade = ? WHERE id = ?;";

        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, estoque);
            pstmt.setInt(2, idProduto);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar estoque do produto: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void finalizarCompra(Connection conexao) {
        RegistroSaida registroSaida = new RegistroSaida();
        if (carrinho.calcularTotal() <= 0) {
            System.out.println("Carrinho vazio. Não é possível finalizar a compra.");
            sc.nextLine();
            sc.nextLine();
            return;
        }

        double total = carrinho.calcularTotal();
        String formaPagamento;
        LT.limparTerminal();
        System.out.printf("Valor total da compra: R$%.2f%n", total);
        System.out.println("Escolha a forma de pagamento: ");
        System.out.println("1. Dinheiro");
        System.out.println("2. Pix");
        System.out.println("3. Cartão");
        int metodoPagamento = sc.nextInt();

        switch (metodoPagamento) {
            case 1:
                formaPagamento = "Dinheiro";
                registroSaida.registrarSaidas(conexao, carrinho.getProdutos(), formaPagamento);
                System.out.printf("Valor da compra R$ %.2f%n", total);
                System.out.println("Obrigado, volte sempre!");
                sc.nextLine();
                sc.nextLine();
                break;
            case 2:
                formaPagamento = "Pix";
                registroSaida.registrarSaidas(conexao, carrinho.getProdutos(), formaPagamento);
                System.out.printf("Valor da compra R$ %.2f%n", total);
                System.out.println("E-mail para pagamento via Pix: sandrasxr123@gmail.com");
                System.out.println("Obrigado, volte sempre!");
                sc.nextLine();
                sc.nextLine();
                break;
            case 3:
                System.out.println("1. Cartão de débito");
                System.out.println("2. Cartão de crédito");
                int cartao = sc.nextInt();
                if (cartao == 1) {
                    formaPagamento = "Cartão de débito";
                    registroSaida.registrarSaidas(conexao, carrinho.getProdutos(), formaPagamento);
                } else if (cartao == 2) {
                    formaPagamento = "Cartão de crédito";
                    total *= 1.05;
                    registroSaida.registrarSaidas(conexao, carrinho.getProdutos(), formaPagamento);
                    System.out.println("Taxa de 5% adicionada no valor total");
                } else {
                    System.out.println("Escolha inválida.");
                    finalizarCompra(conexao);
                }
                System.out.printf("Valor da compra R$ %.2f%n", total);
                System.out.println("Obrigado, volte sempre!");
                recomecarMenu();
                break;
            default:
                System.out.println("Escolha inválida.");
                finalizarCompra(conexao);
        }

        carrinho.limparCarrinho();
    }

    private void recomecarMenu() {
        System.out.println("Deseja voltar a tela inicial?");
        System.out.println("1. Recomeçar programa");
        System.out.println("2. Finalizar programa");

        int opcao = sc.nextInt();

        switch (opcao) {
            case 1:
                sc.nextLine();
                break;
            case 2:
                System.out.println("Finalizando programa");
                System.exit(0);
                break;
            default:
                System.out.println("Opcao invalida. Por favor, digite 1 ou 2.");

        }
    }

}
