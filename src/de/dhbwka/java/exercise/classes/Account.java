package de.dhbwka.java.exercise.classes;

import javax.naming.InsufficientResourcesException;
import java.util.Locale;

public class Account {
    private long accountNumber;
    private String holderName;
    private int balance;
    private int limit;

    public Account(long accountNumber, String holderName, int balance, int limit) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
        this.limit = limit;
    }

    public Account(long accountNumber, String holderName, int limit) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = 0;
        this.limit = limit;
    }

    public Account(long accountNumber, String holderName) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = 0;
        this.limit = 1000;
    }

    public String toString() {
        return String.format(
                Locale.US,
                "Account: Number: %d, Holder: %s, Balance: $ %.2f, Limit: $ %.2f",
                this.accountNumber, this.holderName, this.balance / 100f, this.limit / 100f
        );
    }

    public void deposit(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot deposit a negative amount.");
        }
        if (amount == 0) {
            throw new IllegalArgumentException("Cannot deposit nothing.");
        }
        this.balance += amount;
    }

    public void pay(int amount) throws InsufficientResourcesException {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot pay a negative amount.");
        }
        if (amount == 0) {
            throw new IllegalArgumentException("Cannot pay nothing.");
        }
        if (this.balance + this.limit - amount < 0) {
            throw new InsufficientResourcesException("Insufficient funds.");
        }
        this.balance -= amount;
    }

    public int getBalance() { return this.balance; }
}
