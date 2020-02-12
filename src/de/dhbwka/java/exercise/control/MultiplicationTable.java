package de.dhbwka.java.exercise.control;

public class MultiplicationTable {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            String line = "";
            for (int j = 1; j <= 10; j++) {
                int result = i * j;
                line += String.format("%3d", result);
            }
            System.out.println(line);
        }
    }
}
