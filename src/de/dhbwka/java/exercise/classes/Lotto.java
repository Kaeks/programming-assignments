package de.dhbwka.java.exercise.classes;

import de.jakob.util.ArraySorter;

import java.util.Arrays;

public class Lotto {
    private int pull;
    private int pool;
    private int[] guess;
    private int[] pulled;

    Lotto(int pull, int pool) {
        this.pull = pull;
        this.pool = pool;
        guess = new int[pull];
        Arrays.fill(guess, -1);
        pulled = new int[pull];
        Arrays.fill(pulled, -1);
    }

    int getPull() {
        return pull;
    }

    int getPool() {
        return pool;
    }

    void addGuess(int singularGuess) {
        if (singularGuess > pool || singularGuess < 1) {
            throw new IllegalArgumentException(String.format("Guess (%d) must be in range (1-%d).", singularGuess, pool));
        }
        // See whether or not the specific number has been guessed already
        boolean guessed = false;
        for (int value : guess) {
            if (value == singularGuess) {
                guessed = true;
                break;
            }
        }
        if (guessed) {
            throw new IllegalArgumentException(String.format("Cannot add the same number (%d) to the guess twice.", singularGuess));
        }
        // Find the last unoccupied (-1) position in the guess array
        int index = -1;
        for (int i = 0; i < guess.length; i++) {
            if (guess[i] < 1) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new ArrayIndexOutOfBoundsException(String.format("Cannot add any more guesses, limit (%d) has been reached.", pull));
        }
        guess[index] = singularGuess;
    }

    int[] getGuess() {
        return guess;
    }

    void determinePulled() {
        for (int i = 0; i < pull; i++) {
            boolean valid = true;
            do {
                int rand = (int) (Math.random() * 49 + 1);
                for (int j = 0; j < i; j++) {
                    if (rand == pulled[j]) {
                        valid = false;
                        break;
                    }
                }
                if (valid) pulled[i] = rand;
            } while (!valid);
        }
    }

    private int getMatches() {
        int matches = 0;
        for (int sinGuess : guess) {
            for (int sinPulled : pulled) {
                if (sinGuess == sinPulled) matches++;
            }
        }
        return matches;
    }

    private boolean guessed() {
        for (int val : guess) if (val == -1) return false;
        return true;
    }

    private boolean pulled() {
        for (int val : pulled) if (val == -1) return false;
        return true;
    }

    public String toString() {
        ArraySorter as = new ArraySorter();
        return String.format(
                "---Lotto %d/%d---%n"
                + (guessed() ? "Guess:  %s" : "Not guessed yet.") + "%n"
                + (pulled() ? "Pulled: %s" : "Not pulled yet.")
                + (guessed() && pulled() ? "%nMatches: %d" : ""),
                pull, pool,
                Arrays.toString(as.mergeSort(guess)),
                Arrays.toString(as.mergeSort(pulled)),
                getMatches()
        );
    }
}
