import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();

        BigDecimal result = BigDecimal.valueOf(a)
            .divide(BigDecimal.valueOf(b), 20, RoundingMode.DOWN);

        System.out.println(result.toPlainString());
    }
}