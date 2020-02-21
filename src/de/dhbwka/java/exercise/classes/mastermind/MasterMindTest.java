package de.dhbwka.java.exercise.classes.mastermind;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import de.jakob.util.CharConverter;

public class MasterMindTest {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        MasterMind mm = new MasterMind('A', 'C', 3, 10);
        CharConverter cc = new CharConverter();
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
            boolean taunted = false;
            byte result = -1;
            while (guessing) {
                System.out.print("Challenger, take a guess!: ");
                String guess = scan.nextLine();
                char[] deciphered = guess.toCharArray();;
                if (mm.checkValidInput(deciphered)) {
                    boolean success = mm.attempt(deciphered);
                    ArrayList<Trial> previousTrials = mm.getPreviousTrials();
                    printPreviousTrials(previousTrials);
                    if (success) {
                        System.out.printf("Arrghh! I lost in %d of your attempts!%n", mm.getTrials());
                        guessing = false;
                        result = 1;
                    } else {
                        if (!taunted && mm.getTrials() > mm.getMaxTrials() / 2) {
                            System.out.println("Come on, try harder!");
                            taunted = true;
                        }
                        if (mm.getTrials() == mm.getMaxTrials()) {
                            System.out.println("I win! Bring me a real challenge...");
                            guessing = false;
                            result = 0;
                        }
                    }
                } else {
                    System.out.println("Invalid guess!");
                }
            }
            boolean resetQuery = true;
            while (resetQuery) {
                if (result == 0) System.out.print("Do you want to try beating me again? Hint: you won't succeed next time either.");
                else if (result == 1) System.out.print("I want a rematch!");
                System.out.print(" (Y/N): ");
                String scanReset = "";
                scanReset = scan.nextLine();
                if (scanReset.isEmpty()) {
                    System.out.println("bro bitte");
                    continue;
                }
                switch (cc.getUcIfLetter(scanReset.charAt(0))) {
                    case 'Y': mm.reset(); resetQuery = false; break;
                    case 'N': gaming = false; resetQuery = false; break;
                    default: System.out.println("très funny");
                }
            }
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
