package dev.alerix;

/*
Create a multi-threaded Java application for working with banking transactions.
Customers can withdraw or deposit money to their bank account.
Implement the BankAccount class, which contains the methods withdraw and deposit methods for
withdrawing and depositing money, respectively.
Create a Transaction class that implements the Runnable interface.
In the constructor pass a BankAccount object, and the run method should randomly call the withdraw or deposit method.
Create many client threads that interact with one bank account.
Provide secure access to the account using appropriate synchronisation mechanisms.
 */

import dev.alerix.model.BankAccount;
import dev.alerix.transaction.Transaction;

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000); // Initial balance of 1000

        for (int i = 0; i < 10; i++) { // Create 10 client threads
            Transaction transaction = new Transaction(account);
            Thread clientThread = new Thread(transaction);
            clientThread.start();
        }
    }
}
