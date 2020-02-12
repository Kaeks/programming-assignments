package de.dhbwka.java.exercise.classes;

import java.util.Locale;

public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point point) {
        this.x = point.x;
        this.y = point.y;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }

    public String toString() {
        return String.format(Locale.US, "Point: x = %.3f, y = %.3f", this.x, this.y);
    }

    public double getDistance(Point point) {
        return Math.sqrt(Math.pow(point.x - this.x, 2) + Math.pow(point.y - this.y, 2));
    }

    public double getDistanceFromOrigin() {
        return this.getDistance(new Point(0, 0));
    }

    public Point mirrorX() {
        return new Point(this.x, -this.y);
    }

    public Point mirrorY() {
        return new Point(-this.x, this.y);
    }

    public Point mirrorOrigin() {
        return this.mirrorX().mirrorY();
    }
}
