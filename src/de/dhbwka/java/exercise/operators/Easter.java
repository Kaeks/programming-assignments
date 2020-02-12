package de.dhbwka.java.exercise.operators;

import java.util.Scanner;

public class Easter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Easter easter = new Easter();

        System.out.print("Enter a year: ");

        int year = scanner.nextInt();

        System.out.println(easter.parseMessage(year));
        scanner.close();
    }

    int calculate(int year) {
        int a = year % 19;
        int b = year % 4;
        int c = year % 7;
        int k = year / 100;
        int p = (8 * k + 13) / 25;
        int q = k / 4;
        int m = (15 + k - p - q) % 30;
        int n = (4 + k - q) % 7;
        int d = (19 * a + m) % 30;
        int e = (2 * b + 4 * c + 6 * d + n) % 7;
        return (22 + d + e);
    }

    String parseDay(int day) {
        int realDay = day > 31 ? day - 31 : day;
        String month =  day > 31 ? "April" : "March";
        String blubb = "th'st'rd";
        return realDay + blubb + " of " + month;
    }

    String parseMessage(int year) {
        int day = calculate(year);
        return "The year " + year + "'s easter will be on the " + parseDay(day) + ".";
    }

}
