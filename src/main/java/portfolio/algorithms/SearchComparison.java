package portfolio.algorithms;

import java.util.Arrays;
import java.util.Random;

/** Demonstrates linear search versus binary search on sorted data. */
public class SearchComparison {
    public static void main(String[] args) {
        int[] values = new int[17];
        for (int i = 0; i < values.length; i++) {
            values[i] = i;
        }

        int target = new Random(7).nextInt(values.length);
        int linearIndex = SearchAlgorithms.linearSearch(values, target);
        SearchAlgorithms.SearchResult binaryResult = SearchAlgorithms.binarySearchWithCount(values, target);

        System.out.println("Values: " + Arrays.toString(values));
        System.out.println("Target: " + target);
        System.out.println("Linear search index: " + linearIndex);
        System.out.println("Binary search index: " + binaryResult.index());
        System.out.println("Binary search comparisons: " + binaryResult.comparisons());
    }
}
