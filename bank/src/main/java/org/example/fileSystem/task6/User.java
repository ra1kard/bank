package org.example.fileSystem.task6;

import java.util.HashMap;

public class User {
    private final int id;
    private String fullName;
    private String phoneNumber;
    private HashMap<Integer, Account> mapAccounts;

    public User(int id, String fullName, String phoneNumber) {
        mapAccounts = new HashMap<>();
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void addAccount(Account account) {
        mapAccounts.put(account.getNumber(), account);
    }

    public Account getAccount(int number) {
        return mapAccounts.get(number);
    }

    public HashMap getMapAccounts() {
        return mapAccounts;
    }

    @Override
    public String toString() {
        return "fullName: " + getFullName()
                + ",  phoneNumber: " + getPhoneNumber();
    }

}
