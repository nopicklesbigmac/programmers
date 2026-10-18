public class Main {
    public static void main(String[] args) {
        // Please write your code here.
        int a = 1;
        int b = 2;
        int c = 3;

        a = a + b + c;
        b = a;
        c = a;

        System.out.println(a + " " + b + " " + c);
    }
}