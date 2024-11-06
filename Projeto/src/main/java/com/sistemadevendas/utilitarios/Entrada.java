package com.sistemadevendas.utilitarios;

import java.util.Scanner;

public class Entrada {

    private static final Scanner sc = new Scanner(System.in);

    public static int lerInt() {
        int valor = 0;
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                valor = sc.nextInt();
                entradaValida = true;
            } catch (Exception e) {
                System.out.println("Entrada inválida! Por favor, digite um número inteiro.");
            } finally {
                sc.nextLine();
            }
        }
        return valor;
    }

    public static double lerDouble() {
        double valor = 0;
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                valor = sc.nextDouble();
                entradaValida = true;
            } catch (Exception e) {
                System.out.println("Entrada inválida! Por favor, digite um número real.");
            } finally {
                sc.nextLine();
            }
        }
        return valor;
    }

    public static String lerString() {
        String valor = "";
        valor = sc.nextLine();
        return valor;

    }
}
