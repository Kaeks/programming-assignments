package de.dhbwka.java.exercise.classes.nimmspiel;

import java.util.Scanner;

public class NimmspielTest {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Player 1, enter your name!: ");
        String name0 = scan.nextLine();
        System.out.printf("Hi, %s!%n", name0);
        System.out.print("Player 2, enter your name!: ");
        String name1 = scan.nextLine();
        System.out.printf("Hi, %s!%n", name1);
        byte specificSizes = -1;
        while (specificSizes == -1) {
            System.out.print("Do you want piles with specific sizes? (Y/N) ");
            char c = scan.next().charAt(0);
            if (c == 'y' || c == 'Y') specificSizes = 1;
            else if (c == 'n' || c == 'N') specificSizes = 0;
            else System.out.println("xD");
        }
        Nimmspiel ns;
        if (specificSizes == 1) {
            int size0 = -1;
            int size1 = -1;
            boolean size0Valid = false;
            boolean size1Valid = false;
            while (!size0Valid) {
                System.out.print("Size for pile #1: ");
                if (scan.hasNextInt()) {
                    size0 = scan.nextInt();
                } else {
                    scan.next();
                    System.out.println("xD");
                }
                if (size0 > 0) size0Valid = true;
            }
            while (!size1Valid) {
                System.out.print("Size for pile #2: ");
                if (scan.hasNextInt()) {
                    size1 = scan.nextInt();
                } else {
                    scan.next();
                    System.out.println("xD");
                }
                if (size1 > 0) size1Valid = true;
            }
            ns = new Nimmspiel(name0, name1, size0, size1);
        } else {
            ns = new Nimmspiel(name0, name1);
        }
        int moves = 0;
        while (ns.getWinner() == null) {
            moves++;
            byte typedPile = -1;
            boolean pileValid = false;
            String playingName = ns.getPlaying().getName();
            System.out.printf("---Move #%d: %s---%n", moves, playingName);
            System.out.println(ns.toString());
            while (!pileValid) {
                System.out.printf("%s, choose a pile! (1/2) ", playingName);
                if (scan.hasNextByte()) {
                    typedPile = scan.nextByte();
                } else {
                    scan.next();
                    System.out.println("xD");
                    continue;
                }
                boolean selectedPileIsEmpty;
                if (typedPile == 1) {
                    selectedPileIsEmpty = ns.getPile0().isEmpty();
                } else if (typedPile == 2) {
                    selectedPileIsEmpty = ns.getPile1().isEmpty();
                } else {
                    continue;
                }
                if (selectedPileIsEmpty) {
                    System.out.printf("Pile #%d is empty!%n", typedPile);
                } else {
                    pileValid = true;
                }
            }

            int amount;
            boolean success = false;
            while (!success) {
                System.out.printf("%s, how many balls do you want to take from pile #%d? ", playingName, typedPile);
                amount = scan.nextInt();
                if (amount > 0) {
                    try {
                        ns.makeMove((byte) (typedPile - 1), amount);
                        success = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
        System.out.println("We have a winner!");
        System.out.printf("%s wins this match!", ns.getWinner().getName());
        scan.close();
    }
}
