package com.sistemadevendas.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.sistemadevendas.database.Conexao.conectar;

public class CriacaoDeTabelas {

    public static void tabelas(){
        saidas();
        produtos();
        admin();
        funcionario();
        dados();
        dadosUsuario();

    }

    public static void saidas() {
        String sql = "create table if not exists saidas(\n" +
                "    id int primary key auto_increment,\n" +
                "    id_produtos int not null ,\n" +
                "    produtos varchar(50) not null ,\n" +
                "    preco double not null ,\n" +
                "    quantidade int not null ,\n" +
                "    forma_pagamento varchar(50) not null ,\n" +
                "    dia int not null ,\n" +
                "    mes int not null ,\n" +
                "    ano int not null\n" +
                ")";
        try (PreparedStatement stmt = conectar().prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao criar Tabela" + e.getMessage());
        }
    }

    public static void produtos() {
        String sql = "create table if not exists produtos(\n" +
                "    id int primary key auto_increment not null ,\n" +
                "    nome varchar(50) not null ,\n" +
                "    preco double not null ,\n" +
                "    quantidade int not null\n" +
                ")\n";

        try (PreparedStatement stmt = conectar().prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao criar Tabela" + e.getMessage());
        }
    }

    public static void funcionario() {
        String sql = "create table if not exists funcionario(\n" +
                "    id int primary key auto_increment not null ,\n" +
                "    nome varchar(50) not null ,\n" +
                "    senha int  not null ,\n" +
                "    idF int not null\n" +
                ")";
        try (PreparedStatement stmt = conectar().prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao criar Tabela" + e.getMessage());
        }

    }

    public static void dados(){
        String sql = "create table if not exists dados(\n" +
                "    id           int primary key not null,\n" +
                "    taxa_credito double          not null,\n" +
                "    taxa_debito  double          not null,\n" +
                "    chava_pix    varchar(50)     not null\n" +
                ")";
        try(PreparedStatement stmt = conectar().prepareStatement(sql)){
            stmt.executeUpdate();
        }catch (SQLException e) {
            System.out.println("Erro ao criar Tabela" + e.getMessage());
        }
    }

    public static void admin(){
        String sql = "create table if not exists admin(\n" +
                "    id    int primary key auto_increment not null,\n" +
                "    nome  varchar(50)                    not null,\n" +
                "    senha int                            not null,\n" +
                "    idA   int                            not null\n" +
                ")";

        try(PreparedStatement stmt = conectar().prepareStatement(sql)){
            stmt.executeUpdate();
        }catch (SQLException e) {
            System.out.println("Erro ao criar Tabela" + e.getMessage());
        }
    }

    public static void dadosUsuario(){
        String sql = "create table  if not exists dados_usuarios(\n" +
                "    id int primary key ,\n" +
                "    quantidade_admin int not null ,\n" +
                "    quantidade_funcionario int not null ,\n" +
                "    nome varchar(50) not null ,\n" +
                "    senha int not null\n" +
                ")";
        try(PreparedStatement stmt = conectar().prepareStatement(sql)){
            stmt.executeUpdate();
        }catch (SQLException e) {
            System.out.println("Erro ao criar Tabela" + e.getMessage());
        }
    }
}

