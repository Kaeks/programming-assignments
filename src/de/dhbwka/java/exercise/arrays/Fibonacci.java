package de.dhbwka.java.exercise.arrays;

import java.util.Arrays;
import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        Fibonacci f = new Fibonacci();
        Scanner scan = new Scanner(System.in);
        System.out.printf("n: ");
        int n = scan.nextInt();
        System.out.println(Arrays.toString(f.getFibonacciArray(n)));
    }

    long[] getFibonacciArray(int n) {
        long[] arr = new long[n];
        long cur = 1;
        long prev = 1;
        arr[0] = prev;
        arr[1] = cur;
        for (int i = 2; i < n; i++) {
            long temp = cur;
            cur += prev;
            prev = temp;
            arr[i] = cur;
        }
        return arr;
    }
}
