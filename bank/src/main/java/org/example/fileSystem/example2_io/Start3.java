package org.example.fileSystem.example2_io;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

//тест чтения через Scanner
public class Start3 {

    public static void main(String[] args) throws IOException {
        //создадим директорию
        File folder = new File("/Users/s.kufarev/fileSystemTest/test3/");
        folder.mkdir();

        //создаем файл
        File originalFile = new File("/Users/s.kufarev/fileSystemTest/test3/aa.txt");
        originalFile.createNewFile();

        //чтение файла (запишем в файл что-то сами через блокнот "LisA by test3")
        Scanner scanner = new Scanner(new File("/Users/s.kufarev/fileSystemTest/test3/aa.txt"));
        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
        scanner.close();
    }

}
