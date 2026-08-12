import java.util.Arrays;
class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);

        int a = 0;
        int b = 0;
        int answer = 0;

        while (a < A.length && b < B.length) {
            if (B[b] > A[a]) {
                answer++;
                a++;
                b++;
            } else {
                b++;
            }
        }

        return answer;
    }
}