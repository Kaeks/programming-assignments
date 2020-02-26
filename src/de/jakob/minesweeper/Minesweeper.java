package de.jakob.minesweeper;

import java.util.Scanner;

public class Minesweeper {

    private int amtMines;
    private Field field;

    public Field getField() {
        return field;
    }

    public int getAmtMines() {
        return amtMines;
    }

    public Minesweeper(int width, int height, int amtMines) {
        field = new Field(width, height);
        this.amtMines = amtMines;
        field.fill(amtMines);
    }

    public static void main(String[] args) {
        Minesweeper game = new Minesweeper(9, 9, 10);
        Scanner scan = new Scanner(System.in);
        boolean gaming = true;
        while (gaming) {
            System.out.println(game.getField());
            boolean validInput;
            Move move;
            do {
                try {
                    move = game.parseMove(scan.nextLine());
                    game.field.commit(move);
                    validInput = true;
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid move.");
                    validInput = false;
                }
            } while (!validInput);
        }
        scan.close();
    }

    private MoveType parseMoveType(String mtString) {
        if (mtString.equals(MoveType.UNCOVER.getInputString())) return MoveType.UNCOVER;
        if (mtString.equals(MoveType.FLAG.getInputString())) return MoveType.FLAG;
        if (mtString.equals(MoveType.QUESTION.getInputString())) return MoveType.QUESTION;
        throw new IllegalArgumentException(String.format("Invalid move type string (%s).", mtString));
    }

    private Move parseMove(String mString) {
        Position pos = parsePosition(mString.substring(0, 2));
        MoveType moveType = parseMoveType(mString.length() == 3 ? mString.substring(2) : "");
        return new Move(pos, moveType);
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