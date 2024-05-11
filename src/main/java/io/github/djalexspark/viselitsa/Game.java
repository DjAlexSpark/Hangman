package io.github.djalexspark.viselitsa;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Game implements PartsOfHangman{
    ArrayList<String> dictionary;
    String realWord;
    StringBuilder passWord;
    Scanner scanner;
    boolean showHints;
    int count;
    boolean isNotLose;

    public Game() {
        this.isNotLose = true;
        this.showHints = false;
        this.dictionary = new ArrayList<String>(listOfWordsFromFile());
        Random random = new Random();
        int indexOfWord = random.nextInt(dictionary.size());
        this.realWord = dictionary.get(indexOfWord).toLowerCase();
        this.scanner = new Scanner(System.in);
        this.passWord = new StringBuilder(realWord.length());
        this.count = 0;
        for (int i = 0; i < realWord.length(); i++) {
            passWord.append('*');
        }
    }


    private static void replaceAll(StringBuilder builder, String word, String symbolToReplace) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == symbolToReplace.charAt(0)) {
                builder.setCharAt(i, symbolToReplace.charAt(0));
            }
        }
    }

    public void setShowHints(boolean showHints) {
        this.showHints = showHints;
    }

    public void start() {
        if(showHints) System.out.println(this.realWord);
        count=0;
        while (isNotWin()&& isNotLose&&count<=6) {
            showHangman(count);
            System.out.println(passWord + "\nвведите букву");
            String letterToReplace = String.valueOf(scanner.next().charAt(0));
            replaceAll(passWord, realWord, letterToReplace);
            if (realWord.indexOf(letterToReplace)<0){count++;}
        }

        if (!isNotWin()){System.out.println(realWord+"\nвы победили");}
        else {
            System.out.println("Вы проиграли, " +
                    "\nзагаданное слово - "+realWord);
        }

    }

    private void showHangman(int numberOfPart) {
            switch(numberOfPart){
                case(NOTHING):
                    System.out.println(
                            " __\n" +
                            "|  \n" +
                            "| \n" +
                            "|\n" +
                            "|");
                    break;
                case(HEAD):
                    System.out.println(
                            " __\n" +
                            "|  о\n" +
                            "|  \n" +
                            "|\n" +
                            "|");
                    break;
                case(LEFT_HAND):
                    System.out.println(
                            " __\n" +
                                    "|  о\n" +
                                    "| / \n" +
                                    "|\n" +
                                    "|");
                    break;
                case(RIGHT_HAND):
                    System.out.println(
                                    " __\n" +
                                    "|  о\n" +
                                    "| / \\\n" +
                                    "|\n" +
                                    "|");
                    break;
                case(BODY):
                    System.out.println(
                            " __\n" +
                                    "|  о\n" +
                                    "| /|\\\n" +
                                    "|\n" +
                                    "|");
                    break;
                case(LEFT_LEG):
                    System.out.println(
                            " __\n" +
                                    "|  о\n" +
                                    "| /|\\\n" +
                                    "| /\n" +
                                    "|");
                    break;
                case(RIGHT_LEG):
                    System.out.println(
                                    " __\n" +
                                    "|  о\n" +
                                    "| /|\\\n" +
                                    "| / \\\n" +
                                    "|");
                    break;
                default:
                    break;
            }
    }

    private boolean isNotWin() {
        return this.realWord.compareTo(passWord.toString()) != 0;
    }

    private List<String> listOfWordsFromFile() {
        List list = new ArrayList<>();
        try {
            list = Files.readAllLines(Path.of(getClass().getResource("dictionary.txt").toURI()));
        } catch (IOException | URISyntaxException e) {
            list.add("нет_словаря");
            throw new RuntimeException(e);
        }
        return list;
    }
}
