import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int h = sc.nextInt();
        int w = sc.nextInt();

        double bmi = 10000.0 * w / (h * h);

        System.out.println((int) bmi);

        if (bmi >= 25) {
            System.out.println("Obesity");
        }
    }
}