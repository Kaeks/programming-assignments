package de.dhbwka.java.exercise.classes.candycrush;

import java.util.HashSet;

import de.jakob.util.AnsiCode;
import de.jakob.util.Pair;

public class Field {

    private int size;
    private int colors;
    private byte[][] values;

    private final byte DELETED_VALUE = -1;
    private final byte NO_VALUE = -2;

    public Field(int size, int colors) {
        this.size = size;
        this.colors = colors;
        values = new byte[size][size];
        generateValues();
    }

    public int getSize() {
        return size;
    }

    public int getColors() {
        return colors;
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

    /*
     * POSSIBLE MOVE FINDING AI
     */

    private HashSet<Pair<Position, Position>> findHorizontal110Pairs() throws Exception {
        HashSet<Pair<Position, Position>> pairs = new HashSet<Pair<Position, Position>>();
        for (int y = 0; y < size; y++) {
            int neighboring = 0;
            byte curValue = NO_VALUE;
            for (int x = 0; x < size; x++) {
                byte valueHere = getValueAt(x, y);
                if (x == 0) curValue = valueHere;
                if (valueHere == curValue) neighboring++;
                else neighboring = 0;
                if (neighboring == 2) {
                    Position pos1 = new Position(x - 1, y);
                    Position pos2 = new Position(x, y);
                    Pair<Position, Position> pair = new Pair<Position, Position>(pos1, pos2);
                    pairs.add(pair);
                }
                if (neighboring > 2) {
                    throw new Exception(String.format("More than 2 neighboring same-colored fields in row %d, position %d. The field is supposed to be resting.", y, x));
                }
            }
        }
        return pairs;
    }

    private HashSet<Pair<Position, Position>> findVertical110Pairs() throws Exception {
        HashSet<Pair<Position, Position>> pairs = new HashSet<Pair<Position, Position>>();
        for (int x = 0; x < size; x++) {
            int neighboring = 0;
            byte curValue = NO_VALUE;
            for (int y = 0; y < size; y++) {
                byte valueHere = getValueAt(x, y);
                if (y == 0) curValue = valueHere;
                if (valueHere == curValue) neighboring++;
                else neighboring = 0;
                if (neighboring == 2) {
                    Position pos1 = new Position(x, y - 1);
                    Position pos2 = new Position(x, y);
                    Pair<Position, Position> pair = new Pair<Position, Position>(pos1, pos2);
                    pairs.add(pair);
                }
                if (neighboring > 2) {
                    throw new Exception(String.format("More than 2 neighboring same-colored fields in column %d, position %d. The field is supposed to be resting.", x, y));
                }
            }
        }
        return pairs;
    }

    private byte getPairValue(Pair<Position, Position> pair) throws Exception {
        byte val1 = getValueAt(pair.getL());
        byte val2 = getValueAt(pair.getR());
        if (val1 != val2) throw new Exception("Something went wrong with this pair, the values do not match.");
        return val1;
    }

    private void addMoveIfFits(HashSet<Move> set, Position check, Position put, byte value) {
        if (getValueAt(check) == value) set.add(new Move(check, put));
    }

    private HashSet<Move> findHorizontal110Moves() throws Exception {
        HashSet<Move> moves = new HashSet<Move>();
        HashSet<Pair<Position, Position>> pairs = findHorizontal110Pairs();
        for (Pair<Position, Position> pair : pairs) {
            byte value = getPairValue(pair);
            Position l = pair.getL();
            int y = l.getY();
            if (l.getX() > 0) {
                Position close = new Position(l.getX() - 1, y);
                if (y > 0) addMoveIfFits(moves, new Position(l.getX() - 1, y - 1), close, value);
                if (y < size - 1) addMoveIfFits(moves, new Position(l.getX() - 1, y + 1), close, value);
                if (l.getX() > 1) addMoveIfFits(moves, new Position(l.getX() - 2, y), close, value);
            }
            Position r = pair.getR();
            if (r.getX() < size - 1) {
                Position close = new Position(r.getX() + 1, y);
                if (y > 0) addMoveIfFits(moves, new Position(r.getX() + 1, y - 1), close, value);
                if (y < size - 1) addMoveIfFits(moves, new Position(r.getX() + 1, y + 1), close, value);
                if (r.getX() < size - 2) addMoveIfFits(moves, new Position(r.getX() + 2, y), close, value);
            }
        }
        return moves;
    }

    private HashSet<Move> findVertical110Moves() throws Exception {
        HashSet<Move> moves = new HashSet<Move>();
        HashSet<Pair<Position, Position>> pairs = findVertical110Pairs();
        for (Pair<Position, Position> pair : pairs) {
            byte value = getPairValue(pair);
            Position u = pair.getL();
            int x = u.getX();
            if (u.getY() > 0) {
                Position close = new Position(x, u.getY() - 1);
                if (x > 0) addMoveIfFits(moves, new Position(x - 1, u.getY() - 1), close, value);
                if (x < size - 1) addMoveIfFits(moves, new Position(x + 1, u.getY() - 1), close, value);
                if (u.getY() > 1) addMoveIfFits(moves, new Position(x, u.getY() - 2), close, value);
            }
            Position d = pair.getR();
            if (d.getY() < size - 1) {
                Position close = new Position(x, d.getY() + 1);
                if (x > 0) addMoveIfFits(moves, new Position(x - 1, d.getY() + 1), close, value);
                if (x < size - 1) addMoveIfFits(moves, new Position(x + 1, d.getY() + 1), close, value);
                if (d.getY() < size - 2) addMoveIfFits(moves, new Position(x, d.getY() + 2), close, value);
            }
        }
        return moves;
    }

    /**
     * Finds possible "110" moves (moves, where a block could be moved next to a pair of matching blocks)
     * @return Set of possible moves
     * @throws Exception
     */
    private HashSet<Move> find110Moves() throws Exception {
        HashSet<Move> moves = new HashSet<Move>();
        moves.addAll(findHorizontal110Moves());
        moves.addAll(findVertical110Moves());
        return moves;
    }

    private HashSet<Pair<Position, Position>> findHorizontal101Pairs() {
        HashSet<Pair<Position, Position>> pairs = new HashSet<Pair<Position, Position>>();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                Position here = new Position(x, y);
                if (x < size - 2) {
                    Position there = new Position(x + 2, y);
                    if (getValueAt(there) == getValueAt(here)) {
                        pairs.add(new Pair<Position, Position>(here, there));
                    }
                }
            }
        }
        return pairs;
    }

    private HashSet<Pair<Position, Position>> findVertical101Pairs() {
        HashSet<Pair<Position, Position>> pairs = new HashSet<Pair<Position, Position>>();
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Position here = new Position(x, y);
                if (y < size - 2) {
                    Position there = new Position(x, y + 2);
                    if (getValueAt(there) == getValueAt(here)) {
                        pairs.add(new Pair<Position, Position>(here, there));
                    }
                }
            }
        }
        return pairs;
    }

    private HashSet<Move> findHorizontal101Moves() throws Exception {
        HashSet<Move> moves = new HashSet<Move>();
        HashSet<Pair<Position, Position>> pairs = findHorizontal101Pairs();
        for (Pair<Position, Position> pair : pairs) {
            byte value = getPairValue(pair);
            Position l = pair.getL();
            if (l.getY() > 0) {

            }
            if (l.getY() < size - 1) {

            }
            Position r = pair.getL();
        }
        return moves;
    }

    /**
     * Finds possible "101" moves (moves, where a block could be moved inbetween a pair of matching blocks)
     * @return Set of possible moves
     * @throws Exception
     */
    private HashSet<Move> find101Moves() throws Exception {
        HashSet<Move> moves = new HashSet<Move>();
        moves.addAll(findHorizontal101Moves());
        // moves.addAll(findVertical101Moves());
        return moves;
    }

    private HashSet<Move> findMoves() throws Exception {
        HashSet<Move> moves = new HashSet<Move>();
        moves.addAll(find110Moves());
        moves.addAll(find101Moves());
        return moves;
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
