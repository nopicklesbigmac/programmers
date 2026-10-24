import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] time = sc.nextLine().split(":");

        int h = Integer.parseInt(time[0]);
        int m = Integer.parseInt(time[1]);

        System.out.println((h + 1) + ":" + m);
    }
}