package com.sistemadevendas;

import com.sistemadevendas.database.AdminDB;
import com.sistemadevendas.telas.TelaLogin;

import java.util.Locale;

import static com.sistemadevendas.database.CriacaoDeTabelas.tabelas;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        TelaLogin login = new TelaLogin();
        tabelas();
       int i = new AdminDB().quantidadeAdmin();
        if(i < 1){
            login.primeiraExecucao();
        }else {
            login.EscolhaLogin();
        }

    }
}