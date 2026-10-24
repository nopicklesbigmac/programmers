import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String number = sc.nextLine();

        System.out.println(number.replace("-", ""));
    }
}