package de.dhbwka.java.exercise.datatypes;

public class Round {
    public static void main(String[] args) {
        Round r = new Round();

        final double PI   = 3.1415926;
        final double E    = 2.7182818;
        double nPi     = PI * -1;
        double nE      = E * -1;

        System.out.println("Original");
        System.out.println(PI);
        System.out.println(E);

        System.out.println("Rounded");
        System.out.println(r.round(PI));
        System.out.println(r.round(E));

        System.out.println("Negative");
        System.out.println(nPi);
        System.out.println(nE);

        System.out.println("Rounded Negative");
        System.out.println(r.round(nPi));
        System.out.println(r.round(nE));

        System.out.println("Better Rounded");
        System.out.println(r.betterRound(PI));
        System.out.println(r.betterRound(E));

        System.out.println("Better Rounded Negative");
        System.out.println(r.betterRound(nPi));
        System.out.println(r.betterRound(nE));
    }

    int round(double value) {
        return (int) (value + 0.5);
    }

    int betterRound(double value) {
        return (int) (value + (value >= 0 ? + 0.5 : - 0.5));
    }
}
