class Solution {
    public int[] solution(int n, int s) {
        int[] answer = {};
        if (s < n) {
            return new int[]{-1};
        }

        answer = new int[n];
        int quotient = s / n;
        int remainder = s % n;

        for (int i = 0; i < n - remainder; i++) {
            answer[i] = quotient;
        }

        for (int i = n - remainder; i < n; i++) {
            answer[i] = quotient + 1;
        }

        return answer;
    }
}