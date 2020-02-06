package de.dhbwka.java.exercise.control;

import java.util.Scanner;

public class NumberGuess {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Name?: ");
        String name = scan.nextLine();

        boolean exit = false;
        while (!exit) {
            int randomNumber = (int) (Math.random() * 100 + 1);
            int guessedNumber = -1;
            int tries = 0;

            while (guessedNumber != randomNumber) {
                System.out.printf("%s, guess a number [1-100]: ", name);
                guessedNumber = scan.nextInt();
                tries++;
                String s = guessedNumber > randomNumber ? "too high" : guessedNumber < randomNumber ? "too low" : "correct";
                System.out.printf("Attempt #%d: %d is %s.%n", tries, guessedNumber, s);
            }
            while (true) {
                System.out.printf("Continue?%n0 - Yes%n1 - No%n");
                int continueNum = scan.nextInt();
                if (continueNum == 0) break;
                else if (continueNum == 1) {
                    exit = true;
                    break;
                }
            }
        }
        scan.close();
    }
}
