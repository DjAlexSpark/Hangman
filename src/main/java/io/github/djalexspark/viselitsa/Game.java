package io.github.djalexspark.viselitsa;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    ArrayList dictionary;
    String realWord;
    StringBuilder passWord;
    Scanner scanner;
    String c;

    public Game() {
        this.dictionary = new ArrayList(listOfWords());
        this.realWord = dictionary.get(new Random().nextInt(dictionary.size())).toString().toLowerCase();
        scanner = new Scanner(System.in);
        passWord = new StringBuilder(realWord.length());

        for (int i = 0; i < realWord.length(); i++) {
            passWord.append('*');
        }
    }
    public Game(boolean showAnswer){
        this();
        System.out.println(this.realWord);
    }

    private static void replaceAll(StringBuilder builder, String word, String symbolToReplace) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == symbolToReplace.charAt(0)) {
                builder.setCharAt(i, symbolToReplace.charAt(0));
            }
        }
    }

    public void startGame() {
        while (realWord.compareTo(passWord.toString()) != 0) {
            System.out.println(passWord + "\nprint letter");
            c = String.valueOf(scanner.next().charAt(0));
            replaceAll(passWord, realWord, c);

        }
        System.out.println("you win");
    }

    private List listOfWords() {
        List list = new ArrayList<>();
        try {
            list = Files.readAllLines(Path.of(getClass().getResource("dictionary.txt").toURI()));
        } catch (IOException | URISyntaxException e) {
            list.add("нетсловаря");
            throw new RuntimeException(e);
        }
        return list;
    }

}
