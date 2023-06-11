package Algorithms.SelectionSort.src;

/**
 * Stability-Measuring Value
 *
 * Represents a value with a tag to demonstrate why some algorithms are considered UNSTABLE.
 * Resembles a different index by which we recognize variables.
 */
public record SMValue(int Value, String tag) {
    @Override
    public String toString() {
        return Value + tag;
    }
}
