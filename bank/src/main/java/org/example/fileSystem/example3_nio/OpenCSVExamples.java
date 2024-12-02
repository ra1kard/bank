package org.example.fileSystem.example3_nio;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class OpenCSVExamples {

    // Основной метод для демонстрации
    public static void main(String[] args) {
        String filePath = "data.csv";
        String tempFilePath = "data_temp.csv";
        String idToRemove = "3";

        // Пример очистки файла
        // clearCSVFile(filePath);

        // Пример удаления строки по id
        removeRowById(filePath, tempFilePath, idToRemove);

        // Дополнительно: после удаления можно заменить исходный файл временным
        /*
        try {
            java.nio.file.Files.delete(java.nio.file.Path.of(filePath));
            java.nio.file.Files.move(java.nio.file.Path.of(tempFilePath), java.nio.file.Path.of(filePath));
            System.out.println("Исходный файл обновлен.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }

    // Метод для очистки всего файла
    public static void clearCSVFile(String filePath) {
        try (FileWriter writer = new FileWriter(filePath, false)) {
            // Оставляем файл пустым
            System.out.println("Файл успешно очищен.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для удаления строки по id (загрузка всех строк)
    public static void removeRowById(String inputFilePath, String tempFilePath, String idToRemove) {
        try (
                CSVReader reader = new CSVReader(new FileReader(inputFilePath));
                CSVWriter writer = new CSVWriter(new FileWriter(tempFilePath))
        ) {
            List<String[]> allRows = reader.readAll();

            if (allRows.size() <= 1) {
                System.out.println("Файл пуст или содержит только заголовок.");
                return;
            }

            String[] header = allRows.get(0);
            writer.writeNext(header);

            List<String[]> filteredRows = allRows.stream()
                    .skip(1)
                    .filter(row -> !row[0].equals(idToRemove))
                    .collect(Collectors.toList());

            writer.writeAll(filteredRows);

            System.out.println("Строка с id = " + idToRemove + " успешно удалена.");
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

}
