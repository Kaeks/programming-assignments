package de.dhbwka.java.exercise.arrays;

import java.util.Arrays;
import java.util.Scanner;

public class Eratostenes {
    public static void main(String[] args) {
        Eratostenes e = new Eratostenes();
        Scanner scan = new Scanner(System.in);
        System.out.print("n: ");
        int n = scan.nextInt();
        int[] primes = e.getPrimes(n);
        System.out.println(Arrays.toString(primes));
    }

    int[] getPrimes(int n) {
        int[] sieve = getFilledNumberArray(n + 1);
        boolean[] bools = getFilledBooleanArray(n + 1, true);
        int[] primes = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            if (!bools[i]) continue;

            // "push" to primes array
            for (int j = 0; j < primes.length; j++) {
                if (primes[j] == 0) {
                    primes[j] = sieve[i];
                    break;
                }
            }

            for (int j = 2, multiple = i; i * j <= n; j++) {
                multiple = i * j;
                bools[multiple] = false;
            }
        }
        return primes;
    }

    int[] getFilledNumberArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        return arr;
    }

    boolean[] getFilledBooleanArray(int length, boolean val) {
        boolean[] arr = new boolean[length];
        for (int i = 0; i < length; i++) {
            arr[i] = val;
        }
        return arr;
    }
}