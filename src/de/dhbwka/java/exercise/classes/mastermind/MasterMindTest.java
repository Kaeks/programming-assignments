package de.dhbwka.java.exercise.classes.mastermind;

import java.util.ArrayList;
import java.util.Locale;
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
            System.out.println("Try to guess what I am thinking about!");
            System.out.printf("My thought contains %d places with the letters from %s to %s.%n", mm.getOptions(), mm.getMin(), mm.getMax());
            System.out.printf(
                Locale.US,
                "You have %d attempts, and thus only a %.2f%% chance of guessing right!%n",
                mm.getMaxTrials(),
                100 * mm.getMaxTrials() / Math.pow((int) (mm.getMax() - mm.getMin() + 1), mm.getOptions())
            );
            boolean guessing = true;
            while (guessing) {
                System.out.print("Scholar, take a guess!: ");
                String guess = scan.nextLine();
                char[] deciphered = guess.toCharArray();;
                if (mm.checkValidInput(deciphered)) {
                    boolean success = mm.attempt(deciphered);
                    ArrayList<Trial> previousTrials = mm.getPreviousTrials();
                    printPreviousTrials(previousTrials);

                    if (success) {
                        System.out.printf("Arrghh! I lost in %d of your attempts!%n", mm.getTrials());
                        guessing = false;
                    } else if (mm.getTrials() == mm.getMaxTrials()) {
                        System.out.println("Pathetic. I win!");
                        guessing = false;
                    }
                } else {
                    System.out.println("Invalid guess!");
                }
            }
            mm.reset();
        }
        scan.close();
    }

    static void printPreviousTrials(ArrayList<Trial> previousTrials) {
        for (Trial t : previousTrials) {
            for (char c : t.getValues()) {
                System.out.print(c);
            }
            System.out.printf(" %2d %2d%n", t.getCorrectPlaces(), t.getWrongPlaces());
        }
    }
}
