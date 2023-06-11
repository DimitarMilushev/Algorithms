package Algorithms.SelectionSort.src;

import java.util.Arrays;

public class SelectionSortValueFactory {
    private static final int firstTagASCII = 97;
    public static SelectionSortValue[] getValueFromIntArray(int[] arr) {
        SelectionSortValue[] resultList = new SelectionSortValue[arr.length];
        char tag;
        for (int i = 0; i < arr.length; i++) {
            tag = (char) (firstTagASCII + getNumberOfOccurence(Arrays.copyOfRange(resultList, 0, i), arr[i]));
            resultList[i] = new SelectionSortValue(arr[i], Character.toString(tag));
        }
        return resultList;
    }
    private static int getNumberOfOccurence(SelectionSortValue[] arr, int num) {
       int count = 0;
       for (int i = 0; i < arr.length; i++) {
           if (arr[i].Value() == num) {
               count++;
           }
       }
       return count;
    }

}
