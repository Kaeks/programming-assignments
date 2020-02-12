package de.dhbwka.java.exercise.control;

import java.util.Locale;

public class TemperatureTable {
    public static void main(String[] args) {
        TemperatureTable tt = new TemperatureTable();
        System.out.println("  °F  |  °C   " );
        System.out.println("------+-------");
        for (int f = 0; f <= 300; f++) {
            float c = (5f/9)*(f-32);
            System.out.printf(Locale.US, "%5d | %5.1f%n", f, c);
        }
    }
}
