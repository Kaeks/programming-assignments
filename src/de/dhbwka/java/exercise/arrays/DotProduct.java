package de.dhbwka.java.exercise.arrays;

import java.util.Scanner;

public class DotProduct {
    public static void main(String[] args) {
        DotProduct dp = new DotProduct();
        Scanner scan = new Scanner(System.in);
        System.out.print("# of elements n: ");
        int n = scan.nextInt();
        int[] vectorX = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.printf("Value of x_%d: ", i);
            vectorX[i] = scan.nextInt();
        }
        int[] vectorY = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.printf("Value of y_%d: ", i);
            vectorY[i] = scan.nextInt();
        }
        int dot = dp.getDotProduct(vectorX, vectorY);
        System.out.printf("Dot product of x and y is %d", dot);
        scan.close();
    }

    int getDotProduct(int[] vX, int[] vY) {
        int value = 0;
        int length = vX.length;
        for (int i = 0; i < length; i++) {
            value += vX[i] * vY[i];
        }
        return value;
    }
}
