package Algorithms.CountingSort.src;

import Algorithms.SelectionSort.src.SMValue;

import java.util.Arrays;
import java.util.Collection;

public class CountingSort {

    public static void sort(SMValue[] list, int min, int max) {
        int range = max - min;
        int[] countingArr = new int[range + 1];
        Arrays.fill(countingArr, 0); // Fill with 0s
        // Load up counting arr
        for(SMValue v : list){
            countingArr[v.Value() - min]++;
        }
        // Calculate positions
        for (int i = 1; i < countingArr.length; i++) {
            countingArr[i] += countingArr[i - 1];
        }
        // Shift elements
        for (int i = countingArr.length - 1; i > 0; i--) {
            countingArr[i] = countingArr[i - 1];
        }
        countingArr[0] = 0;
        // Generate output
        SMValue[] output = new SMValue[list.length];
        int pos;
        for (var v : list) {
            pos = countingArr[v.Value() - min];
            output[pos] = v;
            countingArr[v.Value() - min]++; // increment starting pos
        }

        // Apply output to reference
        for (int i = 0; i < output.length; i++) {
            list[i] = output[i];
        }
        System.out.println(Arrays.toString(output));
    }

    /**
     * The no shift implementation will save on some computational load by not shifting elements.
     * To do this we'll have to consider that the current indices are the last position of the element,
     * so we iterate backwards and decrement each occurrence.
     * @param list
     * @param min
     * @param max
     */
    public static void sortNoShift(SMValue[] list, int min, int max) {
        int range = max - min;
        int[] countingArr = new int[range + 1];
        Arrays.fill(countingArr, 0); // Fill with 0s
        // Load up counting arr
        for(SMValue v : list){
            countingArr[v.Value() - min]++;
        }
        // Calculate positions
        for (int i = 1; i < countingArr.length; i++) {
            countingArr[i] += countingArr[i - 1];
        }

        // Generate output
        SMValue[] output = new SMValue[list.length];
        int pos;
        for (int i = list.length - 1; i >= 0; --i) {
            pos = countingArr[list[i].Value() - min];
            output[pos - 1] = list[i];
            countingArr[list[i].Value() - min]--; // decrement starting pos
        }

        // Apply output to reference
        for (int i = 0; i < output.length; i++) {
            list[i] = output[i];
        }
        System.out.println(Arrays.toString(output));
    }
}
