import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] date = sc.nextLine().split("-");

        System.out.println(date[2] + "." + date[0] + "." + date[1]);
    }
}