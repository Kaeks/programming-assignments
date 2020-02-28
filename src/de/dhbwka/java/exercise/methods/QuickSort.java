package de.dhbwka.java.exercise.methods;

import java.util.Arrays;
import java.util.Scanner;

public class QuickSort {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("LENGTH??: ");
        int l = scan.nextInt();
        int[] arr = new int[l];
        for (int i = 0; i < l; i++) {
            System.out.printf("VALUE #%d???: ", i + 1);
            arr[i] = scan.nextInt();
        }
        System.out.printf("B4: %s%n", Arrays.toString(arr));
        quickSort(arr);
        System.out.printf("A4: %s%n", Arrays.toString(arr));
        scan.close();
    }

    private static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int l, int r) {
        if (l < r) {
            int pivotIndex = getPivotIndex(arr, l, r);
            quickSort(arr, l, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, r);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static int getPivotIndex(int[] arr, int l, int r) {
        int i = l, j = r - 1, pivot = arr[r];
        do {
            while (arr[i] <= pivot && i < r) i++;
            while (arr[j] >= pivot && j > l) j--;
            if (i < j) swap(arr, i, j);
        } while (i < j);
        if (arr[i] > pivot) swap(arr, i, r);
        return i;
    }
}