package portfolio.oop;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** Simple bank account model demonstrating encapsulation and validation. */
public class BankAccount {
    private final int accountNumber;
    private BigDecimal balance;

    public BankAccount(int accountNumber, BigDecimal openingBalance) {
        if (openingBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("opening balance cannot be negative");
        }
        this.accountNumber = accountNumber;
        this.balance = openingBalance.setScale(2, RoundingMode.HALF_UP);
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void deposit(BigDecimal amount) {
        requirePositive(amount, "deposit");
        balance = balance.add(amount).setScale(2, RoundingMode.HALF_UP);
    }

    public void withdraw(BigDecimal amount) {
        requirePositive(amount, "withdrawal");
        if (amount.compareTo(balance) > 0) {
            throw new IllegalArgumentException("insufficient funds");
        }
        balance = balance.subtract(amount).setScale(2, RoundingMode.HALF_UP);
    }

    public void transferTo(BankAccount destination, BigDecimal amount) {
        withdraw(amount);
        destination.deposit(amount);
    }

    private static void requirePositive(BigDecimal amount, String action) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(action + " amount must be positive");
        }
    }

    @Override
    public String toString() {
        return "Account %d balance: $%s".formatted(accountNumber, balance);
    }
}
