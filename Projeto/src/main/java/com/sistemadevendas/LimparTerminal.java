package com.sistemadevendas;

public class LimparTerminal {

    public static void limparTerminal() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                (new ProcessBuilder(new String[]{"cmd", "/c", "cls"})).inheritIO().start().waitFor();
            } else {
                (new ProcessBuilder(new String[]{"bash", "-c", "clear"})).inheritIO().start().waitFor();
            }
        } catch (Exception e) {

            System.out.println("Erro ao limpar o terminal: " + e.getMessage());
        }

    }
}
