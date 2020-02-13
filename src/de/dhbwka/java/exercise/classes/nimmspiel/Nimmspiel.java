package de.dhbwka.java.exercise.classes.nimmspiel;

import java.util.Scanner;

public class Nimmspiel {
    private Player plr0;
    private Player plr1;

    private Pile pile0;
    private Pile pile1;

    private Player playing;
    private Player winner;

    public Nimmspiel(String plr0name, String plr1name) {
        plr0 = new Player(plr0name);
        plr1 = new Player(plr1name);
        pile0 = new Pile((int) (Math.random() * 10 + 1));
        pile1 = new Pile((int) (Math.random() * 10 + 1));
        playing = plr0;
    }

    public Nimmspiel(String plr0name, String plr1name, int balls0, int balls1) {
        plr0 = new Player(plr0name);
        plr1 = new Player(plr1name);
        pile0 = new Pile(balls0);
        pile1 = new Pile(balls1);
        playing = plr0;
    }

    public String toString() {
        return String.format(
                "Nimmspiel%n"
                + "Players: %s and %s%n"
                + "Pile 1: %s%n"
                + "Pile 2: %s%n",
                plr0.getName(), plr1.getName(), pile0.toString(), pile1.toString()
        );
    }

    public Pile getPile0() {
        return pile0;
    }

    public Pile getPile1() {
        return pile1;
    }

    public Player getPlaying() {
        return playing;
    }

    private void switchPlaying() {
        if (playing == plr0) playing = plr1;
        else if (playing == plr1) playing = plr0;
    }

    private boolean checkWin() {
        return pile0.isEmpty() && pile1.isEmpty();
    }

    public Player getWinner() {
        return winner;
    }

    public void makeMove(byte pileNo, int taken) {
        Pile pile;
        switch (pileNo) {
            case 0: pile = pile0; break;
            case 1: pile = pile1; break;
            default: throw new IllegalArgumentException("No such pile: " + pileNo);
        }
        pile.takeBalls(taken);
        if (checkWin()) {
            winner = playing;
            return;
        }
        switchPlaying();
    }
}
