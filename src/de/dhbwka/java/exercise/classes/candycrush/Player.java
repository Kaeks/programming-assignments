package de.dhbwka.java.exercise.classes.candycrush;

public class Player {

    private int points = 0;

    public Player() {
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int amount) {
        points += amount;
    }

    @Override
    public String toString() {
        return String.format("Player: %d points", points);
    }
}
