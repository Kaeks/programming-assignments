package de.dhbwka.java.exercise.classes;

import java.util.ArrayList;

public class ComplexTest {
    public static void main(String[] args) {
        ComplexTest ct = new ComplexTest();

        Complex c1 = new Complex(1, 2);
        System.out.println("C1: " + c1.toString());

        Complex c2 = new Complex(2, 1);
        System.out.println("C2: " + c2.toString());

        System.out.println("C1 + C2: " + c1.add(c2).toString());
        System.out.println("C1 - C2: " + c1.sub(c2).toString());
        System.out.println("C1 * C2: " + c1.mult(c2).toString());
        System.out.println("C1 / C2: " + c1.div(c2).toString());
        System.out.println("C1 < C2: " + c1.isLess(c2));

        ArrayList<Complex> complexes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            double real = Math.random() * 10;
            double imag = Math.random() * 10;
            complexes.add(new Complex(real, imag));
        }

        System.out.println("Unsorted");
        complexes.forEach(complex -> {
            System.out.printf("%s; Magnitude: %.3f%n", complex.toString(), complex.getMagnitude());
        });

        ct.sort(complexes);

        System.out.println("Sorted");
        complexes.forEach(complex -> {
            System.out.printf("%s; Magnitude: %.3f%n", complex.toString(), complex.getMagnitude());
        });

    }

    void sort(ArrayList<Complex> al) {
        for (int i = 0; i < al.size(); i++) {
            boolean done = true;
            for (int j = 0; j < al.size() - 1; j++) {
                if (al.get(j + 1).isLess(al.get(j))) {
                    swapArrayListIndices(al, j, j + 1);
                    done = false;
                }
            }
            if (done) break;
        }
    }

    void swapArrayListIndices(ArrayList<Complex> al, int i0, int i1) {
        Complex temp = al.get(i0);
        al.set(i0, al.get(i1));
        al.set(i1, temp);
    }
}
