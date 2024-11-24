package org.example.fileSystem.task6;

import java.util.HashMap;

/**
 * Счета пользователей должны храниться в классе AccountRepository
 * структуру для хранения данных выберите самостоятельно, исходя из удобства и оптимальности использования
 * - - -
 * Классы репозитории должны не только хранить данные
 * Но и обеспечивать возможность удаления, добавления, изменения, получения данных
 * Вся логика связанная с сохр данных должна располагаться именно в репозиториях
 */
public class AccountRepository implements RepositoryOperation<Account> {
    private HashMap<Integer, Account> mapAccounts;

    AccountRepository() {
        mapAccounts = new HashMap<>();
    }

    @Override
    public void add(Account account) {
        mapAccounts.put(account.getNumber(), account);
    }

    @Override
    public Account getById(int id) {
        return mapAccounts.get(id);
    }

    @Override
    public void remove(Account account) {
        mapAccounts.remove(account.getNumber());
    }

    @Override
    public void remove(int id) {
        mapAccounts.remove(id);
    }

}
