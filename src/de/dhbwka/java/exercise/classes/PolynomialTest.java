package de.dhbwka.java.exercise.classes;

public class PolynomialTest {
    public static void main(String[] args) {
        Polynomial p1 = new Polynomial(2, 0, 0);
        Polynomial p2 = new Polynomial(0, -4, 1);
        Polynomial p3 = p1.add(p2);
        System.out.println("P3 = P1 + P2: " + p3.toString());
        Polynomial p4 = p3.multiply(2);
        System.out.println("P4 = 2 * P3: " + p4.toString());
        try {
            double[] zeroPointValues = p4.getZeroPoints();
            System.out.println("P4 (" + p4.toString() + ") has the following zero point values:");
            for (double zpv : zeroPointValues) {
                System.out.println(zpv);
            }
        } catch (Exception e) {
            System.out.println("P4 (" + p4.toString() + ") has no zero point values.");
        }
    }
}
