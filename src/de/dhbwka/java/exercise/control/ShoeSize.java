package de.dhbwka.java.exercise.control;

public class ShoeSize {
    public static void main(String[] args) {
        System.out.println("Centimeters   | Size ");
        System.out.println("--------------+------");

        int size = 30;
        while (size <= 49) {
            double minCm = (size - 1) / 1.5;
            double maxCm = size / 1.5;
            System.out.printf("%.2f - %.2f | %d%n", minCm, maxCm, size);
            size++;
        }
    }
}
