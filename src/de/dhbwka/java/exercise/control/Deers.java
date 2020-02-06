package de.dhbwka.java.exercise.control;

public class Deers {
    public static void main(String[] args) {
        int deer = 200;
        double increaseRate = 0.1;
        int i = 0;
        System.out.println("oh deer");
        while (deer < 300) {
            i++;
            deer = (int) (deer * (1 + increaseRate) - 15);
            System.out.println(i + ": " + deer + " deer");
        }
    }
}
