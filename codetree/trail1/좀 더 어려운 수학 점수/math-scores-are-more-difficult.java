import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int aMath = sc.nextInt();
        int aEng = sc.nextInt();
        int bMath = sc.nextInt();
        int bEng = sc.nextInt();
        if (aMath > bMath) {
            System.out.println("A");
        } else if (aMath < bMath) {
            System.out.println("B");
        } else if (aEng > bEng) {
            System.out.println("A");
        } else {
            System.out.println("B");
        }
    }
}