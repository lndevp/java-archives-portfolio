package portfolio.algorithms;

import java.util.Arrays;
import java.util.Random;

/** Compares sorting algorithms against best, average, and worst-case arrays. */
public class SortingBenchmark {
    public static void main(String[] args) {
        int[] bestCase = range(1, 1000);
        int[] worstCase = reversedRange(1, 1000);
        int[] averageCase = randomValues(1000, 1000, 42);

        runStats("Best case", bestCase);
        runStats("Average case", averageCase);
        runStats("Worst case", worstCase);

        int[] quickSortDemo = {2, 5, 7, 4, 6, 8, 3, 771, 9};
        SortingAlgorithms.quickSort(quickSortDemo);
        System.out.println("Quick sort demo: " + Arrays.toString(quickSortDemo));

        int[] mergeSortDemo = {5, 2, 9, 1, 5, 6};
        SortingAlgorithms.mergeSort(mergeSortDemo);
        System.out.println("Merge sort demo: " + Arrays.toString(mergeSortDemo));

        String[] names = {"Delta", "alpha", "Charlie", "bravo"};
        SortingAlgorithms.insertionSort(names);
        System.out.println("String insertion sort demo: " + Arrays.toString(names));
    }

    private static void runStats(String label, int[] original) {
        System.out.println("\n" + label);
        int[] bubble = Arrays.copyOf(original, original.length);
        int[] selection = Arrays.copyOf(original, original.length);
        int[] insertion = Arrays.copyOf(original, original.length);
        System.out.println(SortingAlgorithms.bubbleSort(bubble));
        System.out.println(SortingAlgorithms.selectionSort(selection));
        System.out.println(SortingAlgorithms.insertionSort(insertion));
    }

    private static int[] range(int startInclusive, int endInclusive) {
        int[] values = new int[endInclusive - startInclusive + 1];
        for (int i = 0; i < values.length; i++) {
            values[i] = startInclusive + i;
        }
        return values;
    }

    private static int[] reversedRange(int startInclusive, int endInclusive) {
        int[] values = range(startInclusive, endInclusive);
        for (int left = 0, right = values.length - 1; left < right; left++, right--) {
            int temp = values[left];
            values[left] = values[right];
            values[right] = temp;
        }
        return values;
    }

    private static int[] randomValues(int size, int maxExclusive, long seed) {
        Random random = new Random(seed);
        int[] values = new int[size];
        for (int i = 0; i < values.length; i++) {
            values[i] = random.nextInt(maxExclusive);
        }
        return values;
    }
}
