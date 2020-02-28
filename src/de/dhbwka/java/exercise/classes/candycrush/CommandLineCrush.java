package de.dhbwka.java.exercise.classes.candycrush;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandLineCrush {

    private CandyCrush game;

    public CommandLineCrush() {

    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        CommandLineCrush clc = new CommandLineCrush();
        System.out.print("Candy Crush Saga! What's your name?: ");
        String name = scan.nextLine();
        Player player = new Player(name);
        clc.newGame(player);
        boolean gaming = true;
        while (gaming) {
            System.out.println(clc.getGame().getField());

            Move move;

            boolean validInput = false;
            while (!validInput) {
                String mString = scan.nextLine();
                try {
                    move = clc.parseMove(mString);
                    clc.getGame().move(move);
                    validInput = true;
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        }
        scan.close();
    }

    public CandyCrush getGame() {
        return game;
    }

    public void newGame(Player player) {
        game = new CandyCrush(player);
    }

    private Move parseMove(String mString) {
        Pattern pattern = Pattern.compile("([A-Z]+)([1-9]\\d*)([A-Z]+)([1-9]\\d*)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(mString);

        if (!matcher.find()) throw new IllegalArgumentException("Invalid move");

        String xString1 = matcher.group(1);
        String yString1 = matcher.group(2);

        Position pos1 = parsePosition(xString1, yString1);

        String xString2 = matcher.group(3);
        String yString2 = matcher.group(4);

        Position pos2 = parsePosition(xString2, yString2);

        return new Move(pos1, pos2);
    }

    private int parseLetterValue(String lString) {
        int value = 0;
        for (int i = 0; i < lString.length(); i++) {
            value = value * 26 + (Character.toUpperCase(lString.charAt(i)) - ('A' - 1));
        }
        return value - 1;
    }

    private Position parsePosition(String xString, String yString) {
        int x = parseLetterValue(xString);
        int y = Integer.valueOf(yString) - 1;

        return new Position(x, y);
    }
}