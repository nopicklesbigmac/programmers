class Solution {
    public int[] solution(int numer1, int denom1, int numer2, int denom2) {
        int[] answer = {};
        int n = numer1 * denom2 + numer2 * denom1;
        int d = denom1 * denom2;
        for (int i = n; i >= 1; i--) {
            if (n % i == 0 && d % i == 0) {
                return new int[]{n / i, d / i};
            }
        }
        
        answer = new int[]{n, d};
        return answer;
    }
}