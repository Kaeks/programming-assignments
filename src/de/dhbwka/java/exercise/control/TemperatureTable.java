package de.dhbwka.java.exercise.control;

public class TemperatureTable {
    public static void main(String[] args) {
        TemperatureTable tt = new TemperatureTable();
        System.out.println("  °F  |  °C   " );
        System.out.println("------+-------");
        for (int f = 0; f <= 300; f++) {
            float c = (5f/9)*(f-32);
            float roundedC = Math.round(c * 10) / 10f;
            System.out.println(tt.padNumber(f, 5) + " | " + tt.padNumber(roundedC, 5));
        }
    }

    private String padNumber(float number, int length) {
        int numLength = number == 0 ? 0 : (int) Math.log10(number);
        numLength += 2;                 // Add 2 due to decimal point and a single decimal
        if (number < 0) numLength++;    // Add 1 for negative numbers
        String ting = "";
        for (int i = 0; i < length - numLength - 1; i++) {
            ting += " ";
        }
        return ting + number;
    }
}
