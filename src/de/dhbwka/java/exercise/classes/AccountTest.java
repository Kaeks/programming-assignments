package de.dhbwka.java.exercise.classes;

import javax.naming.InsufficientResourcesException;

public class AccountTest {
    public static void main(String[] args) {
        Account account = new Account(4711, "Donald Duck", 500, 1000);
        System.out.println(account);
        account.deposit(200);
        System.out.println(account);
        try {
            account.pay(400);
        } catch (InsufficientResourcesException ire) {
            System.out.println("Insufficient funds.");
        }
        System.out.println(account);
        try {
            account.pay(2000);
        } catch (InsufficientResourcesException ire) {
            System.out.println("Insufficient funds.");
        }
        System.out.println(account);

    }
}
