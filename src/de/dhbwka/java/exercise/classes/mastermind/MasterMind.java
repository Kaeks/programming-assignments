package de.dhbwka.java.exercise.classes.mastermind;

import de.jakob.util.CharConverter;

import java.util.ArrayList;

public class MasterMind {

    private CharConverter cc = new CharConverter();

    private int maxTrials;
    private int trials;
    private char min;
    private char max;
    private int options;
    private char[] thought;
    private ArrayList<Trial> previousTrials;

    public MasterMind() {
        min = 'A';
        max = 'H';
        options = 5;
        maxTrials = 20;
        previousTrials = new ArrayList<>();
    }

    public MasterMind(char min, char max, int options) {
        this.min = min;
        this.max = max;
        this.options = options;
        maxTrials = 20;
        previousTrials = new ArrayList<>();
    }

    public MasterMind(char min, char max, int options, int maxTrials) {
        this.min = min;
        this.max = max;
        this.options = options;
        this.maxTrials = maxTrials;
        previousTrials = new ArrayList<>();
    }

    void reset() {
        trials = 0;
        previousTrials = new ArrayList<>();
    }

    void think() {
        thought = new char[options];
        for (int i = 0; i < options; i++) {
            thought[i] = getRandomChar();
        }
    }

    public boolean checkValidInput(char[] in) {
        
        for (int i = 0; i < in.length; i++) {
            try {
            in[i] = cc.getUcIfLetter(in[i]); 
            } catch (IllegalArgumentException iae) {
                return false;
            }
        }

        if (in.length != options) return false;
        for (char c : in) {
            if (c > max || c < min) return false;
        } 
        return true;
    }

    public boolean attempt(char[] guess) {
        CharConverter cc = new CharConverter();
        char[] convertedGuess = new char[options];
        for (int i = 0; i < options; i++) {
            convertedGuess[i] = cc.getUcIfLetter(guess[i]);
        }
        Trial trial = new Trial(convertedGuess);
        trial.determineCorrectPlaces(thought);
        trial.determineWrongPlaces(thought);
        int correct = trial.getCorrectPlaces();
        trials++;
        previousTrials.add(trial);
        return correct == options;
    }

    private char getRandomChar() {
        return (char) (min + (int) (Math.random() * (max - min + 1)));
    }

    public ArrayList<Trial> getPreviousTrials() {
        return previousTrials;
    }

    public int getTrials() {
        return trials;
    }

    public char getMin() {
        return min;
    }

    public char getMax() {
        return max;
    }

    public int getMaxTrials() {
        return maxTrials;
    }

    public int getOptions() {
        return options;
    }
}
