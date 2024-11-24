package org.example.fileSystem.example2_io;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Start5 {

    public static void main(String[] args) throws IOException {
        //создадим директорию
        File folder = new File("/Users/s.kufarev/fileSystemTest/test5/");
        folder.mkdir();

        //создаем файл
        File originalFile = new File("/Users/s.kufarev/fileSystemTest/test5/aa.txt");
        originalFile.createNewFile();

        //запись
        try(FileWriter writer = new FileWriter("/Users/s.kufarev/fileSystemTest/test5/aa.txt", false))
        {
            // запись всей строки
            String text = "Hello Gold!! !";
            writer.write(text);
            // запись по символам
            writer.append('\n');
            writer.append('E');
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

        //чтение файла
        try(FileReader reader = new FileReader("/Users/s.kufarev/fileSystemTest/test5/aa.txt"))
        {
            // читаем посимвольно
            int c;
            while((c=reader.read())!=-1){
                System.out.print((char)c);
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

}
