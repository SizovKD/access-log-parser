package org.example;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String path;
        File file;
        boolean fileExists;
        boolean isDirectory;
        int i = 1;
        while (true) {
            System.out.println("Введите путь:");
            path = new Scanner(System.in).nextLine();
            file = new File(path);
            isDirectory = file.isDirectory();
            fileExists = file.exists();
            if (isDirectory) {
                System.out.println("Указанный путь является путём к папке, а не к файлу");
                continue;
            }
            if (fileExists) {
                System.out.println("Путь указан верно");
                System.out.println("Это файл номер " + i++);
            } else {
                System.out.println("Указанного файла не существует или путь указан некорректно");
            }

        }
    }
}