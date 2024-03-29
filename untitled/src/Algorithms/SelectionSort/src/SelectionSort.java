package Algorithms.SelectionSort.src;

import java.util.Arrays;
import java.util.Optional;

/**
 * The algorithm repeatedly selects the smallest (or largest) element from the unsorted portion of the list and swaps
 * it with the first element of the unsorted part. This process is repeated for the remaining
 * unsorted portion until the entire list is sorted. [<a href="https://www.geeksforgeeks.org/selection-sort/">Source</a>]
 */
public class SelectionSort {
    /**
     * Swaps elements position
     *
     * @param arr array
     * @param i   - position of first element
     * @param j   - position of second element
     */
    public static int[] swapElements(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return arr;
    }

    /**
     * Does a linear search on the array, starting from element at i.
     *
     * @param arr
     * @param i   starting position
     * @return Returns the smallest element starting from i
     */
    public static int indexLowest(int[] arr, int i) {
        int indexSmallest = i;
        ++i;
        while (i < arr.length) {
            if (arr[i] < arr[indexSmallest]) {
                indexSmallest = i;
            }
            ++i;
        }
        return indexSmallest;
    }

    public static int[] selectionSort(int[] arr) {
        int tempIndex; // holds the value for each indexLowest call;
        for (int i = 0; i < arr.length; i++) {
            tempIndex = indexLowest(arr, i);
            // Has found lower value
            if (tempIndex != i) {
                arr = swapElements(arr, i, tempIndex);
            }
        }
        return arr;
    }

    public static SMValue[] selectionSort(SMValue[] arr) {
        int tempIndex; // holds the value for each indexLowest call;
        for (int i = 0; i < arr.length; i++) {
            tempIndex = indexLowest(arr, i);
            // Has found lower value
            if (tempIndex != i) {
                arr = swapElements(arr, i, tempIndex);
            }
        }
        return arr;
    }

    public static SMValue[] swapElements(SMValue[] arr, int i, int j) {
        SMValue temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return arr;
    }

    public static int indexLowest(SMValue[] arr, int i) {
        int indexSmallest = i;
        ++i;
        while (i < arr.length) {
            if (arr[i].Value() < arr[indexSmallest].Value()) {
                indexSmallest = i;
            }
            ++i;
        }
        return indexSmallest;
    }

    // Takes the greatest in arr up to index I;
    public static SMValue[] recursive(SMValue[] arr, Integer i) {
        // This approach does sorting in an opposite fashion which makes it also stable.
        // arr [3,1,2]
        // [3, 1] -> [1, 3]
        // [1, 2, 3]
        if (i == null) i = arr.length - 1;
        if (i < 1) return arr;

        int largestIndex = getLargestIndex(arr, i);
        if (largestIndex != i) {
            swapElements(arr, largestIndex, i);
        }
        return recursive(arr, i - 1);
    }

    private static int getLargestIndex(SMValue[] arr, int start) {
        int result = start;
        for (int i = result - 1; i >= 0; --i) {
            if (arr[i].Value() > arr[result].Value()) result = i;
        }
        return result;
    }
}

