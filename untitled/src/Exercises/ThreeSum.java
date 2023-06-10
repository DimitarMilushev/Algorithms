package Algorithms.Exercises.src;

import jdk.jshell.spi.ExecutionControl;

import java.text.MessageFormat;
import java.util.*;

/**
 * LeetCode 3SUM
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that
 * i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 * Example 1:
 *
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Explanation:
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
 * The distinct triplets are [-1,0,1] and [-1,-1,2].
 * Notice that the order of the output and the order of the triplets does not matter.
 *
 * Example 2:
 *
 * Input: nums = [0,1,1]
 * Output: []
 * Explanation: The only possible triplet does not sum up to 0.
 *
 * Example 3:
 *
 * Input: nums = [0,0,0]
 * Output: [[0,0,0]]
 * Explanation: The only possible triplet sums up to 0.
 *
 * Constraints:
 *
 *     3 <= nums.length <= 3000
 *     -105 <= nums[i] <= 105
 */
class ThreeSum {
    private static void printSolution(String name, String result, String complexity) {
        System.out.println(MessageFormat.format(
                "3Sum [{0}]\n    Result: {1}\n    TimeComplexity: {2}\n===================================",
                name, result, complexity));
    }
     public static void main(String[] args) {
         int[] list = new int[] {-1,0,1,2,-1,-4,-2,-3,3,0,4};
         printSolution("Two Pointer", threeSumTwoPointer(list).toString(), "O(n^2)");
         printSolution("Heuristic Approach", threeSumHeuristic(list).toString(), "O(n^3)");
         printSolution("HashMap Approach", threeSumHashMap(list).toString(), "O(n^2)");
         /**
          * Small test that concludes java passes myList by reference!
          * Try using .put with by passing myList and then myList.stream().toList()
          */

//        ArrayList<Integer> myList = new ArrayList<>(List.of(1, 2, 3));
//        HashMap<String, List<Integer>> m = new HashMap<>();
//        m.put("123", myList));
//
//        myList.set(0, 4);
//        myList.set(1, 4);
//        myList.set(2, 4);
//        m.put("345", myList);
//        System.out.println(m.toString());
    }

    /**
     * Heuristic approach. (Bad runtime speed)
     * Time complexity: O(n^3)
     */
   public static List<List<Integer>> threeSumHeuristic(int[] nums) {
       if (nums.length < 3) {
           return new ArrayList();
       }
       ArrayList<Integer> triplets = new ArrayList<Integer>(Collections.nCopies(3, 0));
       HashMap<String, List<Integer>> mappings = new HashMap();
       for (int i = 0; i < nums.length - 2; i++) {
           triplets.set(0, nums[i]);
           for (int j = i + 1; j < nums.length - 1; j++) {
               triplets.set(1, nums[j]);
               for (int z = j + 1; z < nums.length; z++) {
                   triplets.set(2, nums[z]);
                   if (triplets.stream().reduce(0, Integer::sum) == 0) {
                       mappings.putIfAbsent(generateHashID(triplets), triplets.stream().toList());
                   }
               }
           }
       }
       return new ArrayList<>(mappings.values().stream().toList());
   }
    // Apply selection sort on triplets (complexity is negligible) - Search smallest
    private static String generateHashID(List<Integer> input) {
        Integer[] triplets = input.toArray(new Integer[0]);
        int tempIndex;
        int tempValue;
        for (int i = 0; i < triplets.length; i++) {
            tempIndex = i;
            for (int j = i + 1; j < triplets.length; j++) {
                if (triplets[tempIndex] > triplets[j]) {
                    tempIndex = j;
                }
            }
            //Swap positions
            if (tempIndex != i) {
                tempValue = triplets[tempIndex];
                triplets[tempIndex] = triplets[i];
                triplets[i] =  tempValue;
            }
        }
        StringBuilder sb = new StringBuilder();
        Arrays.stream(triplets).forEach(sb::append);
        return sb.toString();
    }
    /**
     * Two pointer method.
     *
     * Find by checking the diff of numbers in two pointers and subtracting it with the third number.
     * sum of ptr1, ptr2 and nums[i] equals 0, add to result and move pointers
     * if sum is lower than third num, increment ptr1
     * else decrement ptr2
     */
    public static List<List<Integer>> threeSumTwoPointer(int[] nums) {
       // Sort arr
        Arrays.sort(nums);
        // Outputs storage
        Set<List<Integer>> output = new HashSet();
        // Create pointers
        int high, low;
        // Other variables
        int sum, temp1, temp2, temp3;
        int i = 0;
        while (i < nums.length) {
            // set low boundary
            low = i + 1;
            high = nums.length - 1;
            while (low < high) {
                sum = nums[i] + nums[low] + nums[high];
                if (sum > 0) {
                    --high;
                } else if (sum < 0) {
                    ++low;
                } else {
                    output.add(List.of(nums[i], nums[low], nums[high]));
                    // Move both because there cannot be another x = low + high* or low* + high.
                    // To ensure we iterate the first time, we create temp1, temp2 to store current indices.
                    temp1 = low;
                    temp2 = high;
                    while (low < high && nums[temp1] == nums[low]) low++;
                    while (low < high && nums[temp2] == nums[high]) high--;
                }
            }
            temp3 = i;
            while (i < nums.length && nums[temp3] == nums[i]) i++;
        }
        return output.stream().toList();
    }

    /**
     * Hashmap approach.
     * 1. Sorts the input O(n * logn)
     * 2. Creates a hashmap. Uses the nums value for hash and the index for value. This way the last index is of the value
     * is preserved. O(n)
     * Then for each element in nums checks if the result of n[i] - n[j] is equal to any of the keys in the hashmap.
     * if YES then saves to result O(n^2)
     * Result: O(n^2) + O(n) + O(n ln) which is technically better than O(n^3)
     * Sometimes checks if num[j] == num[j - 1] to avoid duplicates.
     * @param nums
     * @return List of triplets where the sum is equal to 0
     */
    public static List<List<Integer>> threeSumHashMap(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<>();
        }
        // O (n * ln)
        Arrays.sort(nums);
        if (nums[0] > 0) {
            return new ArrayList<>();
        }
        HashMap<Integer, Integer> mappings = new HashMap();
        ArrayList<List<Integer>> result = new ArrayList<>();
        // O(n)
        // Add all numbers as unique keys
        for (int i = 0; i< nums.length; i++) {
            mappings.put(nums[i], i);
        }
        int key;
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) break;
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            for (int j = i + 1; j < nums.length - 1; j++) {
                if (j != (i + 1) && nums[j] == nums[j - 1]) {
                    continue;
                }
                key = -1 * (nums[i] + nums[j]);
                if (mappings.containsKey(key) && mappings.get(key) > j) {
                    result.add(List.of(key, nums[i], nums[j]));
                }
            }
        }
        return result.stream().toList();
    }
}