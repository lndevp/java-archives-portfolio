package portfolio;

import java.math.BigDecimal;
import java.util.Arrays;
import portfolio.algorithms.SearchAlgorithms;
import portfolio.algorithms.SortingAlgorithms;
import portfolio.oop.BankAccount;

public class PortfolioSmokeTest {
    public static void main(String[] args) {
        testSearching();
        testSorting();
        testBankAccount();
        System.out.println("All smoke tests passed.");
    }

    private static void testSearching() {
        int[] values = {1, 3, 5, 7, 9};
        require(SearchAlgorithms.linearSearch(values, 7) == 3, "linear search should find 7");
        require(SearchAlgorithms.binarySearch(values, 9) == 4, "binary search should find 9");
        require(SearchAlgorithms.binarySearch(values, 4) == -1, "binary search should return -1 when missing");
    }

    private static void testSorting() {
        int[] values = {5, 2, 9, 1, 5, 6};
        SortingAlgorithms.quickSort(values);
        require(Arrays.equals(values, new int[] {1, 2, 5, 5, 6, 9}), "quick sort should sort integers");

        String[] names = {"Delta", "alpha", "Charlie"};
        SortingAlgorithms.insertionSort(names);
        require(Arrays.equals(names, new String[] {"alpha", "Charlie", "Delta"}), "string insertion sort should ignore case");
    }

    private static void testBankAccount() {
        BankAccount account = new BankAccount(123, new BigDecimal("100.00"));
        account.deposit(new BigDecimal("25.00"));
        account.withdraw(new BigDecimal("40.00"));
        require(account.getBalance().compareTo(new BigDecimal("85.00")) == 0, "bank account balance should update");
    }

    private static void require(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
