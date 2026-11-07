import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        System.out.println(n % 3 == 0 ? "YES" : "NO");
        System.out.println(n % 5 == 0 ? "YES" : "NO");
    }
}