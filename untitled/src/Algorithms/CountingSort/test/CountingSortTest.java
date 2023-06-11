package Algorithms.CountingSort.test;

import Algorithms.CountingSort.src.CountingSort;
import Algorithms.SelectionSort.src.SMValue;
import Algorithms.SelectionSort.src.SMValueFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class CountingSortTest {
    @Test
    public void CountingSort1to10() {
        final int[] unordered = new int[]{1, 4, 1, 2, 7, 5, 2};
        final int[] ordered = new int[]{1, 1, 2, 2, 4, 5, 7};

        final var expected = SMValueFactory.getValueFromIntArray(ordered);
        SMValue[] actual = SMValueFactory.getValueFromIntArray(unordered);

        CountingSort.sort(actual, 0, 9);

        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void CountingSort10to99() {
        final int[] unordered = new int[]{27, 50, 72, 45, 61, 85, 31, 92, 61, 65, 78, 37, 92, 20, 45, 82, 65, 56, 72, 82};
        final int[] ordered = new int[]{20, 27, 31, 37, 45, 45, 50, 56, 61, 61, 65, 65, 72, 72, 78, 82, 82, 85, 92, 92};

        final var expected = SMValueFactory.getValueFromIntArray(ordered);
        SMValue[] actual = SMValueFactory.getValueFromIntArray(unordered);

        CountingSort.sort(actual, 10, 99);

        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void CountingSortNegatives() {
        final int[] unordered = new int[]{5, -2, 3, 0, 7, -7, -1, 10, 5, -8};
        final int[] ordered = new int[]{-8, -7, -2, -1, 0, 3, 5, 5, 7, 10};

        final var expected = SMValueFactory.getValueFromIntArray(ordered);
        SMValue[] actual = SMValueFactory.getValueFromIntArray(unordered);

        CountingSort.sort(actual, -10, 10);

        Assert.assertArrayEquals(expected, actual);
    }
}
