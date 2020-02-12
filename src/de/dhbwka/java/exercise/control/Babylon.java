package de.dhbwka.java.exercise.control;

import java.util.Scanner;

public class Babylon {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double xn = 0;
        do {
            System.out.print("x0?: ");
            xn = scanner.nextInt();
        } while (xn == 0);

        System.out.print("Root of which #?: ");
        int toRoot = scanner.nextInt();

        double xn1 = xn;

        do {
            xn = xn1;
            xn1 = (xn + toRoot / xn) / 2;
            System.out.printf("xn: %.7f%n", xn);
        } while (Math.abs(xn1 - xn) > 10e-6);

        System.out.printf("Result: %.0f", xn);
        scanner.close();
    }
}
