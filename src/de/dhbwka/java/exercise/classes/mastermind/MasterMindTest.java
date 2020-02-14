package de.dhbwka.java.exercise.classes.mastermind;

import java.util.Scanner;

public class MasterMindTest {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        MasterMind mm = new MasterMind();
        boolean gaming = true;
        while (gaming) {
            System.out.println("---The Mastermind challenges you---");
            System.out.println("I am thinking...");
            mm.think();
            boolean guessing = true;
            while (guessing) {
                System.out.print("Scholar, take a guess!: ");

            }
        }
    }

    char[] decipher(String input) {
        char[] arr = new char[input.length()];
        return arr;
    }
}
