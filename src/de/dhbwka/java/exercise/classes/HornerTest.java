package de.dhbwka.java.exercise.classes;

public class HornerTest {
    public static void main(String[] args) {
        Horner f = new Horner(1.0, -2.0, 3.0, 4.5, 8.0, -10.0, 3.0, 4.0, 2.0, -3.0, 0.5);
        System.out.println("Polynomial f: " + f.toString());
        System.out.println("f(1.5) = " + f.getValue(1.5));
        Horner g = new Horner(5.8, 3.2, 5.7, 6.5, 2.8, 1.33, 7.77, 2.22, 9.11, -4.0);
        System.out.println("Polynomial g: " + g.toString());
        System.out.println("g(0.375) = " + g.getValue(0.375));
        Horner h = new Horner(10);
        h.fillRandomly(-10, 10);
        System.out.println("Polynomial h: " + h.toString());
        System.out.println("h(-3.5) = " + h.getValue(-3.5));
    }
}
