package com.sistemadevendas.login;

public class Funcionario {

    private String nome;
    private int senha;
    private int idF;

    public Funcionario() {}

    public Funcionario(String nome, int senha, int idF) {
        this.nome = nome;
        this.senha = senha;
        this.idF = idF;
    }

    public int getIdF() {
        return idF;
    }

    public void setIdF(int idF) {
        this.idF = idF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }
}
