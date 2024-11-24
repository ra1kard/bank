package org.example.fileSystem.example3_nio;

import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.util.List;

public class Start9 {

    //Запись и чтение CSV-файла с использованием Java NIO (ручной подход)
    public static void main(String[] args) {

        Path directoryPath = Paths.get("/Users", "/s.kufarev/fileSystemTestNio/test3");
        try {
            // Создание каталога
            Files.createDirectories(directoryPath);
            System.out.println("Каталог создан или уже существует.");

            // Путь к CSV-файлу
            Path csvFilePath = directoryPath.resolve("a1.csv");

            // Создание CSV-файла, если он не существует
            if (Files.notExists(csvFilePath)) {
                Files.createFile(csvFilePath);
                System.out.println("CSV-файл создан.");
            } else {
                System.out.println("CSV-файл уже существует.");
            }

            // Запись данных в CSV-файл
            // Каждый элемент массива представляет собой ячейку (столбец)
            String[] header = {"Name", "Age", "Country"};
            String[] row1 = {"Ivan Ivanov", "25", "Russia"};
            String[] row2 = {"Maria Smirnova", "30", "Belarus"};
            String[] row3 = {"John Doe", "28", "USA"};

            // Запись заголовка
            writeCSVLine(csvFilePath, header);

            // Запись строк данных
            writeCSVLine(csvFilePath, row1);
            writeCSVLine(csvFilePath, row2);
            writeCSVLine(csvFilePath, row3);

            System.out.println("Данные записаны в CSV-файл.");

            // Чтение из CSV-файла
            List<String> csvLines = Files.readAllLines(csvFilePath, StandardCharsets.UTF_8);
            System.out.println("Содержимое CSV-файла:");
            for (String line : csvLines) {
                // Разделение строки на ячейки
                String[] cells = line.split(",");
                for (String cell : cells) {
                    System.out.print(cell.trim() + " | ");
                }
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Метод для записи одной строки в CSV-файл.
     *
     * @param filePath Путь к CSV-файлу
     * @param data     Массив строк, представляющий ячейки строки
     * @throws IOException Если происходит ошибка ввода/вывода
     *
     * Метод `writeCSVLine`:
     *     - Принимает путь к CSV-файлу и массив строк, представляющих ячейки строки.
     *     - Обрабатывает каждую ячейку: если она содержит запятые или кавычки, оборачивает её в кавычки и экранирует внутренние кавычки.
     *     - Соединяет ячейки с помощью запятых и добавляет символ новой строки.
     *     - Записывает полученную строку в файл, используя `StandardOpenOption.APPEND` для добавления.
     *
     * Запись и чтение:
     *     - Сначала записываем заголовок и несколько строк данных.
     *     - При чтении разделяем каждую строку по запятым и выводим ячейки, разделяя их вертикальной чертой для наглядности.
     *
     * Ограничения ручного подхода:
     *     - Экранирование символов: Вам нужно вручную обрабатывать запятые, кавычки и другие специальные символы в данных.
     *     - Производительность: При больших файлах может быть неэффективно считывать весь файл в память.
     *     - Функциональность: Ручная обработка ограничена по сравнению с возможностями специализированных библиотек.
     *
     */
    private static void writeCSVLine(Path filePath, String[] data) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            // Если данные содержат запятые или кавычки, оборачиваем их в кавычки и экранируем кавычки
            String cell = data[i];
            if (cell.contains(",") || cell.contains("\"")) {
                cell = "\"" + cell.replace("\"", "\"\"") + "\"";
            }
            sb.append(cell);
            if (i < data.length - 1) {
                sb.append(",");
            }
        }
        sb.append(System.lineSeparator());

        // Запись строки в файл с добавлением
        Files.write(filePath, sb.toString().getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        }

}
