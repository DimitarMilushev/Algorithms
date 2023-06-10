package Exercises;

import jdk.jshell.spi.ExecutionControl;
import org.junit.rules.Stopwatch;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Timer;

/**
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * You can return the answer in any order.
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] list = new int[]{2, 7, 11, 15};
        int target = 9;
        final long t1 = System.nanoTime();
        int[] output = HashMapSolution(list, target);
        final long t2 = System.nanoTime();
        System.out.println(Arrays.toString(output));
        System.out.println("Execution time: " + (t2 - t1));
    }

    public static int[] HashMapSolution(int[] nums, int target) {
        // 1. Check if diff of target and number is present in map
        // 1.1 TRUE - return result
        // 1.2 FALSE - add value to hashmap
        // Hashing - by associating the index by value we can check if any result subtracted by target is matching.
        // Analogy - a + b = c <==> c - b = a
        // This way we traverse the list only once - O(n)
        // Getting an element from HashMap has constant time complexity - O(1)
        int[] result = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        int key;
        for (int i = 0; i < nums.length; i++) {
            key = target - nums[i];
            if (map.containsKey(key)) {
                result[0] = map.get(key);
                result[1] = i;
                break;
            }
            // Optimization since we care only for the first occurrence
            map.putIfAbsent(nums[i], i);
        }
        return result;
    }

    /**
     * This solution won't work, because we can't order the list, since it would re-arrange the indices.
     */
    public static int[] TwoPointerSolution(int[] nums, int target) {
        // Using two pointers
        Arrays.sort(nums);
        // Traversal is O(n), since we'll be traversing index based on result
        int low = 0;
        int high = nums.length - 1;
        int sum;
        while (low < high) {
            sum = nums[low] + nums[high];
            if (sum == target) {
                return new int[] {low, high};
            }
            if (sum > target) {
                high--;
            } else {
                low++;
            }
        }
        return new int[]{};
    }
    public static int[] HeuristicSolution(int[] nums, int target) {
        // Traverse through each element and check sum of all elements in sub-list
        // Time Complexity: O(n^2)

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                }
                // Small optimization to clear duplicates
                while (j < nums.length - 2 && nums[j] == nums[j + 1]) {
                    ++j;
                }
            }
            // Small optimization to clear duplicates
            while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                ++i;
            }
        }
        return new int[]{};
    }
}
