package de.dhbwka.java.exercise.control;

import java.util.Scanner;

public class AddUp {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int mode = -1;
        do {
            System.out.println("Please choose a mode");
            System.out.println("0 - While");
            System.out.println("1 - Do While");
            mode = scan.nextInt();
        } while (mode != 0 && mode != 1);

        int cumulative = 0;

        int newNum = 0;

        if (mode == 0) {
            while (newNum >= 0) {
                System.out.print("Type a number (exit: < 0): ");
                newNum = scan.nextInt();
                if (newNum < 0) {
                    System.out.println("Result: " + cumulative);
                    continue;
                }
                cumulative += newNum;
            }
        } else if (mode == 1) {
            do {
                System.out.print("Type a number (exit: < 0): ");
                newNum = scan.nextInt();
                if (newNum < 0) {
                    System.out.println("Result: " + cumulative);
                    break;
                }
                cumulative += newNum;
            } while (newNum >= 0);
        }
        scan.close();
    }
}
