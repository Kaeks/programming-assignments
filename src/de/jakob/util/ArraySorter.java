package de.jakob.util;

public class ArraySorter {

    public int[] mergeSort(int[] in) {
        if (in.length <= 1) return in;
        int[] l = new int[(int) Math.floor(in.length/2f)];
        int[] r = new int[(int) Math.ceil(in.length/2f)];
        if (l.length >= 0) System.arraycopy(in, 0, l, 0, l.length);
        if (r.length >= 0) System.arraycopy(in, l.length, r, 0, r.length);
        //System.out.print(Arrays.toString(l));
        //System.out.println(Arrays.toString(r));
        l = mergeSort(l);
        r = mergeSort(r);
        //System.out.print(Arrays.toString(l));
        //System.out.println(Arrays.toString(r));
        return innerMergeSort(l, r);
    }

    private int[] innerMergeSort(int[] l, int[] r) {
        int[] m = new int[l.length + r.length];
        int li = 0, ri = 0;
        int mi = 0;
        while (li < l.length || ri < r.length) {
            if (ri >= r.length || li < l.length && l[li] < r[ri]) {
                m[mi] = l[li];
                li++;
            } else {
                m[mi] = r[ri];
                ri++;
            }
            mi++;
        }
        return m;
    }

    public int[] selectionSort(int[] in) {
        int smallest = in[0];
        int smallestIndex = 0;
        for (int i = 0; i < in.length; i++) {
            for (int j = i + 1; j < in.length; j++) {
                if (in[j] < smallest) {
                    smallest = in[j];
                    smallestIndex = j;
                }
            }
            swapIndices(in, i, smallestIndex);
        }
        return in;
    }

    public int[] insertionSort(int[] in) {
        for (int i = 0; i < in.length - 1; i++) {
            int selected = in[i];
            for (int j = i + 1; j < in.length; j++) {
                if (selected > in[j]) {
                    swapIndices(in, i, j);
                }
            }
        }
        return in;
    }

    public int[] bubbleSort(int[] in) {
        for (int i = 0; i < in.length; i++) {
            boolean swapped = false;
            for (int j = 0; j < in.length; j++) {
                if (in[j] > in[j + 1]) {
                    swapIndices(in, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
        return in;
    }

    private void swapIndices(int[] in, int id0, int id1) {
        int temp = in[id0];
        in[id0] = in[id1];
        in[id1] = temp;
    }
}
