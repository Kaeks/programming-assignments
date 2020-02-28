package de.dhbwka.java.exercise.strings;

import java.util.Scanner;

public class Palindrome {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Type something: ");
        String in = scan.nextLine();
        String out = getInvertedString(in);
        System.out.printf("'%s' inverted is '%s'. It is %sa palindrome.", in, out, isPalindrome(in, out) ? "" : "not ");
        scan.close();
    }

    private static String getInvertedString(String s) {
        String is = "";
        for (int i = s.length() - 1; i >= 0; i--) is += s.charAt(i);
        return is;
    }

    private static boolean isPalindrome(String s) {
        return isPalindrome(s, getInvertedString(s));
    }

    private static boolean isPalindrome(String s, String is) {
        return s.toLowerCase().equals(is.toLowerCase());
    }

}