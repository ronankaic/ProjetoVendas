package com.sistemadevendas;

import com.sistemadevendas.database.AdminDB;
import com.sistemadevendas.telas.Menu;
import com.sistemadevendas.telas.TelaLogin;

import java.util.Locale;

import static com.sistemadevendas.database.CriacaoDeTabelas.tabelas;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        TelaLogin login = new TelaLogin();
        Menu menu = new Menu();
        tabelas();
       int i = new AdminDB().quantidadeAdmin();
        if(i < 2){
            menu.menuPrimeiraVez();
        }else {
            login.EscolhaLogin();
        }

    }
}