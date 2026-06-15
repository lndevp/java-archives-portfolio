package portfolio.algorithms;

/** Small recursive methods from early algorithm practice. */
public class RecursionExamples {
    public static void main(String[] args) {
        System.out.println("factorial(5) = " + factorial(5));
        System.out.println("sumTo(10) = " + sumTo(10));
        System.out.println("binarySearchRecursive = " + binarySearchRecursive(new int[] {1, 3, 5, 7, 9}, 7));
        countdown(3);
    }

    public static long factorial(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("value must be non-negative");
        }
        if (value <= 1) {
            return 1;
        }
        return value * factorial(value - 1);
    }

    public static int sumTo(int value) {
        if (value <= 0) {
            return 0;
        }
        return value + sumTo(value - 1);
    }

    public static int binarySearchRecursive(int[] sortedValues, int target) {
        return binarySearchRecursive(sortedValues, target, 0, sortedValues.length - 1);
    }

    private static int binarySearchRecursive(int[] sortedValues, int target, int low, int high) {
        if (low > high) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if (sortedValues[mid] == target) {
            return mid;
        }
        if (sortedValues[mid] < target) {
            return binarySearchRecursive(sortedValues, target, mid + 1, high);
        }
        return binarySearchRecursive(sortedValues, target, low, mid - 1);
    }

    public static void countdown(int value) {
        if (value < 0) {
            return;
        }
        System.out.println(value);
        countdown(value - 1);
    }
}
