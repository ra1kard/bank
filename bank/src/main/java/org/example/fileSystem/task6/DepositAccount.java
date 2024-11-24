package org.example.fileSystem.task6;

public class DepositAccount extends Account {
    private final TypeAccount typeAccount = TypeAccount.DEPOSIT;

    DepositAccount(int number, double balance) {
        super(number, balance);
    }

    @Override
    public String getName() {
        return "◦ Депозитный    счет";
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
