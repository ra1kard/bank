package org.example.fileSystem.task6_v2;

import java.util.HashMap;
import java.util.Map;

public class Start {

    public static void main(String[] args) {

        UserRepository userRepository = new UserRepository();
        AccountRepository accountRepository = new AccountRepository();
        UserService userService = new UserService(userRepository, accountRepository);

        //подразумеваем, что создаем Юзеров только через репозиторий
        User katya = new User(1, "Kanova Katya", "79552223310");
        userRepository.add(katya);
        userRepository.add(new User(2, "Danova Daria", "79552223311"));
        userRepository.add(new User(3, "Panov Petya", "79552223312"));
        userRepository.add(new User(4, "Vanov Vasya", "79552223313"));
        userRepository.printFile();
        userRepository.getById(2);
        userRepository.remove(3);
        userRepository.remove(katya);
        userRepository.printFile();

        //подразумеваем, что создаем Счета только через репозиторий (пусть пока сразу с балансом, потом сдел изменение)
        accountRepository.add(new DepositAccount(105201, 44000));
        accountRepository.add(new DepositAccount(105202, 3000));
        accountRepository.add(new DepositAccount(105203, 283100));
        accountRepository.add(new DepositAccount(105204, 140000));

        accountRepository.add(new CreditAccount(105205, 3000, 155000, 15));
        accountRepository.add(new CreditAccount(105206, -113000, -115000, 27));
        accountRepository.add(new CreditAccount(105207, -333000, 999000, 27));
        accountRepository.add(new CreditAccount(105208, 222000, 1555000, 6));

        accountRepository.add(new SavingAccount(105209, 1222000, 10));
        accountRepository.add(new SavingAccount(105210, 7555000, 15));
        Account savingAccount105211 = new SavingAccount(105211, 555000, 15);
        accountRepository.add(savingAccount105211);
        accountRepository.add(new SavingAccount(105212, 15000, 15));

        accountRepository.add(new FuelLoyaltyAccount(205201, 100, 3));
        accountRepository.add(new FuelLoyaltyAccount(205202, 0, 5));
        accountRepository.add(new FuelLoyaltyAccount(205203, 0, 10));

        accountRepository.add(new StoreFiftyLoyaltyAccount(305201, 50, 5));
        accountRepository.add(new StoreFiftyLoyaltyAccount(305202, 50, 10));
        accountRepository.add(new StoreFiftyLoyaltyAccount(305203, 50, 15));

        accountRepository.getById(105210);
        System.out.println("проверка getById: " + accountRepository.getById(105210));
        accountRepository.remove(savingAccount105211);

        //добавим юзерам счета
        //userRepository.getById(1).addAccount(accountRepository.getById(105201));
        //userRepository.getById(1).addAccount(accountRepository.getById(105202));
        //userRepository.getById(1).addAccount(accountRepository.getById(105203));
        //userRepository.getById(1).addAccount(accountRepository.getById(205201));
        //userRepository.getById(1).addAccount(accountRepository.getById(305201));

        /*userRepository.getById(3).addAccount(accountRepository.getById(105202));
        userRepository.getById(3).addAccount(accountRepository.getById(105206));
        userRepository.getById(3).addAccount(accountRepository.getById(105210));
        userRepository.getById(3).addAccount(accountRepository.getById(205202));
        userRepository.getById(3).addAccount(accountRepository.getById(305202));*/

        //выведем полную информацию по юзеру и его счетам
        //printInfoUser(userRepository.getById(1));
        //printInfoUser(userRepository.getById(3));

        //ИТЕРАЦИЯ 1
        //попробуем внести/снять деньги на счет НАПРЯМУЮ
        /*printInfoUser(userRepository.getById(1));
        accountRepository.getById(105201).deposit(100);
        accountRepository.getById(105205).withdraw(5000);
        accountRepository.getById(105209).withdraw(1000);
        printInfoUser(userRepository.getById(1));*/

        //ИТЕРАЦИЯ 2
        //попробуем перевод через Сервис от юзераА к юзеруБ (другу на счет)
        /*printInfoUser(userRepository.getById(1));
        printInfoUser(userRepository.getById(3));
        userService.transferMoney(
                105201,
                105202,
                4000
        );
        printInfoUser(userRepository.getById(1));
        printInfoUser(userRepository.getById(3));*/

        //ИТЕРАЦИЯ 3
        //попробуем перевод через Сервис со счета юзераА, на счет юзераА (свой счет)
        /*printInfoUser(userRepository.getById(3));
        userService.transferMoney(
                105206,
                105210,
                2000
        );
        printInfoUser(userRepository.getById(3));*/

        //ИТЕРАЦИЯ 4
        //пополним карту заправки и спишем
        /*printInfoUser(userRepository.getById(1));
        userService.refuelCar(false, 20, 59, 205201);
        printInfoUser(userRepository.getById(1));*/

        //ИТЕРАЦИЯ 5
        //пополним карту заправки и спишем
        /*printInfoUser(userRepository.getById(1));
        userService.buyItem(false, 250, 305201);
        printInfoUser(userRepository.getById(1));*/
    }

    /*public static void printInfoUser(User user) {
        System.out.println();
        System.out.println("Информация по юзеру " + user.getFullName() + ":");
        System.out.println("◦ id: " + user.getId() + ", " + user);
        System.out.println("Выведем список счетов " + user.getFullName() + ":");

        HashMap<Integer, Account> mapAccountss = new HashMap<>();
        mapAccountss.putAll(user.getMapAccounts());
        for (Map.Entry<Integer, Account> entry: mapAccountss.entrySet()) {
            System.out.println(entry.getValue().getName() + ", №"
                    + entry.getValue().getNumber() + ", баланс: "
                    + entry.getValue().getBalance());
        }

        System.out.println();
    }*/

}
