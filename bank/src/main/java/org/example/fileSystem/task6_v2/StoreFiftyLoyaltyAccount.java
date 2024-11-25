package org.example.fileSystem.task6_v2;

public class StoreFiftyLoyaltyAccount extends Account {
    private final TypeAccount typeAccount = TypeAccount.STORE;

    StoreFiftyLoyaltyAccount(int number, double balance, int discountPercent) {
        super(number, balance, discountPercent);
    }

    @Override
    public String getName() {
        return "◦ КартаЛМагазин счет";
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
