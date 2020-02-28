package de.dhbwka.java.exercise.classes.candycrush;

public class Move {

    private Position pos1;
    private Position pos2;

    public Move(Position pos1, Position pos2) {
        this.pos1 = pos1;
        this.pos2 = pos2;
        if (!pos1.isAdjacentTo(pos2)) throw new IllegalArgumentException("Positions must be adjacent to each other.");
        if (pos1.equals(pos2)) throw new IllegalArgumentException("Positions cannot be the same.");
    }

    public Position getPos1() {
        return pos1;
    }

    public Position getPos2() {
        return pos2;
    }

    public boolean isWithin(int minX, int maxX, int minY, int maxY) {
        return pos1.isWithin(minX, maxX, minY, maxY) && pos2.isWithin(minX, maxX, minY, maxY);
    }

    public boolean isWithin(int min, int max) {
        return isWithin(min, max, min, max);
    }
}