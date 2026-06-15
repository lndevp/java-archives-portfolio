package portfolio.algorithms;

import java.util.Arrays;

/** Clean implementations of common sorting algorithms. */
public final class SortingAlgorithms {
    private SortingAlgorithms() {
    }

    public static SortStats bubbleSort(int[] values) {
        SortStats stats = new SortStats("Bubble sort");
        boolean swapped;
        do {
            swapped = false;
            stats.passes++;
            for (int i = 0; i < values.length - 1; i++) {
                stats.comparisons++;
                if (values[i] > values[i + 1]) {
                    swap(values, i, i + 1, stats);
                    swapped = true;
                }
            }
        } while (swapped);
        return stats;
    }

    public static SortStats selectionSort(int[] values) {
        SortStats stats = new SortStats("Selection sort");
        for (int i = 0; i < values.length - 1; i++) {
            stats.passes++;
            int smallestIndex = i;
            for (int j = i + 1; j < values.length; j++) {
                stats.comparisons++;
                if (values[j] < values[smallestIndex]) {
                    smallestIndex = j;
                }
            }
            if (smallestIndex != i) {
                swap(values, i, smallestIndex, stats);
            }
        }
        return stats;
    }

    public static SortStats insertionSort(int[] values) {
        SortStats stats = new SortStats("Insertion sort");
        for (int i = 1; i < values.length; i++) {
            stats.passes++;
            int valueToInsert = values[i];
            stats.writes++;
            int j = i - 1;
            while (j >= 0) {
                stats.comparisons++;
                if (values[j] <= valueToInsert) {
                    break;
                }
                values[j + 1] = values[j];
                stats.writes++;
                j--;
            }
            values[j + 1] = valueToInsert;
            stats.writes++;
        }
        return stats;
    }

    public static void mergeSort(int[] values) {
        if (values.length <= 1) {
            return;
        }
        int[] workspace = Arrays.copyOf(values, values.length);
        mergeSort(values, workspace, 0, values.length - 1);
    }

    private static void mergeSort(int[] values, int[] workspace, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = low + (high - low) / 2;
        mergeSort(values, workspace, low, mid);
        mergeSort(values, workspace, mid + 1, high);
        merge(values, workspace, low, mid, high);
    }

    private static void merge(int[] values, int[] workspace, int low, int mid, int high) {
        System.arraycopy(values, low, workspace, low, high - low + 1);
        int left = low;
        int right = mid + 1;
        int current = low;

        while (left <= mid && right <= high) {
            if (workspace[left] <= workspace[right]) {
                values[current++] = workspace[left++];
            } else {
                values[current++] = workspace[right++];
            }
        }
        while (left <= mid) {
            values[current++] = workspace[left++];
        }
    }

    public static void quickSort(int[] values) {
        quickSort(values, 0, values.length - 1);
    }

    private static void quickSort(int[] values, int low, int high) {
        if (low >= high) {
            return;
        }
        int pivotIndex = partition(values, low, high);
        quickSort(values, low, pivotIndex - 1);
        quickSort(values, pivotIndex + 1, high);
    }

    private static int partition(int[] values, int low, int high) {
        int pivot = values[high];
        int smallerBoundary = low;
        for (int current = low; current < high; current++) {
            if (values[current] <= pivot) {
                int temp = values[smallerBoundary];
                values[smallerBoundary] = values[current];
                values[current] = temp;
                smallerBoundary++;
            }
        }
        int temp = values[smallerBoundary];
        values[smallerBoundary] = values[high];
        values[high] = temp;
        return smallerBoundary;
    }

    public static void insertionSort(String[] values) {
        for (int i = 1; i < values.length; i++) {
            String valueToInsert = values[i];
            int j = i - 1;
            while (j >= 0 && values[j].compareToIgnoreCase(valueToInsert) > 0) {
                values[j + 1] = values[j];
                j--;
            }
            values[j + 1] = valueToInsert;
        }
    }

    private static void swap(int[] values, int first, int second, SortStats stats) {
        int temp = values[first];
        values[first] = values[second];
        values[second] = temp;
        stats.swaps++;
        stats.writes += 3;
    }

    public static final class SortStats {
        private final String algorithm;
        private int passes;
        private int comparisons;
        private int swaps;
        private int writes;

        private SortStats(String algorithm) {
            this.algorithm = algorithm;
        }

        @Override
        public String toString() {
            return "%s: passes=%d, comparisons=%d, swaps=%d, writes=%d".formatted(
                    algorithm, passes, comparisons, swaps, writes);
        }
    }
}
