package org.example.fileSystem.task6;

/**
 * Важно чтобы каждый класс банковского счета мог быть абстрагирован до интерфейса AccountOperation
 * - метод: deposit(double amount)         назначение: метод внесения денег на счет
 * - метод: withdraw(double amount)        назначение: метод снятия денег со счета
 * - метод: getBalance()                   назначение: метод получения текущего баланса счета
 */
public interface AccountOperation {

    void deposit(double amount);

    void withdraw(double amount);

    double getBalance();

    double spendBalanceLoyalty();

    void addBalanceLoyalty(double sumPurchase);

}
