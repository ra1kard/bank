package org.example.fileSystem.task6_v2;

public interface RepositoryOperation<T> {

    /* //UserRepositoryOperation        - избавляемся от лишнего интерфейса, в пользу единого RepositoryOperation
    void addUser(User user);
    User getUserById(int id);
    void removeUser(Integer id);*/

    /* //AccountRepositoryOperation      - избавляемся от лишнего интерфейса, в пользу единого RepositoryOperation
    void addAccount(Account account);
    Account getAccountByNumber(int number);
    void removeAccount(Account account);*/

    void add(T object);

    T getById(int id);

    void remove(T object);

    void remove(int id);

}
