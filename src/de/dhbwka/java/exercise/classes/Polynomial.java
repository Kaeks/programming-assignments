package de.dhbwka.java.exercise.classes;

import java.util.Locale;

public class Polynomial {
    private double a;
    private double b;
    private double c;

    public Polynomial(double a) {
        this.a = a;
        b = 0;
        c = 0;
    }

    public Polynomial(double a, double b) {
        this.a = a;
        this.b = b;
        c = 0;
    }

    public Polynomial(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public String toString() {
        String aPart = a != 0 ? String.format(Locale.US, "%.1fx^2 ", a) : "";
        String bPart = b != 0 ? (b > 0 ? "+" : "-") + String.format(Locale.US, " %.1fx ", Math.abs(b)) : "";
        String cPart = c != 0 ? (c > 0 ? "+" : "-") + String.format(Locale.US, " %.1f", Math.abs(c)) : "";
        return aPart + bPart + cPart;
    }

    public double getF(double x) {
        return a * Math.pow(x, 2) + b * x + c;
    }

    public Polynomial add(Polynomial p) {
        return new Polynomial(a + p.a, b + p.b, c + p.c);
    }

    public Polynomial subtract(Polynomial p) {
        return add(p.multiply(-1));
    }

    public Polynomial multiply(double factor) {
        return new Polynomial(factor * a, factor * b, factor * c);
    }

    public double[] getZeroPoints() throws Exception {
        double p = b / a;
        double q = c / a;

        double v = Math.pow(p/2, 2) - q;

        if (v < 0) {
            throw new Exception("The polynomial has no zero point values.");
        }
        if (v == 0) {
            return new double[]{-p/2};
        }

        double w = Math.sqrt(v);
        return new double[]{-p/2 - w, -p/2 + w};
    }
}
