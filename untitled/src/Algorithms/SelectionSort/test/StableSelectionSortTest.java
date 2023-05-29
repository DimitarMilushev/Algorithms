package Algorithms.SelectionSort.test;

import Algorithms.SelectionSort.src.SelectionSortValue;
import Algorithms.SelectionSort.src.SelectionSortValueFactory;
import org.junit.Assert;
import org.junit.Test;

import static Algorithms.SelectionSort.src.StableSelectionSort.selectionSort;


public class StableSelectionSortTest {
    private int[] list;

    /**
     * Demonstrate how [StableSelectionSort] implementation preserves the natural order of elements
     */
    @Test
    public void selectionSortIsStable() {
        list = new int[] {4, 1, 2, 4, 3};
        SelectionSortValue[] values = SelectionSortValueFactory.getValueFromIntArray(list);
        SelectionSortValue first = null;
        SelectionSortValue second = null;
        for (int i = 0; i < values.length; i++) {
            if (values[i].Value() == 4) {
                if (first == null) {
                    first = values[i];
                } else {
                    second = values[i];
                    break;
                }
            }
        }
        // Naturally xA comes before xB
        Assert.assertEquals(first.tag(), "a");
        Assert.assertEquals(second.tag(), "b");

        SelectionSortValue[] sorted = selectionSort(values);
        first = null;
        second = null;
        for (int i = 0; i < sorted.length; i++) {
            if (sorted[i].Value() == 4) {
                if (first == null) {
                    first = sorted[i];
                } else {
                    second = sorted[i];
                    break;
                }
            }
        }
        // xA should come before xB as it naturally appears.
        Assert.assertEquals(first.tag(), "a");
        Assert.assertEquals(second.tag(), "b");
    }
}
