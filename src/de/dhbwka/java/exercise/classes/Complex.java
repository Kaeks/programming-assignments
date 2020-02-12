package de.dhbwka.java.exercise.classes;

import java.util.Locale;

public class Complex {
    double real;
    double imag;

    public Complex() {
        real = 0;
        imag = 0;
    }

    public Complex(double real) {
        this.real = real;
        imag = 0;
    }

    public Complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public double getImag() {
        return imag;
    }

    public double getReal() {
        return real;
    }

    public Complex add(Complex c) {
        return new Complex(real + c.real, imag + c.imag);
    }

    public Complex sub(Complex c) {
        return new Complex(real - c.real, imag + c.imag);
    }

    public Complex mult(Complex c) {
        return new Complex(real * c.real - imag * c.imag, real * c.imag + imag * c.real);
    }

    public Complex div(Complex c) {
        double nen = Math.pow(c.real,2) + Math.pow(c.imag, 2);
        return new Complex((real * c.real + imag * c.imag) / nen, (imag * c.real - real * c.imag) / nen);
    }

    public String toString() {
        return String.format(Locale.US, "%.3f " + (imag > 0 ? "+" : "-") + " %.3fi", real, imag);
    }

    public double getMagnitude() {
        return Math.sqrt(Math.pow(real, 2) + Math.pow(imag, 2));
    }

    public boolean isLess(Complex c) {
        return getMagnitude() < c.getMagnitude();
    }
}
