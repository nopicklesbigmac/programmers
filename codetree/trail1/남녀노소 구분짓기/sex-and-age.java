import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int gender = sc.nextInt();
        int age = sc.nextInt();

        if (gender == 0) {
            if (age >= 19) {
                System.out.println("MAN");
            } else {
                System.out.println("BOY");
            }
        } else {
            if (age >= 19) {
                System.out.println("WOMAN");
            } else {
                System.out.println("GIRL");
            }
        }
    }
}