package de.dhbwka.java.exercise.strings;

import java.util.Scanner;

public class CrossTotal {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean valid;
        String input = "";
        do {
            System.out.print("Type in a number to calculate the cross total of: ");
            valid = true;
            input = scan.nextLine();
            for (char c : input.toCharArray()) {
                if (!isNumber(c)) {
                    System.out.println("Invalid.");
                    valid = false;
                    break;
                }
            }
        } while (!valid);
        System.out.printf("Cross total of %s: %d", input, calculate(input));
        scan.close();
    }

    private static boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    private static int calculate(String in) {
        int total = 0;
        for (char c : in.toCharArray()) {
            total += Character.getNumericValue(c);
        }
        return total;
    }

}