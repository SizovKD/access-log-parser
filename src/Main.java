import java.util.Scanner;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        int i = 0;
        String path;
        Statistics statistics = new Statistics();
        int count = 0;
        while (true){
            System.out.println("Введите путь:");
            path = new Scanner(System.in).nextLine();
            File file = new File(path);
            boolean pathOfFile = file.exists();
            boolean isNotDirectory = !file.isDirectory();

            if (pathOfFile && isNotDirectory) {
                System.out.println("Путь указан верно");
                System.out.println("Это файл номер "+(++i));

                try {
                    FileReader fileReader = new FileReader(path);
                    BufferedReader reader =
                            new BufferedReader(fileReader);
                    String line;
                    while ((line = reader.readLine()) != null) {
                        int length = line.length();
                        if (length > 1024) {
                            throw new RuntimeException("Длина строки больше 1024");
                        }
                        LogEntry e = new LogEntry(line);
                        statistics.addEntry(e);
                    }
                    System.out.println("Общий трафик: " + statistics.totalTraffic + " bytes");
                    System.out.println("Минимальное время: " + statistics.minTime);
                    System.out.println("Максимальное время: " + statistics.maxTime);
                    System.out.println("Скорость трафика: " + statistics.getTrafficRate() + " bytes per hour");
                } catch(Exception ex){
                    ex.printStackTrace();
                }
            }
            else {
                System.out.println("Файл отсутствует или путь ведет к директории файла");
            }
        }
    }

}