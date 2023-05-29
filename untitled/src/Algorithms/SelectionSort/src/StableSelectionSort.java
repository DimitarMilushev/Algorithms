package Algorithms.SelectionSort.src;

import java.util.Arrays;

/**
 * Since the regular SelectionSort is deemed an unstable algorithm because it doesn't keep the relative
 * order of elements, this implementation would try to tackle this problem, hence making the algorithm STABLE.
 *
 * To do this, we'll replace swapping of j(smallest) with removing it,
 * placing it at i and shifting the rest from [i..j], similar to insertion sort.
 *
 * Problem with this is that, although stable, there's a lot of shifting, which makes for additional computational
 * burden. A better solution would be to use LinkedList.
 * [<a href="https://www.baeldung.com/cs/selection-sort-stable">Source</a>]
 */
public class StableSelectionSort {
    public static void main(String[] args) {
        int[] list = new int[] {4, 1, 2, 4, 3};
        SelectionSortValue[] values = SelectionSortValueFactory.getValueFromIntArray(list);
        System.out.println(Arrays.toString(selectionSort(values)));
    }
    public static SelectionSortValue[] selectionSort(SelectionSortValue[] arr) {
        int tempIndex; // holds the value for each indexLowest call;
        for (int i = 0; i < arr.length; i++) {
            tempIndex = indexLowest(arr, i);
            // Has found lower value
            if (tempIndex != i) {
                arr = shiftElements(arr, i, tempIndex);
            }
        }
        return arr;
    }

    /**
     * Shifts the elements up to the ending index one step ahead.
     * Then places the j value at the start of the array.
     * @param to End of shifting of sub-array
     * @param start Start of shifting of sub-array
     */
    public static SelectionSortValue[] shiftElements(SelectionSortValue[] arr, int start,  int to) {
        SelectionSortValue lastElement = arr[to];
        SelectionSortValue temp;

        for (int i = to; i > start; i--) {
            arr[i] = arr[i - 1];
        }
        arr[start] = lastElement;

        return arr;
    }
    public static int indexLowest(SelectionSortValue[] arr, int i) {
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
}
