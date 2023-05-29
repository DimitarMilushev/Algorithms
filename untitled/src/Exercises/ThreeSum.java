package Algorithms.Exercises.src;

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
     public static void main(String[] args) {
         int[] list = new int[] {-1,0,1,2,-1,-4};
         System.out.println(threeSum(list));

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
    // Optimization - add all values in a hashmap
    // Then Check if the diff of the other 2 numbers is equal to it - if TRUE, then add pair
    public static List<List<Integer>> threeSum(int[] nums) {
        ArrayList<Integer> triplets = new ArrayList<Integer>(Collections.nCopies(3, 0));
        HashMap<String, List<Integer>> mappings = new HashMap();
        // O(n)
        // Add all numbers as unique keys
        for (int i = 0; i< nums.length; i++) {
//            mappings.put() // TODO: FInish
        }
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
        return new ArrayList(mappings.values().stream().toList());
    }
    // Apply selection sort on triplets (complexity is negligible) - Search smallest
    private static String generateHashID(List<Integer> input) {
        Integer[] triplets = input.toArray(new Integer[input.size()]);
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
}