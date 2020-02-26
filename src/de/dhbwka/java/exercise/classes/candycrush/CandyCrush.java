package de.dhbwka.java.exercise.classes.candycrush;

import java.util.Scanner;

public class CandyCrush {
    
    private Field field;
    private int msTime;
    private Player player;
    
    private final int MS_TIME_INCREMENT = 45000;

    public CandyCrush() {
        field = new Field(9, 3);
        player = new Player();
    }

    Player getPlayer() {
        return player;
    }

    Field getField() {
        return field;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        CandyCrush game = new CandyCrush();
        boolean gaming = true;
        while (gaming) {
            boolean typing = true;
            while (typing) {
                System.out.println(game.getPlayer());
                System.out.println(game.getField());
                String in = scan.nextLine();
                if (in == "bruh") {
                    typing = false;
                    break;
                }
                Position parsedFrom;
                Position parsedTo;
                try {
                    parsedFrom = game.parsePosition(in.substring(0, 2));
                    parsedTo = game.parsePosition(in.substring(2, 4));
                } catch (Exception e) {
                    System.out.println("Invalid input.");
                    continue;
                }
                System.out.println(parsedFrom);
                System.out.println(parsedTo);
                if (game.field.isValidMove(parsedFrom, parsedTo)) {
                    game.player.addPoints(game.field.commitMove(parsedFrom, parsedTo));
                } else {
                    System.out.println("Invalid move.");
                }
            }
        }
        scan.close();
    }

    private Position parsePosition(String pString) {
        if (pString.length() != 2) throw new IllegalArgumentException(String.format("Position string must have length 2 (%s).", pString));
        char letter = pString.charAt(0);
        char number = pString.charAt(1);

        int x, y = -1;

        if (Character.isLetter(letter)) x = Character.toUpperCase(letter) - 'A';
        else throw new IllegalArgumentException(String.format("First character must be letter (%s).", letter));

        if (Character.isDigit(number)) y = Character.getNumericValue(number) - 1;
        else throw new IllegalArgumentException(String.format("Second character must be number (%s).", number));

        return new Position(x, y);
    }

}