package de.dhbwka.java.exercise.classes.nimmspiel;

public class Pile {
    private int balls;

    Pile(int balls) {
        if (balls < 0) throw new IllegalArgumentException(String.format("Amount of balls cannot be below 0 (%d).", balls));
        this.balls = balls;
    }

    public int getBalls() {
        return balls;
    }

    void takeBalls(int amount) {
        if (amount > balls) {
            throw new IllegalArgumentException(String.format("Can't take more balls (%d) than what is on the pile (%d).", amount, balls));
        }
        balls -= amount;
    }

    boolean isEmpty() {
        return balls == 0;
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < balls; i++) {
            s += "o";
        }
        return s;
    }
}
