package de.dhbwka.java.exercise.arrays;

public class Matrix {
    private int x;
    private int y;
    private int[][] values;

    public Matrix(int x, int y) {
        this.x = x;
        this.y = y;
        this.values = new int[y][x];
    }

    int getX() { return this.x; }
    int getY() { return this.y; }

    int getValue(int x, int y) {
        return this.values[y][x];
    }

    void setValue(int x, int y, int value) {
        this.values[y][x] = value;
    }

    void fillRandomly(int min, int max) {
        for (int row = 0; row < y; row++) {
            for (int col = 0; col < x; col++) {
                this.values[col][row] = (int) (Math.random() * (max - min) + min);
            }
        }
    }

    void display() {
        int len = this.getRequiredSpacing();
        for (int[] row: this.values) {
            for (int val: row) {
                System.out.printf("%" + len + "d", val);
            }
            System.out.printf("%n");
        }
    }

    private int getRequiredSpacing() {
        int maxLen = -1;
        for (int[] row: this.values) {
            for (int val: row) {
                int curLen = (int) Math.log10(Math.abs(val)) + 1;
                if (val < 0) curLen++;
                if (curLen > maxLen) maxLen = curLen;
            }
        }
        // +1 for a space between
        return maxLen + 1;
    }
}
