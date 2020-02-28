package de.dhbwka.java.exercise.methods;

import java.util.Scanner;

public class Exponentiation {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("BASIS!!!!!: ");
        double base = scan.nextDouble();
        System.out.print("POSITIVE EXPONENT!!!!: ");
        int exp = scan.nextInt();
        System.out.printf("RESULT!!!: %.1f%n", xPowerN(base, exp));
        scan.close();
    }

    private static double xPowerN(double x, int n) {
        if (n < 0) throw new NumberFormatException("LESS THAN 0 BAD");
        return n == 0 ? 1 : x * xPowerN(x, n - 1);
    }
}