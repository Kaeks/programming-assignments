package de.dhbwka.java.exercise.methods;

import java.util.Scanner;

public class Fibonacci {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.print("POSITIVE N!!!! (NEGATIVE WILL EXIT): ");
            int n = scan.nextInt();
            if (n < 0) break;
            System.out.println("RESULT!");
            for (int i = 0; i < n; i++) {
                System.out.printf("F(%3d) = %5d%n", i + 1, calc(i + 1));
            }
        }
        scan.close();
    }

    private static int calc(int n) {
        if (n < 1) throw new NumberFormatException("LESS THan 1 bAD!");
        return n == 1 || n == 2 ? 1 : calc(n - 1) + calc(n - 2);
    }
}