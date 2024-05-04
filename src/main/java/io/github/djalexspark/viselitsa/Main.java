package io.github.djalexspark.viselitsa;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            System.out.println("(И)грать или (В)ыйти");

            String enterCommand = new Scanner(System.in).next();
            if (enterCommand.equalsIgnoreCase("выйти") || enterCommand.equalsIgnoreCase("в")) {
                System.exit(0);
            } else if (enterCommand.equalsIgnoreCase("играть") || enterCommand.equals("и")) {
                Game game = new Game(true);
                System.out.println("Игра начинается");
                game.startGame();
            }
        }
    }
}