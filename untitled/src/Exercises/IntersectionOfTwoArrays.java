package Exercises;

import Algorithms.CountingSort.src.CountingSort;

import java.util.*;

/**
 * Given two integer arrays nums1 and nums2, return an array of their
 * intersection. Each element in the result must be unique and
 * you may return the result in any order.
 *
 * Constraints:
 *
 *     1 <= nums1.length, nums2.length <= 1000
 *     0 <= nums1[i], nums2[i] <= 1000
 */
public class IntersectionOfTwoArrays {
    public static void main(String[] args) {
        final int[] arr1 = new int[] {1,2,2,1};
        final int[] arr2 = new int[] {2, 2};
        System.out.println(Arrays.toString(FastSolution(arr1, arr2)));
    }

    /**
     * Initial idea:
     * I need to find an intersection between two arrays w/ length 0 to 2000
     * Meaning: List all shared elements.
     *
     * Thought: Arrays seem unsorted. Sorting will likely make the process faster.
     * Can't escape iterating both - O(n) * 2
     * CountingSort O(n + k)
     * Then check w/ two pointers, one for nums1, one for nums2.
     * BOTH POINTERS SHOULD NOT EXCEED LENGTH
     * If nums1[ptr1] > nums2[ptr2] -> ptr1++
     * if nums1[ptr1] < nums2[ptr2] -> ptr2++
     * if == then add to set -> (ptr1, ptr2) += 1
     *
     * A technique to skip the Set DS is to use while loops that iterate through
     * same values.
     *
     * Though 2: Could use counting sort as it is O(n) and we know it's a
     *  small set of 2000 positive numbers
     */
    public static int[] HeuristicSolution(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) return new int[]{};

        ArrayList<Integer> set = new ArrayList<>();
        int ptr1 = 0;
        int ptr2 = 0;

        // O(n + k)
        CountingSort(nums1, NUMBERS_RANGE);
        CountingSort(nums2, NUMBERS_RANGE);

        int prev;
        while (ptr1 < nums1.length && ptr2 < nums2.length) {
            if (nums1[ptr1] == nums2[ptr2]) {
                set.add(nums1[ptr1]);
                // Remove duplicates
                prev = nums2[ptr2];
                while (ptr2 < nums2.length && prev == nums2[ptr2] ) {
                    ++ptr2;
                }
                prev = nums1[ptr1];
                while (ptr1 < nums1.length && prev == nums1[ptr1]) {
                    ++ptr1;
                }
                continue;
            }
            if (nums1[ptr1] < nums2[ptr2]) {
                ++ptr1;
            } else {
                ++ptr2;
            }
        }
        return set.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * This solution uses the least amount of space time.
     * 1. Stores all nums1 array values as indices, similar to a counting sort, inside
     * of a checkArray. The values of each index is a boolean:
     *      1.1. This indicates if index has been used (prevents duplication)
     *      1.2. Since boolean is only 2 bytes, this is the least memory heavy structure.
     * 2. Then creates a size variable, similar ot all Collections implementing structures.
     * 3. The output array is AT MOST the smaller of the two arrays.
     * 4. Checks for each element in nums2.
     *      4.1. If it exists, then mark as present, increment size and add to the output array.
     * 5. Copies elements from 0 to size of the output array.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    private static int[] FastSolution(int[] nums1, int[] nums2) {
        boolean[] checkArray = new boolean[NUMBERS_RANGE + 1];
        // O(n)
        for (int v : nums1) {
            checkArray[v] = true;
        }
        int size = 0;
        // O(n)
        int[] res = new int[Math.min(nums1.length, nums2.length)];
        // O(n)
        for (int v : nums2) {
            // O(1) ~
            if (checkArray[v]) { // Checks for duplicate
                res[size] = v;
                size++;
                checkArray[v] = false;
            }
        }
        // O(n)
        return Arrays.copyOf(res, size);
    }
    private static int[] SetSolution(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) return new int[]{};

        Set<Integer> smallerSetNumbers = new HashSet<>();
        ArrayList<Integer> intersections = new ArrayList<>(Math.min(nums1.length, nums2.length));
        // O(n)
        for (int e : nums1) {
            // O(1)
            smallerSetNumbers.add(e);
        }
        // O(n)
        for (int e : nums2){
            if (smallerSetNumbers.contains(e)) {
                intersections.add(e);
                smallerSetNumbers.remove(e);
            }
        }
        // O(n)
        return intersections.stream().mapToInt(Integer::intValue).toArray();
    }

    private static int NUMBERS_RANGE = 1000; // Constraint 0 - 1000
    private static void CountingSort(int[] list, int range) {
        int[] countingArr = new int[range + 1];
        Arrays.fill(countingArr, 0);
        // Find occurrences
        for (int e : list) {
            countingArr[e]++;
        }
        // Accumulate
        for (int i = 1; i < countingArr.length; i++) {
            countingArr[i] += countingArr[i - 1];
        }
        // Shift Right
        for (int i = countingArr.length - 1; i > 0; i--) {
            countingArr[i] = countingArr[i - 1];
        }
        countingArr[0] = 0;
        int[] output = new int[list.length];
        // Generate output
        int pos;
        for (int e : list) {
            pos = countingArr[e];
            output[pos] = e;
            countingArr[e]++;
        }
        // replace with output
        System.arraycopy(output, 0, list, 0, output.length);
    }
}
