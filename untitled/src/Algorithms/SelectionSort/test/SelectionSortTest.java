package Algorithms.SelectionSort.test;

import Algorithms.SelectionSort.src.SMValue;
import Algorithms.SelectionSort.src.SMValueFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static Algorithms.SelectionSort.src.SelectionSort.selectionSort;

public class SelectionSortTest {
    private int[] list;

    @Test
    public void sortsIntegerElements() {
        list = new int[] {4, 1, 2, 4, 3};
        int[] result = selectionSort(list);
        int[] expected = new int[] {1, 2, 3, 4, 4};
        Assert.assertArrayEquals(result, expected );
    }
    @Test
    public void sortsSelectionSortValueElements() {
        list = new int[] {4, 1, 2, 4, 3};
        SMValue[] values = selectionSort(SMValueFactory.getValueFromIntArray(list));
        int[] result = new int[values.length];
        Object[] objArray = Arrays.stream(values).map(SMValue::Value).toArray();
        for (int i = 0; i < values.length; i++) {
            result[i] = (int) objArray[i];
        }

        int[] expected = new int[] {1, 2, 3, 4, 4};
        Assert.assertArrayEquals(result, expected );
    }
    /**
     * Demonstrate that selection sort is unstable when 4a comes after 4b
     * Doesn't preserve order of appearance.
     */
    @Test
    public void selectionSortIsUnstable() {
        list = new int[] {4, 1, 2, 4, 3};
        SMValue[] values = SMValueFactory.getValueFromIntArray(list);
        SMValue first = null;
        SMValue second = null;
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

        SMValue[] sorted = selectionSort(values);
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
        // xB will come before xA in an unstable algorithm
        Assert.assertEquals(first.tag(), "b");
        Assert.assertEquals(second.tag(), "a");
    }

}
