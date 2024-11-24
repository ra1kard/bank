package org.example.fileSystem.task6;

public class CreditAccount extends Account {
    private double creditLimit;
    private int percentCredit;
    private final TypeAccount typeAccount = TypeAccount.CREDIT;

    CreditAccount(int number, double balance, double creditLimit, int percentCredit) {
        super(number, balance);
        this.creditLimit = creditLimit;
        this.percentCredit = percentCredit;
    }

    @Override
    public double getCreditLimit() {
        return creditLimit;
    }

    public double getPercentCredit() {
        return percentCredit;
    }

    @Override
    public String getName() {
        return "◦ Кредитный     счет";
    }

    @Override
    public String toString() {
        return "Номер счета: " + getNumber()
                + ",  баланс: " + getBalance();
    }

    @Override
    public TypeAccount getTypeAccount() {
        return typeAccount;
    }

}
