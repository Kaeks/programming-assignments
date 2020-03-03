package de.dhbwka.java.exercise.classes.candycrush;

import java.util.HashSet;

import de.jakob.util.AnsiCode;

public class Field {

    private int size;
    private int colors;
    private byte[][] values;

    private final byte DELETED_VALUE = -1;
    public Field(int size, int colors) {
        this.size = size;
        this.colors = colors;
        values = new byte[size][size];
        generateValues();
    }

    public int getSize() {
        return size;
    }

    public byte getValueAt(int x, int y) {
        return values[y][x];
    }

    public byte getValueAt(Position pos) {
        return getValueAt(pos.getX(), pos.getY());
    }

    private void setValueAt(int x, int y, byte value) {
        values[y][x] = value;
    }

    private void setValueAt(Position pos, byte value) {
        setValueAt(pos.getX(), pos.getY(), value);
    }

    private void setDeleted(int x, int y) {
        setValueAt(x, y, DELETED_VALUE);
    }

    private void setDeleted(Position pos) {
        setDeleted(pos.getX(), pos.getY());
    }

    /**
     * Randomly populates the entire field
     */
    private void generateValues() {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                values[y][x] = generateValue(x, y);
            }
        }
    }

    /**
     * Looks for cells to delete horizontally with regard for the cell located at a specific position
     * @param pos The position to start looking from
     * @return A set of positions that are to be deleted
     */
    private HashSet<Position> findToDeleteHorizontally(Position pos) {
        byte value = getValueAt(pos);
        int startIndex = pos.getX(), endIndex = pos.getX();
        while (startIndex > 0 && getValueAt(startIndex - 1, pos.getY()) == value) {
            startIndex--;
        }
        while (endIndex < size - 1 && getValueAt(endIndex + 1, pos.getY()) == value) {
            endIndex++;
        }
        HashSet<Position> toDelete = new HashSet<Position>();
        if (endIndex - startIndex >= 3 - 1) {
            for (int x = startIndex; x <= endIndex; x++) {
                toDelete.add(new Position(x, pos.getY()));
            }
        }
        return toDelete;
    }

    /**
     * Looks for cells to delete vertically with regard for the cell located at a specific position
     * @param pos The position to start looking from
     * @return A set of positions that are to be deleted
     */
    private HashSet<Position> findToDeleteVertically(Position pos) {
        byte value = getValueAt(pos);
        int startIndex = pos.getY(), endIndex = pos.getY();
        while (startIndex > 0 && getValueAt(pos.getX(), startIndex - 1) == value) {
            startIndex--;
        }
        while (endIndex < size - 1 && getValueAt(pos.getX(), endIndex + 1) == value) {
            endIndex++;
        }
        HashSet<Position> toDelete = new HashSet<Position>();
        if (endIndex - startIndex >= 3 - 1) {
            for (int y = startIndex; y <= endIndex; y++) {
                toDelete.add(new Position(pos.getX(), y));
            }
        }
        return toDelete;
    }

    /**
     * Looks for cells to delete following a certain Position
     * @param pos The position to check
     * @return A set of positions that are to be deleted
     */
    private HashSet<Position> findToDelete(Position pos) {
        HashSet<Position> set1 = findToDeleteHorizontally(pos);
        HashSet<Position> set2 = findToDeleteVertically(pos);
        HashSet<Position> merged = new HashSet<Position>();
        merged.addAll(set1);
        merged.addAll(set2);
        return merged;
    }

    /**
     * Looks for cells to delete along a certain column
     * @param x The x coordinate of the column to check
     * @return A set of positions that are to be deleted
     */
    private HashSet<Position> findToDeleteInColumn(int x) {
        HashSet<Position> set = new HashSet<Position>();
        for (int y = 0; y < size; y++) {
            set.addAll(findToDelete(new Position(x, y)));
        }
        return set;
    }

    /**
     * Looks for cells to delete following a certain move
     * @param move The move to check
     * @return A set of positions that are to be deleted
     */
    private HashSet<Position> findToDelete(Move move) {
        HashSet<Position> set1 = findToDelete(move.getPos1());
        HashSet<Position> set2 = findToDelete(move.getPos2());
        HashSet<Position> merged = new HashSet<Position>();
        merged.addAll(set1);
        merged.addAll(set2);
        return merged;
    }

    /**
     * Sets the values of the cells located at all positions inside the set to the "deleted" value (0)
     * @param toDelete A set of positions to delete
     */
    private void setValuesOfToBeDeleted(HashSet<Position> toDelete) {
        for (Position pos : toDelete) {
            setDeleted(pos);
        }
    }

    /**
     * "Bubbles" the deleted values of a column up to the top of the column
     * @param x The index of the column to bubble up
     */
    private void bubbleUpColumn(int x) {
        for (int i = 0; i < size; i++) {
            for (int y = size - 1; y > 0; y--) {
                Position shiftPosition = new Position(x, y);
                if (getValueAt(shiftPosition) == DELETED_VALUE) {
                    Position dropPosition = new Position(x, y - 1);
                    swap(shiftPosition, dropPosition);
                }
            }
        }
    }

    /**
     * Repopulates a certain column
     * @param x The index of the column to repopulate
     */
    private void regenerateColumn(int x) {
        int y = 0;
        while (getValueAt(x, y) == DELETED_VALUE) {
            setValueAt(x, y, generateValue(x, y));
            y++;
        }
    }

    /**
     * Determines whether a certain column needs to be shifted. (Deleted values pushed to top)
     * @param x The index of the column to check
     * @return Needs to be shifted
     */
    private boolean needsShifting(int x) {
        boolean sawRegular = false;
        for (int y = 0; y < size; y++) {
            boolean isDeleted = getValueAt(x, y) == DELETED_VALUE;
            if (sawRegular && isDeleted) return true;
            if (!(sawRegular || isDeleted)) sawRegular = true;
        }
        return false;
    } 

    private boolean hasDeletedCells(int x) {
        for (int y = 0; y < size; y++) if (getValueAt(x, y) == DELETED_VALUE) return true;
        return false;
    }

    /**
     * Recursive deletion function
     * @param toDelete Initial set of positions of cells to delete
     * @return Amount of cells deleted in total
     */
    private int executeOrderDeletyDelete(HashSet<Position> toDelete) {
        int deleted = toDelete.size();
        setValuesOfToBeDeleted(toDelete);
        HashSet<Position> moreDelete = new HashSet<Position>();
        for (int x = 0; x < size; x++) {
            if (hasDeletedCells(x)) {
                if (needsShifting(x)) {
                    System.out.println(this);
                    System.out.printf("Column %d needs shifting!", x);
                    bubbleUpColumn(x);
                    System.out.println(this);
                }
                regenerateColumn(x);
                System.out.println(this);
                HashSet<Position> columnDelete = findToDeleteInColumn(x);
                moreDelete.addAll(columnDelete);
            }
        }
        drawDeleteList(moreDelete);
        if (moreDelete.size() > 0) {
            deleted += executeOrderDeletyDelete(moreDelete);
        }
        return deleted;
    }

    private void drawDeleteList(HashSet<Position> toDelete) {
        boolean[][] bools = new boolean[size][size];
        for (Position pos : toDelete) {
            bools[pos.getY()][pos.getX()] = true;
        }
        for (boolean[] row : bools) {
            for (boolean val : row) System.out.print(val ? "XX" : "##");
            System.out.println("");
        }
    }

    public int commit(Move move) {
        if (!move.isWithin(0, size)) throw new IllegalArgumentException("Move out of bounds.");
        swap(move.getPos1(), move.getPos2());
        HashSet<Position> toDelete = findToDelete(move);
        drawDeleteList(toDelete);
        if (toDelete.size() > 0) {
            return executeOrderDeletyDelete(toDelete);
        } else {
            swap(move.getPos1(), move.getPos2());
            throw new IllegalArgumentException("Move does not produce any matches.");
        }
    }

    public void swap(Position from, Position to) {
        byte temp = getValueAt(from);
        setValueAt(from, getValueAt(to));
        setValueAt(to, temp);
    }

    private byte generateValue(int x, int y) {
        boolean valid;
        byte random = -1;
        do {
            valid = true;
            random = (byte) (Math.random() * colors);
            int neighboringX = 0;
            for (int i = x - 2; i < x + 2; i++) {
                if (i < 0 || i == x || i > size - 1) continue;
                if (values[y][i] == random) neighboringX++;
                if (neighboringX >= 2) {
                    valid = false;
                    break;
                }
            }
            int neighboringY = 0;
            for (int i = y - 2; i < y + 2; i++) {
                if (i < 0 || i == y || i > size - 1) continue;
                if (values[i][x] == random) neighboringY++;
                if (neighboringY >= 2) {
                    valid = false;
                    break;
                }
            }
        } while (!valid);
        return random;
    }

    private String getColoredString(String content, byte color) {
        String codeString;
        switch (color) {
            case 0: codeString = AnsiCode.RED_BACKGROUND.getValue(); break;
            case 1: codeString = AnsiCode.YELLOW_BACKGROUND.getValue(); break;
            case 2: codeString = AnsiCode.GREEN_BACKGROUND.getValue(); break;
            case 3: codeString = AnsiCode.CYAN_BACKGROUND.getValue(); break;
            case 4: codeString = AnsiCode.BLUE_BACKGROUND.getValue(); break;
            case 5: codeString = AnsiCode.PURPLE_BACKGROUND.getValue(); break;
            case 6: codeString = AnsiCode.WHITE_BACKGROUND.getValue(); break;
            default: codeString = AnsiCode.BLACK_BACKGROUND.getValue(); break;
        }
        return codeString + content + AnsiCode.RESET.getValue();
    }

    @Override
    public String toString() {
        String s = "";

        for (int y = -1; y < size; y++) {
            if (y == -1) for (int x = -1; x < size; x++) s += (x == -1 ? " " : (char)('A' + x)) + " ";
            else for (int x = -1; x < size; x++) s += x == -1 ? y + 1 + " " : colors <= 7 ? getColoredString("  ", getValueAt(x, y)) : String.format("%2d", getValueAt(x, y));
            s += "\n";
        }

        return s;
    }

}
