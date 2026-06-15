package portfolio.algorithms;

/** Utility methods for linear and binary search. */
public final class SearchAlgorithms {
    private SearchAlgorithms() {
    }

    public static int linearSearch(int[] values, int target) {
        for (int index = 0; index < values.length; index++) {
            if (values[index] == target) {
                return index;
            }
        }
        return -1;
    }

    public static int binarySearch(int[] sortedValues, int target) {
        int low = 0;
        int high = sortedValues.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (sortedValues[mid] == target) {
                return mid;
            }
            if (sortedValues[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static SearchResult binarySearchWithCount(int[] sortedValues, int target) {
        int low = 0;
        int high = sortedValues.length - 1;
        int comparisons = 0;

        while (low <= high) {
            comparisons++;
            int mid = low + (high - low) / 2;
            if (sortedValues[mid] == target) {
                return new SearchResult(mid, comparisons);
            }
            if (sortedValues[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return new SearchResult(-1, comparisons);
    }

    public record SearchResult(int index, int comparisons) {
    }
}
