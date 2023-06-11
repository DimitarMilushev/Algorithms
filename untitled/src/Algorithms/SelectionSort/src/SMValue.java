package Algorithms.SelectionSort.src;

/**
 * Represents a value with a tag to demonstrate why some algorithms are considered UNSTABLE.
 * Resembles a different index by which we recognize variables.
 */
public record SelectionSortValue(int Value, String tag) {
    @Override
    public String toString() {
        return Value + tag;
    }
}
