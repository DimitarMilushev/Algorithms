package Exercises;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 *
 * Constraints:
 *
 *     1 <= s.length, t.length <= 5 * 104
 *     s and t consist of lowercase English letters.
 *
 *
 *
 * Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
 */
public class ValidAnagram {
    public static void main(String[] args) {
        System.out.println(CountSortSolution("cx", "nl"));
    }

    /**
     * We think of the strings as two arrays of characters.
     * Instead of doing a lookup on all permutations, we take the arrays, sort them and simply check if they're equal.
     * Arrays creation: O(n)
     * Arrays sorting: O(n logn)
     * Arrays comparison: O(n)
     * -> O(n) + O(n logn) + O(n) = O(n logn)
     *
     * Time Complexity: O(n logn)
     * Space Complexity: O(n) -> two arrays of n elements
     * @param s word
     * @param t potential anagram
     * @return if t is anagram of s
     */
    private static boolean HeuristicSolution(String s, String t) {
        // O(1)
        if (s.length() != t.length()) return false;
        // O(n)
        char[] e1 = s.toCharArray();
        char[] e2 = t.toCharArray();
        // O(n logn)
        Arrays.sort(e1);
        Arrays.sort(e2);
        // O(n)
        return Arrays.equals(e1, e2);
    }

    /**
     * This solution will not create arrays to save on space.
     * We'll incorporate a HashMap that saves each letter from s and append number of occurrences to it.
     * Then move through it a second time with the t by decrementing occurrences.
     * Then go through the map again. All occurrences should be 0.
     *
     * @param s
     * @param t
     * @return
     */
    private  static boolean MapSolution(String s, String t) {
        // O(1)
        if (s.length() != t.length()) return false;
        HashMap<Character, Integer> map = new HashMap<>(26); // 26 letters in English alphabet
        // First arr O(n)
        for (int i = 0; i < s.length(); i++) {
            // Gets the key O(1)
            // Checks if value is null O(1)
            // Sets the value to OR increments with 1 O(1)
            map.merge(s.charAt(i), 1, Integer::sum);
            map.merge(t.charAt(i), -1, Integer::sum);
        }
        // Second iteration + mapping all values to a collection -> O(n) + O(n)
        for (Integer occurrences : map.values()) {
            if (occurrences != 0) return false;
        }
        return true;
    }
    /**
     * Since characters have a numeric value beneath, the sum of the two strings should be exactly the same.
     * This would take O(n) time and a constant Space complexity.
     *
     * Pitfall: would work only for unique lists.
     */
    private  static boolean SumSolution(String s, String t) {
        if (s.length() != t.length()) return false;
        int sum1 = 0;
        int sum2 = 0;
        // First arr O(n)
        for (int i = 0; i < s.length(); i++) {
            sum1 += s.charAt(i);
        }
        // Second array O(n)
        for (int i = 0; i < t.length(); i++) {
            sum2 += t.charAt(i);
        }
        return sum1 == sum2;
    }
    final static int range = 26; // 26 letters in the English alphabet

    /**
     * Similar to a hashmap, we will implement a count sort, a sorting algorithm that takes a range and for each
     * unique value adds up occurrences.
     * This creates only one list.
     * And traverses each string once O(n)
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * To match the letters to the indices we'll subtract their ascii value.
     */

    private  static boolean CountSortSolution(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] map = new int[range];

        // First arr O(n)
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 'a']++; // a = 47, so if char is 'a', it matches 0, while 'z' - 25
            map[t.charAt(i) - 'a']--;

        }
        // Second array O(n)
        for (int i = 0; i < map.length; i++) {
            if (map[i] != 0) return false;
        }
        System.gc(); // Manually call the garbage collector.
        return true;
    }
}
