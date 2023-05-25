class LinearSearch
{
    public static int? basicLinearSearch(int targetNumber)
    {
        int[] sequence = new int[] {67, 23, 95, 84, 33, 81, 65, 2, 44, 55, 74, 43};
        // Invariant; There exists i in [0,...,n], where i != target
        for(int i = 0; i < sequence.Length; i++) {
            if (sequence[i] == targetNumber) {
                return sequence[i];
            }
        }
        return null;
    }
}