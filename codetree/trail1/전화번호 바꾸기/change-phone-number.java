import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] phone = sc.nextLine().split("-");

        System.out.println(phone[0] + "-" + phone[2] + "-" + phone[1]);
    }
}