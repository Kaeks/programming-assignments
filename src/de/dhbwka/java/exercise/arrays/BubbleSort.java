package de.dhbwka.java.exercise.arrays;

import java.util.Arrays;
import java.util.Scanner;

public class BubbleSort {
    public static void main(String[] args) {
        BubbleSort bs = new BubbleSort();
        Scanner scan = new Scanner(System.in);
        System.out.print("Length of random array: ");
        int n = scan.nextInt();
        int method = -1;
        while (method == -1) {
            System.out.print("Do you want to generate random values? (y/n): ");
            char c = scan.next().charAt(0);
            switch (c) {
                case 'y': method = 0; break;
                case 'n': method = 1; break;
                default:
                    System.out.println("do you honestly think you're f###ing funny?");
            }
        }
        int[] arr = new int[n];
        switch (method) {
            case 0:
                System.out.print("Minimum value: ");
                int min = scan.nextInt();
                System.out.print("Maximum value: ");
                int max = scan.nextInt();
                arr = bs.getRandomNumberArray(n, min, max);
                break;
            case 1:
                for (int i = 0; i < n; i++) {
                    System.out.printf("Value %d: ", i);
                    arr[i] = scan.nextInt();
                }
                break;
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(bs.sort(arr)));
        scan.close();
    }

    int[] getRandomNumberArray(int length, int min, int max) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * (max - min) + min);
        }
        return arr;
    }

    int[] sort(int[] in) {
        for (int i = 0; i < in.length; i++) {
            boolean done = true;
            for (int j = 0; j < in.length - 1; j++) {
                if (in[j] > in[j + 1]) {
                    this.swapArrayIndices(in, j, j + 1);
                    done = false;
                }
            }
            if (done) break;
        }
        return in;
    }

    void swapArrayIndices(int[] arr, int id0, int id1) {
        int temp = arr[id0];
        arr[id0] = arr[id1];
        arr[id1] = temp;
    }
}
