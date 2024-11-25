package org.example.fileSystem.task6_v2;

/**
 * 🟠 Вся логика связ с возм или невозм вып операций, ген или обр исключений
 * ✅ при этом в классе сервисе не должно храниться инф о счетах и пользователях,
 * ✅ а вместо этого должны существовать поля UserRepository и AccountRepository
 * 🟠 именно сервис занимается управлением логикой взаимодействия с пользователями и их счетами
 */
public class UserService {
    private UserRepository userRepository;
    private AccountRepository accountRepository;

    UserService(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    public void transferMoney(Integer idFromAccount, Integer idToAccount, double amount) {
        try {
            accountRepository.getById(idFromAccount).getBalance();   //поймать исключение без действия
            accountRepository.getById(idToAccount).getBalance();     //поймать исключение без действия
            try {
                validateAmountAccount(accountRepository.getById(idFromAccount), amount);
                accountRepository.getById(idFromAccount).withdraw(amount);
                accountRepository.getById(idToAccount).deposit(amount);
                System.out.println("Операция выполнена");
            } catch (NotEnoughAmountAccountException e) {
                throw new NotEnoughAmountAccountException("Wrong! Amount is not enough!");
            }
        } catch (NullPointerException e) {
            System.out.println("Account not found, операция отменена");
        }
    }

    public void validateAmountAccount(Account account, Double amount) throws NotEnoughAmountAccountException {
        if ((account.getTypeAccount() == TypeAccount.DEPOSIT || account.getTypeAccount() == TypeAccount.SAVING)
                && (account.getBalance() - amount) < 0) {
            throw new NotEnoughAmountAccountException("Недостаточно средств на счете");
        } else if (account.getTypeAccount() == TypeAccount.CREDIT
                && (account.getBalance() - amount) < account.getCreditLimit()) {
            throw new NotEnoughAmountAccountException("Недостаточно средств на счете");
        }
    }

    public double refuelCar(boolean WithdrawBonus, int liters, int costForLiter, Integer idFromAccount) {
        double res = 0;
        if (WithdrawBonus) {
            try {
                accountRepository.getById(idFromAccount).getBalance();   //поймать исключение без действия
                res = liters
                        * costForLiter
                        - accountRepository.getById(idFromAccount).spendBalanceLoyalty();
            } catch (NullPointerException e) {
                System.out.println("Account not found, операция отменена");
            }
        } else {
            try {
                accountRepository.getById(idFromAccount).getBalance();   //поймать исключение без действия
                accountRepository.getById(idFromAccount).addBalanceLoyalty(liters * costForLiter);
                res = liters * costForLiter;
            } catch (NullPointerException e) {
                System.out.println("Account not found, операция отменена");
            }
        }
        return res;
    }

    public double buyItem(boolean WithdrawBonus, double sumPurchase, Integer idFromAccount) {
        double res = 0;
        if (WithdrawBonus) {
            try {
                accountRepository.getById(idFromAccount).getBalance();   //поймать исключение без действия
                res = sumPurchase - accountRepository.getById(idFromAccount).spendBalanceLoyalty();
            } catch (NullPointerException e) {
                System.out.println("Account not found, операция отменена");
            }
        } else {
            try {
                accountRepository.getById(idFromAccount).getBalance();   //поймать исключение без действия
                accountRepository.getById(idFromAccount).addBalanceLoyalty(sumPurchase);
                res = sumPurchase;
            } catch (NullPointerException e) {
                System.out.println("Account not found, операция отменена");
            }
        }
        return res;
    }

}
