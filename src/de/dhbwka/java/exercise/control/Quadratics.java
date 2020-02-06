package de.dhbwka.java.exercise.control;

import java.util.Scanner;

public class Quadratics {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("a? ");
        double a = scanner.nextDouble();
        System.out.print("b? ");
        double b = scanner.nextDouble();
        System.out.print("c? ");
        double c = scanner.nextDouble();
        Quadratics quad = new Quadratics();

        try {
            double[] solutions = quad.calculate(a, b, c);
            for (double solution : solutions) {
                System.out.println(solution);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        scanner.close();
    }

    private double[] calculate(double a, double b, double c) throws Exception {
        double[] solutions = new double[2];
        if (a == 0) {
            if (b == 0) {
                throw new Exception("Die Gleichung ist degeneriert");
            } else {
                double x = -1 * c / b;
                solutions[0] = x;
            }
        } else {
            double discriminant = Math.pow(b, 2) - 4 * a * c;
            if (discriminant >= 0) {
                solutions[0] = (-1 * b + Math.sqrt(discriminant)) / 2 * a;
                solutions[1] = (-1 * b - Math.sqrt(discriminant)) / 2 * a;
            } else {
                throw new Exception("Die Gleichung ist konjugiert komplex.");
            }
        }
        return solutions;
    }
}
