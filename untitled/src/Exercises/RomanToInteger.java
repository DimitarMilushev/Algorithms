package Exercises;

import java.util.HashMap;
import java.util.Map;

/**
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 *
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 *
 * For example, 2 is written as II in Roman numeral, just two ones added together.
 * 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
 *
 * Roman numerals are usually written largest to smallest from left to right.
 * However, the numeral for four is not IIII. Instead, the number four is written as IV.
 * Because the one is before the five we subtract it making four.
 * The same principle applies to the number nine, which is written as IX.
 * There are six instances where subtraction is used:
 *
 *     I can be placed before V (5) and X (10) to make 4 and 9.
 *     X can be placed before L (50) and C (100) to make 40 and 90.
 *     C can be placed before D (500) and M (1000) to make 400 and 900.
 *
 * Given a roman numeral, convert it to an integer.
 */
public class RomanToInteger {
    public static void main(String[] args) {
        System.out.println(SwitchSolution("XVIII"));
    }

    /**
     * This solution will use switch statement to reduce space complexity, created by
     * HashSet.
     * Clears duplicates by adding them. - If reaches length it returns sum
     *
     */
    private static int SwitchSolution(String s) {
        if (s.length() == 0) return 0;
        char[] symbols = s.toCharArray();
        int prev = 0;
        int current = 0;
        int sum = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            current = GetSymbolValue(symbols[i]);
            // Clear out duplicates
            while (prev == current) {
                sum += prev;
                --i;
                if (i < 0) return sum;
                current = GetSymbolValue(symbols[i]);
            }
            if (current > prev) {
                sum += current;
            } else {
                sum -= current;
            }
            prev = current;
        }

        return sum;
    }
    private static int GetSymbolValue(char symbol) {
        return switch (symbol) {
            case 'I' -> 1;
            case 'V' -> 5;
            case 'X' -> 10;
            case 'L' -> 50;
            case 'C' -> 100;
            case 'D' -> 500;
            case 'M' -> 1000;
            default -> throw new IllegalArgumentException();
        };
    }
    private static int HeuristicSolution(String s) {
        // We'll be using associative array as a DICTIONARY for the numbers;
        final Map<String, Integer> numbersDictionary = SetUpDictionary();
        // Space complexity O(n)
        // Time complexity of get O(1)

        // longest sequence = 3!!
        // Subtraction requirements:
        // If next element is larger
        // Prerequisite - a correct Roman number is sorted by value
        char[] symbols = s.toCharArray();
        int e1 = numbersDictionary.get(String.valueOf(symbols[0]));
        int e2;
        int sum = 0;
        int multiplier = 1;
        // GO through elements linearly O(n)
        for (int i = 1; i < symbols.length; i++) {
            e2 = numbersDictionary.get(String.valueOf(symbols[i]));
            // Next is smaller, end cycle
           if (e1 > e2) {
               // Clear so far
               sum += e1 * multiplier;
               multiplier = 1;
               e1 = e2;
           }
           // If next is larger, then continue and do subtraction.
           else if (e1 < e2) {
               // Can subtract from only one larger
               // Next should be smaller
               sum += e2 - e1;
               multiplier = 0;
               e1 = e2;
           }
           // comparisonResult == 0
           // Can have as many as you want from the same type. Each adds up to multiplier
           else {
               ++multiplier;
           }
        }
        sum += e1 * multiplier;
        return sum;
    }
    private static HashMap<String, Integer> SetUpDictionary() {
        HashMap<String, Integer> dictionary = new HashMap<>();
        dictionary.put("I", 1);
        dictionary.put("V", 5);
        dictionary.put("X", 10);
        dictionary.put("L", 50);
        dictionary.put("C", 100);
        dictionary.put("D", 500);
        dictionary.put("M", 1000);
        return dictionary;
    }

}
