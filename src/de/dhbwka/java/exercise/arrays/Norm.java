package de.dhbwka.java.exercise.arrays;

import java.util.Scanner;

public class Norm {
    public static void main(String[] args) {
        Norm norm = new Norm();
        Scanner scan = new Scanner(System.in);
        System.out.print("# of elements n: ");
        int n = scan.nextInt();
        int[] vector = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.printf("Value of x_%d: ", i);
            vector[i] = scan.nextInt();
        }
        double len = norm.getVectorLength(vector);
        System.out.printf("Length of vector x is %f", len);
        scan.close();
    }

    double getVectorLength(int[] vector) {
        int value = 0;
        for (int el: vector) value += Math.pow(el, 2);
        return Math.sqrt(value);
    }
}
