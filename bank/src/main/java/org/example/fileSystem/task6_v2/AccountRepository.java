package org.example.fileSystem.task6_v2;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Счета пользователей должны храниться в классе AccountRepository
 * структуру для хранения данных выберите самостоятельно, исходя из удобства и оптимальности использования
 * - - -
 * Классы репозитории должны не только хранить данные
 * Но и обеспечивать возможность удаления, добавления, изменения, получения данных
 * Вся логика связанная с сохр данных должна располагаться именно в репозиториях
 */
public class AccountRepository implements RepositoryOperation<Account> {
    private Path directoryPath;
    private Path csvFilePathDeposit;
    private Path csvFilePathCredit;
    private Path csvFilePathSaving;
    private Path csvFilePathFuel;
    private Path csvFilePathStore;

    AccountRepository() {
        directoryPath = Paths.get("/Users", "/s.kufarev/IdeaProjects/bank/bank/src/main/resources");
        try {
            Files.createDirectories(directoryPath);                                             // Создание каталога
            csvFilePathDeposit = directoryPath.resolve("AccountRepositoryDeposit.csv");   // Путь к CSV-файлу
            csvFilePathCredit = directoryPath.resolve("AccountRepositoryCredit.csv");     // Путь к CSV-файлу
            csvFilePathSaving = directoryPath.resolve("AccountRepositorySaving.csv");     // Путь к CSV-файлу
            csvFilePathFuel = directoryPath.resolve("AccountRepositoryFuel.csv");         // Путь к CSV-файлу
            csvFilePathStore = directoryPath.resolve("AccountRepositoryStore.csv");       // Путь к CSV-файлу
            try (
                    CSVWriter writer = new CSVWriter(new FileWriter(csvFilePathDeposit.toFile(), false)) // true для добавления данных в конец файла
            ) {
                String[] header = {"number", "balance"};
                writer.writeNext(header);
            }
            try (
                    CSVWriter writer = new CSVWriter(new FileWriter(csvFilePathCredit.toFile(), false)) // true для добавления данных в конец файла
            ) {
                String[] header = {"number", "balance", "creditLimit", "percentCredit"};
                writer.writeNext(header);
            }
            try (
                    CSVWriter writer = new CSVWriter(new FileWriter(csvFilePathSaving.toFile(), false)) // true для добавления данных в конец файла
            ) {
                String[] header = {"number", "balance", "percentPlus"};
                writer.writeNext(header);
            }
            try (
                    CSVWriter writer = new CSVWriter(new FileWriter(csvFilePathFuel.toFile(), false)) // true для добавления данных в конец файла
            ) {
                String[] header = {"number", "balance", "discountPercent"};
                writer.writeNext(header);
            }
            try (
                    CSVWriter writer = new CSVWriter(new FileWriter(csvFilePathStore.toFile(), false)) // true для добавления данных в конец файла
            ) {
                String[] header = {"number", "balance", "discountPercent"};
                writer.writeNext(header);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(Account account) {
        String r1 = String.valueOf(account.getNumber());
        String r2 = String.valueOf(account.getBalance());

        if (account.getTypeAccount() == TypeAccount.DEPOSIT) {
            String[] row = {r1, r2};
            try {
                try (
                        CSVWriter writer = new CSVWriter(new FileWriter(csvFilePathDeposit.toFile(), true)) // true для добавления данных в конец файла
                ) {
                    writer.writeNext(row);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (account.getTypeAccount() == TypeAccount.CREDIT) {
            String r3 = String.valueOf(account.getCreditLimit());
            String r4 = String.valueOf(account.getPercentCredit());
            String[] row = {r1, r2, r3, r4};
            try {
                try (
                        CSVWriter writer = new CSVWriter(new FileWriter(csvFilePathCredit.toFile(), true)) // true для добавления данных в конец файла
                ) {
                    writer.writeNext(row);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (account.getTypeAccount() == TypeAccount.SAVING) {
            String r3 = String.valueOf(account.getPercentPlus());
            String[] row = {r1, r2, r3};
            try {
                try (
                        CSVWriter writer = new CSVWriter(new FileWriter(csvFilePathSaving.toFile(), true)) // true для добавления данных в конец файла
                ) {
                    writer.writeNext(row);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (account.getTypeAccount() == TypeAccount.FUEL) {
            String r3 = String.valueOf(account.getDiscountPercent());
            String[] row = {r1, r2, r3};
            try {
                try (
                        CSVWriter writer = new CSVWriter(new FileWriter(csvFilePathFuel.toFile(), true)) // true для добавления данных в конец файла
                ) {
                    writer.writeNext(row);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            String r3 = String.valueOf(account.getDiscountPercent());
            String[] row = {r1, r2, r3};
            try {
                try (
                        CSVWriter writer = new CSVWriter(new FileWriter(csvFilePathStore.toFile(), true)) // true для добавления данных в конец файла
                ) {
                    writer.writeNext(row);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public Account getById(int id) {    //поиск во всех файлах
        Account foundAccount = null;
        String targetId = Integer.toString(id);

        //Депозит?
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePathDeposit.toFile()))) {
            String[] nextLine;
            boolean isFirstLine = true;     // Флаг для пропуска заголовка
            while ((nextLine = reader.readNext()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;    // Пропускаем заголовок
                    continue;
                }
                if (nextLine.length > 0 && nextLine[0].equalsIgnoreCase(targetId)) {
                    // в рамках задачи концепция: объект -> файл -> если достаем оттуда, создаем объект наружу, из файла
                    foundAccount = new DepositAccount(Integer.parseInt(nextLine[0]), Double.parseDouble(nextLine[1]));
                    break;
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        //Кредит?
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePathCredit.toFile()))) {
            String[] nextLine;
            boolean isFirstLine = true;     // Флаг для пропуска заголовка
            while ((nextLine = reader.readNext()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;    // Пропускаем заголовок
                    continue;
                }
                if (nextLine.length > 0 && nextLine[0].equalsIgnoreCase(targetId)) {
                    // в рамках задачи концепция: объект -> файл -> если достаем оттуда, создаем объект наружу, из файла
                    foundAccount = new CreditAccount(
                            Integer.parseInt(nextLine[0]),
                            Double.parseDouble(nextLine[1]),
                            Double.parseDouble(nextLine[2]),
                            Integer.parseInt(nextLine[3]));
                    break;
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        //Накопительный?
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePathSaving.toFile()))) {
            String[] nextLine;
            boolean isFirstLine = true;     // Флаг для пропуска заголовка
            while ((nextLine = reader.readNext()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;    // Пропускаем заголовок
                    continue;
                }
                if (nextLine.length > 0 && nextLine[0].equalsIgnoreCase(targetId)) {
                    // в рамках задачи концепция: объект -> файл -> если достаем оттуда, создаем объект наружу, из файла
                    foundAccount = new SavingAccount(
                            Integer.parseInt(nextLine[0]),
                            Double.parseDouble(nextLine[1]),
                            Integer.parseInt(nextLine[2]));
                    break;
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        //Счет карты лояльности заправки?
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePathFuel.toFile()))) {
            String[] nextLine;
            boolean isFirstLine = true;     // Флаг для пропуска заголовка
            while ((nextLine = reader.readNext()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;    // Пропускаем заголовок
                    continue;
                }
                if (nextLine.length > 0 && nextLine[0].equalsIgnoreCase(targetId)) {
                    // в рамках задачи концепция: объект -> файл -> если достаем оттуда, создаем объект наружу, из файла
                    foundAccount = new FuelLoyaltyAccount(
                            Integer.parseInt(nextLine[0]),
                            Double.parseDouble(nextLine[1]),
                            Integer.parseInt(nextLine[2]));
                    break;
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        //Счет карты лояльности магазина?
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePathStore.toFile()))) {
            String[] nextLine;
            boolean isFirstLine = true;     // Флаг для пропуска заголовка
            while ((nextLine = reader.readNext()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;    // Пропускаем заголовок
                    continue;
                }
                if (nextLine.length > 0 && nextLine[0].equalsIgnoreCase(targetId)) {
                    // в рамках задачи концепция: объект -> файл -> если достаем оттуда, создаем объект наружу, из файла
                    foundAccount = new FuelLoyaltyAccount(
                            Integer.parseInt(nextLine[0]),
                            Double.parseDouble(nextLine[1]),
                            Integer.parseInt(nextLine[2]));
                    break;
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        return foundAccount;
    }

    @Override
    public void remove(Account account) {
        List<String[]> allRows;

        //Депозит?
        if (account.getTypeAccount() == TypeAccount.DEPOSIT) {
            try (
                    CSVReader reader = new CSVReader(new FileReader(csvFilePathDeposit.toFile()))
            ) {
                allRows = reader.readAll();             //Читаем все строки с помощью readAll, что возвращает список массивов строк
                allRows.remove(account.getNumber());         //процесс удаления
            } catch (CsvException | IOException e) {
                throw new RuntimeException(e);
            }

            try {                                       //запишем итог измененного листа в файл
                try (
                        CSVWriter writer = new CSVWriter(new FileWriter(csvFilePathDeposit.toFile(), false)) // true для добавления данных в конец файла
                ) {
                    for (int i = 0; i < allRows.size(); i++) {
                        writer.writeNext(allRows.get(i));
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Кредитный?
        if (account.getTypeAccount() == TypeAccount.CREDIT) {
            try (
                    CSVReader reader = new CSVReader(new FileReader(csvFilePathCredit.toFile()))
            ) {
                allRows = reader.readAll();             //Читаем все строки с помощью readAll, что возвращает список массивов строк
                allRows.remove(account.getNumber());         //процесс удаления
            } catch (CsvException | IOException e) {
                throw new RuntimeException(e);
            }

            try {                                       //запишем итог измененного листа в файл
                try (
                        CSVWriter writer = new CSVWriter(new FileWriter(csvFilePathCredit.toFile(), false)) // true для добавления данных в конец файла
                ) {
                    for (int i = 0; i < allRows.size(); i++) {
                        writer.writeNext(allRows.get(i));
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Накопительный?
        if (account.getTypeAccount() == TypeAccount.SAVING) {
            try (
                    CSVReader reader = new CSVReader(new FileReader(csvFilePathSaving.toFile()))
            ) {
                allRows = reader.readAll();             //Читаем все строки с помощью readAll, что возвращает список массивов строк
                int count = 0;
                int goal = 0;
                for (String[] allRow : allRows) {
                    if (allRow[0].equalsIgnoreCase(String.valueOf(account.getNumber()))) {
                        goal = count;
                        break;
                    }
                    count++;
                }
                System.out.println("ОТВЕТ: " + goal);
                allRows.remove(goal);    //процесс удаления
            } catch (CsvException | IOException e) {
                throw new RuntimeException(e);
            }

            try {                                       //запишем итог измененного листа в файл
                try (
                        CSVWriter writer = new CSVWriter(new FileWriter(csvFilePathSaving.toFile(), false)) // true для добавления данных в конец файла
                ) {
                    for (int i = 0; i < allRows.size(); i++) {
                        writer.writeNext(allRows.get(i));
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Счет карты лояльности заправки?
        if (account.getTypeAccount() == TypeAccount.FUEL) {
            try (
                    CSVReader reader = new CSVReader(new FileReader(csvFilePathFuel.toFile()))
            ) {
                allRows = reader.readAll();             //Читаем все строки с помощью readAll, что возвращает список массивов строк
                allRows.remove(account.getNumber());         //процесс удаления
            } catch (CsvException | IOException e) {
                throw new RuntimeException(e);
            }

            try {                                       //запишем итог измененного листа в файл
                try (
                        CSVWriter writer = new CSVWriter(new FileWriter(csvFilePathFuel.toFile(), false)) // true для добавления данных в конец файла
                ) {
                    for (int i = 0; i < allRows.size(); i++) {
                        writer.writeNext(allRows.get(i));
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Счет карты лояльности магазина?
        if (account.getTypeAccount() == TypeAccount.STORE) {
            try (
                    CSVReader reader = new CSVReader(new FileReader(csvFilePathStore.toFile()))
            ) {
                allRows = reader.readAll();             //Читаем все строки с помощью readAll, что возвращает список массивов строк
                allRows.remove(account.getNumber());         //процесс удаления
            } catch (CsvException | IOException e) {
                throw new RuntimeException(e);
            }

            try {                                       //запишем итог измененного листа в файл
                try (
                        CSVWriter writer = new CSVWriter(new FileWriter(csvFilePathStore.toFile(), false)) // true для добавления данных в конец файла
                ) {
                    for (int i = 0; i < allRows.size(); i++) {
                        writer.writeNext(allRows.get(i));
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void remove(int id) {
    }

}
