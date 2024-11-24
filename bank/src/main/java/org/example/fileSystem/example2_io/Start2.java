package org.example.fileSystem.example2_io;

import java.io.*;

//тест чтения через BufferedReader и FileReader
public class Start2 {

    public static void main(String[] args) throws IOException {
        //создадим директорию
        File folder = new File("/Users/s.kufarev/fileSystemTest/test2/");
        folder.mkdir();

        //создаем файл
        File originalFile = new File("/Users/s.kufarev/fileSystemTest/test2/aa.txt");
        originalFile.createNewFile();

        //чтение файла (запишем в файл что-то сами через блокнот "LisA by test2")
        BufferedReader reader = new BufferedReader(new FileReader("/Users/s.kufarev/fileSystemTest/test2/aa.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }

}
