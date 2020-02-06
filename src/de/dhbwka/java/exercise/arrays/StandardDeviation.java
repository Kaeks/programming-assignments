package de.dhbwka.java.exercise.arrays;

public class StandardDeviation {
    public static void main(String[] args) {
        StandardDeviation sd = new StandardDeviation();
        int[] arr = sd.getRandomArray(100);
        double avg = sd.getAverage(arr);
        System.out.printf("Average: %.2f%n", avg);
        double dev = sd.getStandardDeviation(arr);
        System.out.printf("Standard deviation: %f%n", dev);

    }

    int[] getRandomArray(int length) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * 11);
        }
        return arr;
    }

    double getAverage(int[] arr) {
        double value = 0;
        for (int el: arr) value += el;
        return (1d / arr.length) * value;
    }

    double getStandardDeviation(int[] arr) {
        double value = 0;
        int n = arr.length;
        double avg = this.getAverage(arr);
        for (int el: arr) {
            value += Math.pow(el - avg, 2);
        }
        return Math.sqrt((1d / (n - 1)) * value);
    }
}