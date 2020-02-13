package de.dhbwka.java.exercise.classes;

public class Horner {
    private int degree;
    private double[] values;

    public Horner(double... values) {
        degree = values.length;
        this.values = values;
    }

    public Horner(int degree) {
        this.degree = degree;
        values = new double[degree];
    }

    void setValue(int key, double val) {
        values[key] = val;
    }

    void fillRandomly(double min, double max) {
        for (int i = 0; i < degree; i++) {
            setValue(i, Math.random() * (max - min) + min);
        }
    }

    double getValue(double x) {
        double result = values[degree - 1];
        for (int i = degree - 2; i >= 0; i--) {
            result = result * x + values[i];
        }
        return result;
    }

    public String toString() {
        String s = "";
        for (int i = degree - 1; i >= 0; i--) {
            double val = values[i];
            if (val == 0) continue;
            if (i == degree - 1) {
                s += String.format("%.2f*x^%d", val, i);
            } else {
                s += String.format(" " + (val > 0 ? "+" : "-") + " %.2f*x^%d", Math.abs(val), i);
            }
        }
        return s;
    }
}
