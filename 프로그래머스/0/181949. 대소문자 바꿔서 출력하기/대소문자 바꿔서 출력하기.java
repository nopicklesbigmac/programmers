import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
         if (a.length() >= 1 && a.length() <= 20) {

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < a.length(); i++) {
                char c = a.charAt(i);

                if (Character.isLowerCase(c)) {
                    sb.append(Character.toUpperCase(c));
                } else {
                    sb.append(Character.toLowerCase(c));
                }
            }

            System.out.println(sb.toString());
        }
            
    }
}