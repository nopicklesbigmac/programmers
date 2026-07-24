import java.util.Arrays;
class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        int gcdA = arrayA[0];
        for (int i = 1; i < arrayA.length; i++) {
            gcdA = gcd(gcdA, arrayA[i]);
        }

        int gcdB = arrayB[0];
        for (int i = 1; i < arrayB.length; i++) {
            gcdB = gcd(gcdB, arrayB[i]);
        }

        if (canNotDivide(arrayB, gcdA)) {
            answer = Math.max(answer, gcdA);
        }

        if (canNotDivide(arrayA, gcdB)) {
            answer = Math.max(answer, gcdB);
        }

        return answer;
    }

    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    private boolean canNotDivide(int[] arr, int gcdVal) {
        for (int num : arr) {
            if (num % gcdVal == 0) {
                return false;
            }
        }
        return true;
    }
}