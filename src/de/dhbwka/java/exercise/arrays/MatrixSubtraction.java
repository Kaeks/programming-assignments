package de.dhbwka.java.exercise.arrays;

import java.util.Scanner;

public class MatrixSubtraction {
    public static void main(String[] args) {
        MatrixSubtraction ms = new MatrixSubtraction();
        Scanner scan = new Scanner(System.in);

        System.out.print("X: ");
        int x = scan.nextInt();
        System.out.print("Y: ");
        int y = scan.nextInt();

        System.out.print("Minimum value: ");
        int min = scan.nextInt();
        System.out.print("Maximum value: ");
        int max = scan.nextInt();

        Matrix m0 = new Matrix(x, y);
        m0.fillRandomly(min, max);
        Matrix m1 = new Matrix(x, y);
        m1.fillRandomly(min, max);

        System.out.println("X:");
        m0.display();
        System.out.println("Y:");
        m1.display();

        Matrix mr = ms.subtractMatrices(m0, m1);

        System.out.println("R:");
        mr.display();

        scan.close();
    }

    Matrix subtractMatrices(Matrix m0, Matrix m1) {
        int y = m0.getY();
        int x = m0.getX();
        Matrix mr = new Matrix(x, y);
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                int result = m0.getValue(i, j) - m1.getValue(i, j);
                mr.setValue(i, j, result);
            }
        }
        return mr;
    }
}
