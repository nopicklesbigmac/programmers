import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int area = n * n;

        System.out.println(area);

        if (area < 25) {
            System.out.println("tiny");
        }
    }
}