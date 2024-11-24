package org.example.fileSystem.example3_nio;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Start7 {

    public static void main(String[] args) {

        //Создание каталога `users/folder/`
        Path directoryPath = Paths.get("/Users", "/s.kufarev/fileSystemTestNio/test1");
        try {
            Files.createDirectories(directoryPath);
            System.out.println("Каталог создан или уже существует.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Создание файла `users/folder/test1.txt`
        Path filePath = directoryPath.resolve("a1.txt");
        try {
            if (Files.notExists(filePath)) {
                Files.createFile(filePath);
                System.out.println("Txt-файл создан.");
            } else {
                System.out.println("Txt-файл уже существует.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Запись строки в файл `users/folder/test1.txt`
        String content = "hello world, hello!";
        try {
            Files.write(filePath, content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
            System.out.println("Данные записаны в файл.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Чтение файла `users/folder/test1.txt`
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
