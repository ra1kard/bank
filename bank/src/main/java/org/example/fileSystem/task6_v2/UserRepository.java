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
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Пользователи должны храниться в классе UserRepository
 * структуру для хранения данных выберите самостоятельно, исходя из удобства и оптимальности использования
 * - - -
 * Классы репозитории должны не только хранить данные
 * Но и обеспечивать возможность удаления, добавления, изменения, получения данных
 * Вся логика связанная с сохр данных должна располагаться именно в репозиториях
 */
public class UserRepository implements RepositoryOperation<User> {
    private Path directoryPath;
    private Path csvFilePath;

    UserRepository() {
        directoryPath = Paths.get("/Users", "/s.kufarev/IdeaProjects/bank/bank/src/main/resources");
        try {
            Files.createDirectories(directoryPath);                            // Создание каталога
            csvFilePath = directoryPath.resolve("UserRepository.csv");  // Путь к CSV-файлу
            try (
                    CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath.toFile(), false)) // true для добавления данных в конец файла
            ) {
                String[] header = {"id", "name", "phone"};
                writer.writeNext(header);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(User user) {
        String r1 = String.valueOf(user.getId());
        String r2 = String.valueOf(user.getFullName());
        String r3 = String.valueOf(user.getPhoneNumber());
        String[] row = {r1, r2, r3};
        try {
            try (
                    CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath.toFile(), true)) // true для добавления данных в конец файла
            ) {
                writer.writeNext(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getById(int id) {
        User foundUser = null;
        String targetId = Integer.toString(id);
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath.toFile()))) {
            String[] nextLine;
            boolean isFirstLine = true;     // Флаг для пропуска заголовка
            while ((nextLine = reader.readNext()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;    // Пропускаем заголовок
                    continue;
                }
                if (nextLine.length > 0 && nextLine[0].equalsIgnoreCase(targetId)) {
                    // в рамках задачи концепция: объект -> файл -> если достаем оттуда, создаем объект наружу, из файла
                    foundUser = new User(Integer.parseInt(nextLine[0]), nextLine[1], nextLine[2]);
                    break;
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return foundUser;
    }

    @Override
    public void remove(User object) {
        List<String[]> allRows;
        try (
                CSVReader reader = new CSVReader(new FileReader(csvFilePath.toFile()))
        ) {
            allRows = reader.readAll();             //Читаем все строки с помощью readAll, что возвращает список массивов строк
            allRows.remove(object.getId());         //процесс удаления
        } catch (CsvException | IOException e) {
            throw new RuntimeException(e);
        }

        try {                                       //запишем итог измененного листа в файл
            try (
                    CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath.toFile(), false)) // true для добавления данных в конец файла
            ) {
                for (int i = 0; i < allRows.size(); i++) {
                    writer.writeNext(allRows.get(i));
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) {
        List<String[]> allRows;
        try (
                CSVReader reader = new CSVReader(new FileReader(csvFilePath.toFile()))
        ) {
            allRows = reader.readAll();             //Читаем все строки с помощью readAll, что возвращает список массивов строк
            allRows.remove(id);                     //процесс удаления
        } catch (CsvException | IOException e) {
            throw new RuntimeException(e);
        }

        try {                                       //запишем итог измененного листа в файл
            try (
                    CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath.toFile(), false)) // true для добавления данных в конец файла
            ) {
                for (int i = 0; i < allRows.size(); i++) {
                    writer.writeNext(allRows.get(i));
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printFile() {
        try (
                CSVReader reader = new CSVReader(new FileReader(csvFilePath.toFile()))
        ) {
            List<String[]> allRows = reader.readAll();  //Читаем все строки с помощью readAll, что возвращает список массивов строк
            System.out.println("Содержимое CSV-файла:");
            for (String[] row2 : allRows) {
                for (String cell : row2) {
                    System.out.print(cell + " | ");     //Выводим содержимое, разделяя ячейки вертикальными чертами
                }
                System.out.println();
            }
        } catch (CsvException | IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println();
    }

}
