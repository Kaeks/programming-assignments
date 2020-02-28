package de.dhbwka.java.exercise.classes.candycrush;

import java.util.ArrayList;

import de.jakob.util.AnsiCode;

public class Field {

    private int size;
    private int colors;
    private byte[][] values;

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

    private void generateValues() {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                values[y][x] = generateValue(x, y);
            }
        }
    }

    public boolean isValidVertically(Position pos, byte value) {
        int neighboring = 0;
        for (int y = pos.getY() - 2; y <= pos.getY() + 2; y++) {
            if (y < 0 || y >= size) continue;
            if (y == pos.getY() || getValueAt(pos.getX(), y) == value) neighboring++;
            else neighboring = 0;
            if (neighboring >= 3) return true;
        }
        return false;
    }

    public boolean isValidHorizontally(Position pos, byte value) {
        int neighboring = 0;
        for (int x = pos.getX() - 2; x <= pos.getX() + 2; x++) {
            if (x < 0 || x >= size) continue;
            if (x == pos.getX() || getValueAt(x, pos.getY()) == value) neighboring++;
            else neighboring = 0;
            if (neighboring >= 3) return true;
        }
        return false;
    }

    public boolean isValidMove(Position from, Position to){
        // Catch moves outside of the field
        if (!from.isWithin(0, size - 1) || !to.isWithin(0, size - 1)) return false;
        // Catch same field
        if (from.equals(to)) return false;
        // Catch fields that are not adjacent to each other
        if (!from.isAdjacentTo(to)) return false;
        byte fromValue = getValueAt(from);
        byte toValue = getValueAt(to);
        if (isValidHorizontally(from, toValue) || isValidVertically(from, toValue) || isValidHorizontally(to, fromValue) || isValidVertically(to, fromValue)) {
            return true;
        }
        return false;
    }

    private ArrayList<Position> findToDeleteHorizontally(Position pos) {
        byte value = getValueAt(pos);
        int neighboring = 0;
        ArrayList<Position> toDelete = new ArrayList<Position>();
        for (int x = 0; x < size; x++) {
            if (getValueAt(x, pos.getY()) == value) neighboring++;
            else if (neighboring >= 3) {
                for (int i = x - 1; i >= x - neighboring; i--) {
                    toDelete.add(new Position (i, pos.getX()));
                }
                break;
            } else neighboring = 0;
        }
        return toDelete;
    }

    private ArrayList<Position> findToDeleteVertically(Position pos) {
        byte value = getValueAt(pos);
        int neighboring = 0;
        ArrayList<Position> toDelete = new ArrayList<Position>();
        for (int y = 0; y < size; y++) {
            if (getValueAt(pos.getX(), y) == value) neighboring++;
            else if (neighboring >= 3) {
                for (int i = y - 1; i >= y - neighboring; i--) {
                    toDelete.add(new Position (pos.getX(), i));
                }
                break;
            } else neighboring = 0;
        }
        return toDelete;
    }

    private ArrayList<Position> mergePositions(ArrayList<Position> list1, ArrayList<Position> list2) {
        ArrayList<Position> merged = new ArrayList<Position>();
        merged.addAll(list1);
        for (Position pos : list2) if (!merged.contains(pos)) merged.add(pos);
        return merged;
    }

    private ArrayList<Position> findToDelete(Move move) {
        return mergePositions(findToDelete(move.getPos1()), findToDelete(move.getPos2()));
    }

    private ArrayList<Position> findToDelete(Position pos) {
        return mergePositions(findToDeleteHorizontally(pos), findToDeleteVertically(pos));
    }

    /**
     * Shifts a column down by 1
     * @param x The index of the column to shift
     * @param maxY The lowest point of the partial column to shift
     */
    private void shiftColumn(int x, int maxY) {
        for (int y = maxY; y >= 0; y--) {
            swap(new Position(x, y), new Position(x, y + 1));
        }
    }

    private ArrayList<Position> findToDeleteInColumn(int x) {
        ArrayList<Position> merged = new ArrayList<Position>();
        for (int y = 0; y < size; y++) {
            merged = mergePositions(merged, findToDelete(new Position(x, y)));
        }
        return merged;
    }

    private void regenerateColumn(int x) {
        int curY = 0;
        while (getValueAt(x, curY) == -1) {
            setValueAt(x, curY, generateValue(x, curY));
            curY++;
        }
    }

    private int executeOrderDeletyDelete(ArrayList<Position> toDelete) {
        int deleted = 0;
        for (Position pos : toDelete) {
            setValueAt(pos, (byte) -1);
            if (pos.getY() != 0) shiftColumn(pos.getX(), pos.getY() - 1);
            deleted++;
        }
        // System.out.println("Deleted!");
        // System.out.println(this);

        for (int x = 0; x < size; x++) {
            regenerateColumn(x);
            // System.out.println("Regenerated column!");
            // System.out.println(this);
            ArrayList<Position> anotherToDelete = findToDeleteInColumn(x);
            if (anotherToDelete.size() > 0) {
                // System.out.println("Found to delete another!");
                // System.out.println(this);
                deleted += executeOrderDeletyDelete(anotherToDelete);
            }
        }

        return deleted;
    }

    public int commit(Move move) {
        if (!move.isWithin(0, size)) throw new IllegalArgumentException("Move out of bounds.");
        swap(move.getPos1(), move.getPos2());
        ArrayList<Position> toDelete = findToDelete(move);
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
