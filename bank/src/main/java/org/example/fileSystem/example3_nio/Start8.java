package org.example.fileSystem.example3_nio;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Start8 {

    public static void main(String[] args) {

        //Создание каталога `users/folder/`
        Path directoryPath = Paths.get("/Users", "/s.kufarev/fileSystemTestNio/test2");
        try {
            Files.createDirectories(directoryPath);
            System.out.println("Каталог создан или уже существует.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Создание файла `users/folder/test1.csv`
        Path filePath = directoryPath.resolve("test1.csv");
        try {
            if (Files.notExists(filePath)) {
                Files.createFile(filePath);
                System.out.println("CSV-файл создан.");
            } else {
                System.out.println("CSV-файл уже существует.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Запись строки в файл `users/folder/test1.csv` БЕЗ РАЗДЕЛЕНИЯ НА ЯЧЕЙКИ
        String content = "Name,Age,Country\nJohn Doe,30,USA\n";
        try {
            Files.write(filePath, content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
            System.out.println("Данные записаны в CSV-файл.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Чтение файла `users/folder/test1.csv`
        try {
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
