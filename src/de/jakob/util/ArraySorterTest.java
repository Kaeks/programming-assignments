package de.jakob.util;

import java.util.Arrays;

public class ArraySorterTest {
    public static void main(String[] args) {
        int[] arr = new int[20];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 101);
        }
        ArraySorter as = new ArraySorter();
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(as.mergeSort(arr)));
    }
}
