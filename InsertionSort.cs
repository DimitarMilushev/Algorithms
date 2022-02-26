using System;
class InsertionSort {
        //2.1-1
        public static int[] insertSort_Increasing() {
        const int arrayLength = 6;
        int[] A = new int[arrayLength] {31, 41, 59, 26, 41, 58};

        int currentValue = A[0];
        //Invaraint: 
        //In insertion sort, loop invariant condition is that the subarray A[0 to i-1] is always sorted.
        int j;
        for (int i = 1; i < arrayLength; i++) {
            currentValue = A[i];
            j = i - 1;
            // Check with previous values and swap until you find a larger one
            while(j >= 0 && A[j] > currentValue) {
                A[j + 1] = A[j];
                --j;
            }
            A[j + 1] = currentValue;
        }
        return A;
    }
    //2.1-2
    public static int[] insertSort_Decreasing() {
        const int arrayLength = 6;
        int[] A = new int[arrayLength] {31, 41, 59, 26, 41, 58};
        
        int currentValue;
        int j;
        //Same as increasing, but we check for smaller digits
        for (int i = 1; i < arrayLength; i++) {
            currentValue = A[i];
            j = i - 1;
            while(j >= 0 && A[j] < currentValue) {
                A[j + 1] = A[j];
                --j;
            }
            A[j + 1] = currentValue;
        }
        return A;
    }
}