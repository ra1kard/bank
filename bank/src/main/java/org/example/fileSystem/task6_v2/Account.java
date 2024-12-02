package org.example.fileSystem.task6_v2;

public abstract class Account implements AccountOperation {
    private final int number;
    private double balance;
    private String name;
    private TypeAccount typeAccount;
    private double creditLimit;
    private int percentCredit;
    private int discountPercent;
    private int percentPlus;

    Account(int number, double balance) {
        this.number = number;
        this.balance = balance;
    }

    Account(int number, double balance, int discountPercent) {
        this.number = number;
        this.balance = balance;
        this.discountPercent = discountPercent;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public int getPercentPlus() {
        return percentPlus;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) {
        this.balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

    public double spendBalanceLoyalty() {
        double res = balance;
        this.balance = 0;
        return res;
    }

    public void addBalanceLoyalty(double sumPurchase) {
        this.balance += sumPurchase * discountPercent / 100.0;
    }

    public TypeAccount getTypeAccount() {
        return typeAccount;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public int getPercentCredit() {
        return percentCredit;
    }

}
