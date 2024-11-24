package org.example.fileSystem.task6;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Пользователи должны храниться в классе UserRepository
 * структуру для хранения данных выберите самостоятельно, исходя из удобства и оптимальности использования
 * - - -
 * Классы репозитории должны не только хранить данные
 * Но и обеспечивать возможность удаления, добавления, изменения, получения данных
 * Вся логика связанная с сохр данных должна располагаться именно в репозиториях
 */
public class UserRepository implements RepositoryOperation<User> {
    private HashMap<Integer, User> mapUsers;

    UserRepository() {
        mapUsers = new HashMap<>();
        Path directoryPath = Paths.get("/Users", "/s.kufarev/IdeaProjects/bank/bank/src/main/resources");
        try {
            // Создание каталога
            Files.createDirectories(directoryPath);
            System.out.println("Каталог создан или уже существует.");

            // Путь к CSV-файлу
            Path csvFilePath = directoryPath.resolve("UserRepository.csv");

            // Запись данных в файл - Инициализация CSVWriter
            try (
                    CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath.toFile(), true)) // true для добавления данных в конец файла
            ) {
                // Запись заголовка, если файл пустой
                if (Files.size(csvFilePath) == 0) {
                    String[] header = {"id", "name", "phone"};
                    writer.writeNext(header);
                }

                System.out.println("Данные записаны в CSV-файл с использованием OpenCSV.");
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public void addUser(User user) {
        mapUsers.put(user.getId(), user);
    }

    public User getUserById(int id) {
        return mapUsers.get(id);
    }

    public void removeUser(Integer id) {
        mapUsers.remove(id);
    }*/

    @Override
    public void add(User user) {
        mapUsers.put(user.getId(), user);
    }

    @Override
    public User getById(int id) {
        return mapUsers.get(id);
    }

    @Override
    public void remove(User object) {
        mapUsers.remove(object.getId());
    }

    @Override
    public void remove(int id) {
        mapUsers.remove(id);
    }

}
