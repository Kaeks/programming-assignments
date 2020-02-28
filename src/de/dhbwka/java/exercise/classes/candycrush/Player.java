package de.dhbwka.java.exercise.classes.candycrush;

public class Player {

    private String name;
    private int points = 0;

    public Player(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

    public void addPoints(int amount) {
        points += amount;
    }

    @Override
    public String toString() {
        return String.format("Player \"%s\": %d points", name, points);
    }
}
