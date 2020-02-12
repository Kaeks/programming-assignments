package de.dhbwka.java.exercise.arrays;

import java.util.Scanner;

public class Pascal {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Pascal p = new Pascal();
        System.out.print("n: ");
        int n = scan.nextInt();
        int[][] pascal = new int[n][];
        for (int i = 0; i < n; i++) {
            pascal[i] = new int[i + 1];
        }
        p.fillPascal(pascal);
        p.displayPascal(pascal);
    }

    void fillPascal(int[][] pascal) {
        for (int i = 0; i < pascal.length; i++) {
            for (int j = 0; j < pascal[i].length; j++) {
                pascal[i][j] = this.getPascalValue(j, i);
            }
        }
    }

    int getPascalValue(int x, int y) {
        if (x == 0 || x == y) return 1;
        return getPascalValue(x, y - 1) + getPascalValue(x - 1, y - 1);
    }

    void displayPascal(int[][] pascal) {
        int arbitraryWidthValue = 4;
        for (int[] row: pascal) {
            for (int val: row) {
                System.out.printf("%" + arbitraryWidthValue + "d", val);
            }
            System.out.printf("%n");
        }
    }
}
