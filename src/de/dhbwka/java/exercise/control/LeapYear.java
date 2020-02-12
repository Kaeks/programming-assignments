package de.dhbwka.java.exercise.control;

import java.util.Scanner;

public class LeapYear {
    public static void main(String[] args) {
        boolean doLoop = false;
        for (String arg : args) {
            if (arg.equals("loop")) {
                doLoop = true;
                break;
            }
        }

        LeapYear leapYear = new LeapYear();

        if (doLoop) {
            while (true) {
                leapYear.asker();
            }
        } else {
            leapYear.asker();
        }

    }

    void asker() {
        System.out.print("Which year? ");
        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();
        System.out.println(year % 4 == 0 && (year % 100 != 0 || year % 400 == 0) ? "Yes" : "No");
        scanner.close();
    }

}
