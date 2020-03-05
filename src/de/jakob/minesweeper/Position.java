package de.jakob.minesweeper;

public class Position {

    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public boolean isWithin(int min, int max) {
        return isWithin(min, max, min, max);
    }

    public boolean isWithin(int minX, int maxX, int minY, int maxY) {
        return x >= minX && x <= maxX && y >= minY && y <= maxY;
    }
    
    @Override
    public String toString() {
        return String.format("Position: X=%d, Y=%d", x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Position) {
            Position po = (Position) o;
            return x == po.getX() && y == po.getY();
        }
        return false;
    }
}