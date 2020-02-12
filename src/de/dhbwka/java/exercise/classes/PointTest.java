package de.dhbwka.java.exercise.classes;

public class PointTest {
    public static void main(String[] args) {
        Point pointA = new Point(4.0, 2.0);
        System.out.println("A: " + pointA);
        Point pointB = new Point(-1.0, -1.0);
        System.out.println("B: " + pointB);
        System.out.println("Distance A-B: " + pointA.getDistance(pointB));
        Point pointADash = pointA.mirrorOrigin();
        System.out.println("A': " + pointADash);
        System.out.println("Distance A’-B: " + pointADash.getDistance(pointB));
    }
}
