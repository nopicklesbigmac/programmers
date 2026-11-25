import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int B = sc.nextInt();
        int A = sc.nextInt();

        while (B >= A) {
            System.out.print(B + " ");
            B -= 2;
        }
    }
}