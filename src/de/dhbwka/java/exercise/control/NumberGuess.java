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
            int attempts = 0;

            while (guessedNumber != randomNumber) {
                System.out.printf("%s, guess a number [1-100]: ", name);
                guessedNumber = scan.nextInt();
                attempts++;
                String s = guessedNumber > randomNumber ? "too high" : guessedNumber < randomNumber ? "too low" : "correct";
                System.out.printf("Attempt #%d: %d is %s.%n", attempts, guessedNumber, s);
            }
            int cont = -1;
            while (cont == -1) {
                System.out.printf("Continue?%n0 - Yes%n1 - No%n");
                cont = scan.nextInt();
                if (cont != 0 && cont != 1) {
                    System.out.println("Bruh.");
                    cont = -1;
                }
            }
            if (cont == 1) exit = true;
        }
        scan.close();
    }
}
