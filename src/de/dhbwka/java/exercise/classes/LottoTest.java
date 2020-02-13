package de.dhbwka.java.exercise.classes;

import de.jakob.util.ArraySorter;

import java.util.Arrays;
import java.util.Scanner;

public class LottoTest {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArraySorter as = new ArraySorter();
        Lotto deutschesLotto = new Lotto(6, 49);
        System.out.printf("Wilkommen zu Lotto %d aus %d%n", deutschesLotto.getPull(), deutschesLotto.getPool());
        for (int i = 0; i < deutschesLotto.getPull(); i++) {
            boolean valid = false;
            while (!valid) {
                System.out.printf("Geben sie Tipp #%d ein: ", i + 1);
                int guess;
                if (scan.hasNextInt()) {
                    guess = scan.nextInt();
                } else {
                    scan.next();
                    continue;
                }
                try {
                    deutschesLotto.addGuess(guess);
                    valid = true;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        System.out.printf("Ihre getippten Werte:%n%s%n", Arrays.toString(as.mergeSort(deutschesLotto.getGuess())));
        deutschesLotto.determinePulled();
        System.out.println(deutschesLotto.toString());
    }
}
