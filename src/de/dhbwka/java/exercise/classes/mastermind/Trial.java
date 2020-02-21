package de.dhbwka.java.exercise.classes.mastermind;

public class Trial {

    private char[] values;
    private int correctPlaces = -1;
    private int wrongPlaces = -1;

    public Trial(char[] values) {
        this.values = values;
    }

    public char[] getValues() {
        return values;
    }

    public int getCorrectPlaces() {
        return correctPlaces;
    }

    public int getWrongPlaces() {
        return wrongPlaces;
    }

    private boolean[] getCorrectPlacesBool(char[] thought) {
        boolean[] correctCorrect = new boolean[values.length];
        for (int i = 0; i < values.length; i++) {
            if (thought[i] == values[i]) correctCorrect[i] = true;
        }
        return correctCorrect;
    }

    public void determineCorrectPlaces(char[] thought) {
        int amt = 0;
        for (boolean val : getCorrectPlacesBool(thought)) if (val) amt++;
        correctPlaces = amt;
    }

    public void determineWrongPlaces(char[] thought) {
        boolean[] checkedT = new boolean[thought.length];
        boolean[] checkedV = new boolean[values.length];
        boolean[] correctCorrect = getCorrectPlacesBool(thought);

        int amt = 0;

        for (int ti = 0; ti < thought.length; ti++) {
            // Skip entries that are correct in the correct places
            if (correctCorrect[ti]) continue;
            // Skip entries that have already been matched
            if (checkedT[ti]) continue;
            for (int vi = 0; vi < values.length; vi++) {
                // Skip entries that are correct in the correct places
                if (correctCorrect[vi]) continue;
                // Skip entries that have already been matched
                if (checkedV[vi]) continue;
                if (thought[ti] == values[vi]) {
                    checkedT[ti] = true;
                    checkedV[vi] = true;
                    amt++;
                }
            }
        }
        wrongPlaces = amt;
    }
}
