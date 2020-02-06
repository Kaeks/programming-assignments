package de.dhbwka.java.exercise.control;

public class MultiplicationTable {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            String line = "";
            for (int j = 1; j <= 10; j++) {
                int result = i * j;
                line += pad(result, 3);
            }
            System.out.println(line);
        }
    }

    static String pad(int num, int len) {
        String s = "";
        for (int i = 0; i < len - Math.floor(Math.log10(num)); i++) {
            s += " ";
        }
        return s + num;
    }
}
