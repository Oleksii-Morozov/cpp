package dev.alerix.transaction;

import dev.alerix.service.AccountOperations;

import java.util.Random;

public class Transaction implements Runnable {
    private final AccountOperations account;
    private static final Random random = new Random();

    public Transaction(AccountOperations account) {
        this.account = account;
    }

    @Override
    public void run() {
        //noinspection InfiniteLoopStatement
        while (true) {
            makeTransaction();
            try {
                //noinspection BusyWait
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
        }
    }

    private void makeTransaction() {
        if (random.nextBoolean()) {
            account.deposit(random.nextInt(100)); // Deposit a random amount
        } else {
            account.withdraw(random.nextInt(100)); // Withdraw a random amount
        }
    }
}

