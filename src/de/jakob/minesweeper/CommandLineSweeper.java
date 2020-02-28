package de.jakob.minesweeper;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandLineSweeper {

    private Minesweeper game;

    public CommandLineSweeper() {
    }

    public Minesweeper getGame() {
        return game;
    }

    public static void main(String[] args) {
        CommandLineSweeper cls = new CommandLineSweeper();
        Scanner scan = new Scanner(System.in);
        boolean doing = true;
        while (doing) {
            boolean gaming = true;
            cls.newGame();
            while (gaming) {
                System.out.println(cls.getGame().getField());
                boolean validInput;
                Move move;
                do {
                    try {
                        move = cls.parseMove(scan.nextLine());
                        cls.game.move(move);
                        validInput = true;
                        switch (cls.game.getStatus()) {
                            case BEFORE: break;
                            case PLAYING: break;
                            case WON:
                            cls.displayWin();
                            gaming = false;
                            break;
                            case LOST:
                            cls.displayLoss();
                            gaming = false;
                            break;
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid move.");
                        e.printStackTrace();
                        validInput = false;
                    }
                } while (!validInput);
            }
            boolean validAnswer = false;
            do {
                System.out.printf("Play again? (Y/N): ");
                String answer = scan.nextLine();
                switch (Character.toUpperCase(answer.charAt(0))) {
                    case 'Y':
                    validAnswer = true;
                    break;
                    case 'N':
                    doing = false;
                    validAnswer = true;
                    break;
                    default:
                    System.out.println("xD");
                    break;
                }
            } while (!validAnswer);
        }
        scan.close();
    }

    private void newGame() {
        game = new Minesweeper(12, 20, 50);
    }

    private void displayWin() {
        System.out.println(game.getField());
        System.out.println("You is a winner!");
    }

    private void displayLoss() {
        System.out.println(game.getField());
        System.out.println("oof size: large");
    }

    private MoveType parseMoveType(String mtString) {
        if (mtString == null || mtString.equals(MoveType.UNCOVER.getInputString())) return MoveType.UNCOVER;
        if (mtString.equals(MoveType.FLAG.getInputString())) return MoveType.FLAG;
        if (mtString.equals(MoveType.QUESTION.getInputString())) return MoveType.QUESTION;
        throw new IllegalArgumentException(String.format("Invalid move type string (%s).", mtString));
    }

    private Move parseMove(String mString) {
        Pattern pattern = Pattern.compile("([A-Z]+)([1-9]\\d*)([!?])?", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(mString);

        if (!matcher.find()) throw new IllegalArgumentException("Invalid move");

        String xString = matcher.group(1);
        String yString = matcher.group(2);

        Position pos = parsePosition(xString, yString);

        String mtString = matcher.group(3);

        MoveType moveType = parseMoveType(mtString);
        return new Move(pos, moveType);
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
