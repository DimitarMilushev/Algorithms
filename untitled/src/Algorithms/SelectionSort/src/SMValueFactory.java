package Algorithms.SelectionSort.src;

import java.util.Arrays;

public class SMValueFactory {
    private static final int firstTagASCII = 97;
    public static SMValue[] getValueFromIntArray(int[] arr) {
        SMValue[] resultList = new SMValue[arr.length];
        char tag;
        for (int i = 0; i < arr.length; i++) {
            tag = (char) (firstTagASCII + getNumberOfOccurence(Arrays.copyOfRange(resultList, 0, i), arr[i]));
            resultList[i] = new SMValue(arr[i], Character.toString(tag));
        }
        return resultList;
    }
    private static int getNumberOfOccurence(SMValue[] arr, int num) {
       int count = 0;
       for (int i = 0; i < arr.length; i++) {
           if (arr[i].Value() == num) {
               count++;
           }
       }
       return count;
    }

}
