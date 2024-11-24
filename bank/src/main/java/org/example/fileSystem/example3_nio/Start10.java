package org.example.fileSystem.example3_nio;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.nio.file.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Start10 {

    //Запись и чтение CSV-файла с использованием библиотеки *OpenCSV*
    public static void main(String[] args) {

        Path directoryPath = Paths.get("/Users", "/s.kufarev/fileSystemTestNio/test4");
        try {
            // Создание каталога
            Files.createDirectories(directoryPath);
            System.out.println("Каталог создан или уже существует.");

            // Путь к CSV-файлу
            Path csvFilePath = directoryPath.resolve("test1.csv");

            // Запись данных в файл - Инициализация CSVWriter
            try (
                    CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath.toFile(), true)) // true для добавления данных в конец файла
            ) {
                // Запись заголовка, если файл пустой
                if (Files.size(csvFilePath) == 0) {
                    String[] header = {"Имя", "Возраст", "Страна"};
                    writer.writeNext(header);
                }

                // Запись данных
                String[] row1 = {"Иван Иванов", "25", "Россия"};
                String[] row2 = {"Мария Смирнова", "30", "Украина"};
                String[] row3 = {"John Doe", "28", "USA"};

                writer.writeNext(row1);
                writer.writeNext(row2);
                writer.writeNext(row3);

                System.out.println("Данные записаны в CSV-файл с использованием OpenCSV.");
                System.out.println();
            }

            // Чтение из CSV-файла - всей информации
            try (
                    CSVReader reader = new CSVReader(new FileReader(csvFilePath.toFile()))
            ) {
                List<String[]> allRows = reader.readAll();  //Читаем все строки с помощью readAll, что возвращает список массивов строк
                System.out.println("Содержимое CSV-файла:");
                for (String[] row : allRows) {
                    for (String cell : row) {
                        System.out.print(cell + " | ");     //Выводим содержимое, разделяя ячейки вертикальными чертами
                    }
                    System.out.println();
                }
            } catch (CsvException e) {
                throw new RuntimeException(e);
            }

            // Чтение и поиск определенной информации из csv файла
            System.out.println();
            System.out.println("Чтение определенной строки:");
            String targetName = "28";
            try (CSVReader reader = new CSVReader(new FileReader(csvFilePath.toFile()))) {
                String[] nextLine;
                boolean isFirstLine = true; // Флаг для пропуска заголовка

                while ((nextLine = reader.readNext()) != null) {
                    if (isFirstLine) {
                        isFirstLine = false; // Пропускаем заголовок
                        continue;
                    }

                    if (nextLine.length > 0 && nextLine[1].equalsIgnoreCase(targetName)) {
                        // Вывод найденной строки
                        System.out.println(String.join(",", nextLine));
                        System.out.println("Имя найденного: " + nextLine[0]);
                        System.out.println("Возраст найденного: " + nextLine[1]);
                        System.out.println("Страна найденного: " + nextLine[2]);
                        // Если требуется вывести с кавычками
                        // System.out.println("\"" + String.join("\",\"", nextLine) + "\"");
                        break; // Прерываем цикл после нахождения
                    }
                }
            } catch (IOException | CsvValidationException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
