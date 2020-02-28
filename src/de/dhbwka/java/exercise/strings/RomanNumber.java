package de.dhbwka.java.exercise.strings;

import java.util.Scanner;

public class RomanNumber {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String in = "";
        while (!in.startsWith("yeet")) {
            System.out.print("Type a valid roman numeral: ");
            in = scan.nextLine();
            System.out.printf("Value: %d%n", calculateNumber(in));
        }
        scan.close();
    }

    private static int getNumeralValue(char c) {
        switch (c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return -1;
        }
    }

    private static int calculateNumber(String in) {
        int total = 0;
        for (int i = 0; i < in.length(); i++) {
            char c = in.charAt(i);
            int v = getNumeralValue(c);
            if (i + 1 < in.length()) {
                char nc = in.charAt(i + 1);
                int nv = getNumeralValue(nc);
                if (v >= nv) {
                    total += v;
                } else {
                    total += nv - v;
                    i++;
                }
            }
            else total += v;
        }
        return total;
    }

}