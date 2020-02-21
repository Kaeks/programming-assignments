package de.dhbwka.java.exercise.arrays;

import java.util.Scanner;

public class Pascal {

    private int n;
    private int[][] values;

    private int spacing;

    private int calculations = 0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("n: ");
        int n = scan.nextInt();
        int inSpacing = -1;
        /*
        TODO spacing, enter to set default value
        System.out.print("Spacing (default=2): ");
        if (scan.hasNextInt()) {
            inSpacing = scan.nextInt();
        }
        */
        Pascal p;
        if (inSpacing >= 0) p = new Pascal(n, inSpacing);
        else p = new Pascal(n);
        p.fill();
        p.display();
        scan.close();
    }

    public Pascal(int n) {
        this.n = n;
        build();
        spacing = 2;
    }

    public Pascal(int n, int spacing) {
        this.n = n;
        build();
        this.spacing = spacing;
    }

    private void build() {
        values = new int[n][];
        for (int i= 0; i < n; i++) {
            values[i] = new int[i + 1];
        }
    }

    void fill() {
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[i].length; j++) {
                values[i][j] = getPascalValue(j, i);
            }
        }
        System.out.println(calculations);
    }

    private int getPascalValue(int x, int y) {
        calculations++;
        if (x == 0 || x == y) return 1;
        return getPascalValue(x, y - 1) + getPascalValue(x - 1, y - 1);
    }

    void display() {
        for (int i = 0; i < values.length; i++) {
            int[] row = values[i];
            for (int j = 0; j < values.length - i; j++) {
                for (int k = 0; k < spacing; k++) {
                    System.out.print(" ");
                }
            }
            for (int val : row) {
                System.out.printf("%" + spacing * 2 + "d", val);
            }
            System.out.printf("%n");
        }
    }
}
