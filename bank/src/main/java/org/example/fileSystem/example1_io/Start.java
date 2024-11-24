package org.example.fileSystem.example1_io;

import java.io.File;
import java.io.IOException;

public class Start {

    public static void main(String[] args) throws IOException {
        //создаем файл
        File originalFile = new File("/Users/s.kufarev/fileSystemTest/aa.txt");

        //объект-директория
        File folder = originalFile.getParentFile();
        System.out.println();

        System.out.println(folder);
        System.out.println(folder.getParent());             //у этой папки запросим родителя
        System.out.println(originalFile.getParent());       //у этого файла запросим родителя
        System.out.println(originalFile.getParentFile());   //у этого файла запросим родителя
        System.out.println(originalFile.getAbsolutePath()); //Возвращает полный путь файла со всеми поддиректориями.
        System.out.println(originalFile.getName());         //имя файла

        System.out.println();

        //печать списка файлов в директории "fileSystemTest" на экран
        System.out.println("список файлов директории файла " + originalFile.length());
        for (File file : folder.listFiles()) {
            System.out.println(file.getName());
        }

        System.out.println();
        System.out.println(originalFile.isDirectory() + " - Является ли объект originalFile директорией");
        System.out.println(originalFile.isFile() + " - Является ли объект originalFile файлом");
        System.out.println(folder.isDirectory() + " - Является ли объект folder директорией");
        System.out.println(folder.isFile() + " - Является ли объект folder файлом");

        System.out.println();
        System.out.println(originalFile.length() + " - Возвращает размер/длину файла в байтах.");
        System.out.println(originalFile.createNewFile() + " - Создает файл. Если такой файл уже был, возвращает false.");

        System.out.println();
        File originalFile2 = new File("/Users/s.kufarev/fileSystemTest/test1/aaa.txt");
        File folder2 = originalFile2.getParentFile();
        System.out.println(folder2.mkdir() + " - Создает директорию. Название mkdir происходит от «make directory».");
        System.out.println(originalFile2.createNewFile() + " - Создает файл. Если такой файл уже был, возвращает false.");
        //System.out.println(originalFile2.delete() + " - Удаляет файл объекта на диске. Если объект – директория, то только, если в ней нет файлов.");
        //System.out.println(folder2.delete() + " - Удаляет файл объекта на диске. Если объект – директория, то только, если в ней нет файлов.");
    }

}
