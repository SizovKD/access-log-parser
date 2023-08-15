import java.util.Scanner;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        String path = "";
        File file;
        boolean fileExists;
        boolean isDirectory;
        int i=0;
        int stringSum = 0;
        int lenghtMax = 0;
        int lenghtMin = 1024;
        while (true){
            System.out.println("Введите путь:");
            path = new Scanner(System.in).nextLine();
            file = new File(path);
            isDirectory=file.isDirectory();
            fileExists = file.exists();
            if (isDirectory){
                System.out.println("Указанный путь является путём к папке, а не к файлу");
                continue;
            }
            if (isDirectory==false&&fileExists==false) {
                System.out.println("Файл/папка не существует");
                continue;
            }
            if (fileExists) {
                System.out.println("Путь указан верно");
                System.out.println("Это файл номер "+(++i));

                try {
                    FileReader fileReader = new FileReader(path);
                    BufferedReader reader =
                            new BufferedReader(fileReader);
                    String line;
                    while ((line = reader.readLine()) != null) {
                        int length = line.length();
                        if(length>1024) throw new ExceededTheLimit("Количество символов в строке не должно превышать 1024");
                        if(length>lenghtMax) lenghtMax = length;
                        if(length<lenghtMin) lenghtMin = length;
                        stringSum+=1;
                    }
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
                System.out.println("Количество строк: " + stringSum);
                System.out.println("Длина самой длинной строки: " + lenghtMax);
                System.out.println("Длина самой короткой строки: " + lenghtMin);
            }
        }
    }

}