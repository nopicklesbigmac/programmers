import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int count = 0;

        for (int i = 0; i < 3; i++) {
            String symptom = sc.next();
            int temp = sc.nextInt();

            if (symptom.equals("Y") && temp >= 37) {
                count++;
            }
        }

        if (count >= 2) {
            System.out.println("E");
        } else {
            System.out.println("N");
        }
    }
}