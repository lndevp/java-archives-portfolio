package portfolio.oop;

import java.math.BigDecimal;

public class BankAccountDemo {
    public static void main(String[] args) {
        BankAccount chequing = new BankAccount(1234567, new BigDecimal("300.00"));
        BankAccount savings = new BankAccount(9876543, new BigDecimal("400.00"));

        chequing.withdraw(new BigDecimal("75.00"));
        savings.deposit(new BigDecimal("50.00"));
        savings.transferTo(chequing, new BigDecimal("100.00"));

        System.out.println(chequing);
        System.out.println(savings);
    }
}
