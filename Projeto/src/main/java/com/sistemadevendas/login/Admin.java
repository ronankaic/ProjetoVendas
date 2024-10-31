package com.sistemadevendas.login;

public class Admin {

    private String nome;
    private int senha;
    private int idA;

    public Admin(){}

    public Admin(String nome, int senha, int idA) {
        this.nome = nome;
        this.senha = senha;
        this.idA = idA;
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

    public int getIdA() {
        return idA;
    }

    public void setId(int id) {
        this.idA = id;
    }
}
