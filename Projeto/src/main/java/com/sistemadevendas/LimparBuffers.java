package com.sistemadevendas;

import java.util.Scanner;

public class LimparBuffers {

    public void limparBuffer(Scanner sc) {
        if (sc.hasNextLine()) {
            sc.nextLine(); // Limpa qualquer entrada remanescente no buffer
        }
    }

}