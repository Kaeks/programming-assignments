package de.jakob.minesweeper;

import java.util.ArrayList;

import de.jakob.util.AnsiCode;

public class Field {

    private int width;
    private int height;
    private ArrayList<ArrayList<Cell>> cells;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        cells = new ArrayList<ArrayList<Cell>>();
        for (int y = 0; y < height; y++) {
            ArrayList<Cell> row = new ArrayList<Cell>();
            for (int x = 0; x < width; x++) {
                row.add(new Cell(new Position(x, y)));
            }
            cells.add(row);
        }
    }

    private Cell getCell(int x, int y) {
        ArrayList<Cell> row = cells.get(y);
        return row.get(x);
    }

    private int getNeighboringMines(Cell cell) {
        int amt = 0;
        Position pos = cell.getPosition();
        for (int i = -1; i <= 1; i++) {
            int lookingX = pos.getX() + i;
            if (lookingX < 0 || lookingX >= width) continue;
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                int lookingY = pos.getY() + j;
                if (lookingY < 0 || lookingY >= height) continue;
                if (getCell(lookingX, lookingY).isMine()) {
                    amt++;
                }
            }
        }
        return amt;
    }

    private char getValue(Cell cell) {
        if (cell.isCovered()) {
            if (cell.isFlag()) return 'P';
            if (cell.isQuestion()) return '?';
            return '#';
        }
        if (cell.isMine()) return '*';
        int neighboring = getNeighboringMines(cell);
        if (neighboring == 0) return ' ';
        return (char) ('0' + neighboring);
    }

    private char getValue(int x, int y) {
        return getValue(getCell(x, y));
    }

    private String getFormattedString(char value) {
        if (value == 'P') return AnsiCode.BLUE_BACKGROUND.getValue() + AnsiCode.RED.getValue() + value + " " + AnsiCode.RESET.getValue();
        if (value == '*') return AnsiCode.YELLOW_BACKGROUND.getValue() + AnsiCode.RED.getValue() + value + " " + AnsiCode.RESET.getValue();
        return value + " ";
    }

    private String getFormattedString(int x, int y) {
        return getFormattedString(getValue(x, y));
    }

    public void fill(int amount) {
        for (int i = 0; i < amount; i++) {
            int randX;
            int randY;
            do {
                randX = (int) (Math.random() * width);
                randY = (int) (Math.random() * height);
            } while (!getCell(randX, randY).setMine());
        }
    }

    private void recursiveUncover(Cell cell) {
        System.out.println(this);
        if (!cell.isCovered()) {
            System.out.println("Cell is already uncovered.");
            return;
        }
        Position pos = cell.getPosition();
        cell.uncover();
        if (getNeighboringMines(cell) == 0) {
            for (int i = -1; i <= 1; i++) {
                if (i == 0) {
                    System.out.println("i is 0.");
                    continue;
                }
                if (pos.getX() + i < 0 || pos.getX() + i >= width) {
                    System.out.printf("X is out of bounds (%d).%n", pos.getX() + i);
                    continue;
                }
                if (pos.getY() + i < 0 || pos.getY() + i >= height) {
                    System.out.printf("Y is out of bounds (%d).%n", pos.getX() + i);
                    continue;
                }

                Cell xCell = getCell(pos.getX() + i, pos.getY());
                Cell yCell = getCell(pos.getX(), pos.getY() + i);

                recursiveUncover(xCell);
                recursiveUncover(yCell);
            }
        }
    }

    private boolean commitUncover(Cell cell) {
        if (!cell.isCovered()) return false;
        if (cell.isFlag()) return false;
        recursiveUncover(cell);
        return true;
    }

    private boolean commitFlag(Cell cell) {
        if (!cell.isCovered()) return false;
        cell.toggleFlag();
        return true;
    }

    private boolean commitQuestion(Cell cell) {
        return false;
    }

    public boolean commit(Move move) {
        System.out.println("Commit");
        Position position = move.getPosition();
        if (!position.isWithin(0, width, 0, height)) {
            throw new IllegalArgumentException(String.format("%s is out of bounds.", position));
        }
        Cell cell = getCell(position.getX(), position.getY());
        switch(move.getMoveType()) {
            case UNCOVER: return commitUncover(cell);
            case FLAG: return commitFlag(cell);
            case QUESTION: return commitQuestion(cell);
            default: return false;
        }
    }

    @Override
    public String toString() {
        String s = "";

        for (int y = -1; y < height; y++) {
            if (y == -1) for (int x = -1; x < width; x++) s += (x == -1 ? "  " : (char)('A' + x) + " ");
            else for (int x = -1; x < width; x++) s += x == -1 ? y + 1 + " "  : getFormattedString(x, y);
            s += "\n";
        }

        return s;
    }

}