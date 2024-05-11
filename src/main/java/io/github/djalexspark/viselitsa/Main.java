package io.github.djalexspark.viselitsa;

import java.util.Scanner;

public class Main {
    private static final String PLAY1 = "играть";
    private static final String PLAY2 = "и";
    private static final String EXIT1 = "выйти";
    private static final String EXIT2 = "в";

    public static void main(String[] args) {
        while (true) {
//            System.out.printf("%s, %s  или %s, %s \n", PLAY1, PLAY2, EXIT1, EXIT2);
            System.out.println("(И)грать или (в)ыйти?");
            String enterCommand = new Scanner(System.in).next().toLowerCase();
            if (enterCommand.equalsIgnoreCase(EXIT1) || enterCommand.equalsIgnoreCase(EXIT2)) {
                System.exit(0);
            } else if (enterCommand.equalsIgnoreCase(PLAY1) || enterCommand.equals(PLAY2)) {
                Game game = new GameWithHints() ;
                System.out.println("Игра начинается");
                game.start();

            }

        }
    }
}