package dev.alerix.model;

import dev.alerix.service.AccountOperations;
import dev.alerix.service.TransactionLogger;

public class BankAccount implements AccountOperations {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public synchronized void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            TransactionLogger.logTransaction(STR."Deposited: \{amount}, New Balance: \{balance}");
        }
    }

    @Override
    public synchronized void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            TransactionLogger.logTransaction(STR."Withdrawn: \{amount}, New Balance: \{balance}");
        }
    }
}

