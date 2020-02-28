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
        if (value == '#') return AnsiCode.WHITE_BACKGROUND.getValue() + value + " " + AnsiCode.RESET.getValue();
        return value + " ";
    }

    private String getFormattedString(int x, int y) {
        return getFormattedString(getValue(x, y));
    }

    /**
     * Fills the field with a set amount of mines without placing one at a specific position
     * @param position The position to avoid
     * @param amount The amount of mines to place
     */
    public void fillAround(Position position, int amount) {
        for (int i = 0; i < amount; i++) {
            int randX;
            int randY;
            boolean valid = false;
            do {
                randX = (int) (Math.random() * width);
                randY = (int) (Math.random() * height);
                if (randX == position.getX() && randY == position.getY()) continue;
                if (getCell(randX, randY).setMine()) valid = true;
            } while (!valid);
        }
    }

    /**
     * Fills the field with a set amount of mines
     * @param amount The amount of mines to place
     */
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

    /**
     * Uncovers the field recursively
     * @param cell The cell to attempt to uncover
     */
    private void recursiveUncover(Cell cell) {
        // Skip uncovered cells
        if (!cell.isCovered()) return;

        cell.uncover();

        System.out.println(this);

        if (!cell.isMine() && getNeighboringMines(cell) == 0) {
            Position pos = cell.getPosition();
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    // Skip same cell
                    if (i == 0 && j == 0) continue;

                    int lookingX = pos.getX() + i;
                    int lookingY = pos.getY() + j;

                    // Check out of bounds and skip
                    if (lookingX < 0 || lookingX >= width) continue;
                    if (lookingY < 0 || lookingY >= height) continue;

                    // Uncover recursively
                    recursiveUncover(getCell(lookingX, lookingY));
                }
            }
        }
    }

    private void commitUncover(Cell cell) {
        if (!cell.isCovered()) return;
        if (cell.isFlag()) return;
        recursiveUncover(cell);
    }

    private void commitFlag(Cell cell) {
        if (!cell.isCovered()) return;
        cell.toggleFlag();
    }

    private void commitQuestion(Cell cell) {
        if (!cell.isCovered()) return;
        cell.toggleQuestion();
    }

    public Cell commit(Move move) {
        Position position = move.getPosition();
        if (!position.isWithin(0, width, 0, height)) {
            throw new IllegalArgumentException(String.format("%s is out of bounds.", position));
        }
        Cell cell = getCell(position.getX(), position.getY());
        switch(move.getMoveType()) {
            case UNCOVER: commitUncover(cell); break;
            case FLAG: commitFlag(cell); break;
            case QUESTION: commitQuestion(cell); break;
        }
        return cell;
    }

    public void uncoverAllMines() {
        for (ArrayList<Cell> row : cells) {
            for (Cell cell : row) {
                if (cell.isMine()) cell.uncover();
            }
        }
    }

    public boolean allMinesFound() {
        for (ArrayList<Cell> row : cells) {
            for (Cell cell : row) {
                if (!cell.isMine() && cell.isCovered()) return false;
            }
        }
        return true;
    }

    private String getColumnName(int number) {
        StringBuilder sb = new StringBuilder();
        while (number-- > 0) {
            sb.append((char)('A' + (number % 26)));
            number /= 26;
        }
        return sb.reverse().toString();
    }

    private String getFormattedHeader(String s) {
        return AnsiCode.RED.getValue() + s + AnsiCode.RESET.getValue();
    }

    @Override
    public String toString() {
        String s = "";

        for (int y = -1; y < height; y++) {
            if (y == -1) for (int x = -1; x < width; x++) s += (x == -1 ? "  " : getFormattedHeader(String.format("%-2s", getColumnName(x + 1))));
            else for (int x = -1; x < width; x++) s += x == -1 ? getFormattedHeader(String.format("%-2d", y + 1))  : getFormattedString(x, y);
            s += "\n";
        }

        return s;
    }

}