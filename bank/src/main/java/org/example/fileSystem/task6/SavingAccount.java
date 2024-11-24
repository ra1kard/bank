package org.example.fileSystem.task6;

public class SavingAccount extends Account {
    private int percentPlus;
    private final TypeAccount typeAccount = TypeAccount.SAVING;

    public SavingAccount(int number, double balance, int percentPlus) {
        super(number, balance);
        this.percentPlus = percentPlus;
    }

    public double getDiscountPercent() {
        return percentPlus;
    }

    @Override
    public String getName() {
        return "◦ Накопительный счет";
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
