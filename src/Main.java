import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите 1-е число и нажмите <Enter>: ");
        int x = new Scanner(System.in).nextInt();
        System.out.println("Введите 2-е число и нажмите <Enter>: ");
        int y = new Scanner(System.in).nextInt();
        String sum = "Сумма:";
        String difference = "Разность:";
        String product = "Произведение:";
        String quotient = "Частное:";
        System.out.println(sum+(x+y));
        System.out.println(difference+(x-y));
        System.out.println(product+(x*y));
        System.out.println(quotient+((double) x/y));
    }
}
