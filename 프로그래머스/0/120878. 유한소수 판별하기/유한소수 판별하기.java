class Solution {
    public int solution(int a, int b) {
        int answer = 0;
        int gcd = getGCD(a, b);
        b /= gcd;
        while (b % 2 == 0) {
            b /= 2;
        }
        while (b % 5 == 0) {
            b /= 5;
        }
        if (b == 1) {
            answer = 1;
        } else {
            answer = 2;
        }

        return answer;
    }
    private int getGCD(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}