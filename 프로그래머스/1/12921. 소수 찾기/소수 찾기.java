class Solution {
    public int solution(int n) {
        int answer = 0;
        boolean[] isNotPrime = new boolean[n + 1];

        isNotPrime[0] = isNotPrime[1] = true;

        for (int i = 2; i * i <= n; i++) {
            if (!isNotPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }
        for (int i = 2; i <= n; i++) {
            if (!isNotPrime[i]) {
                answer++;
            }
        }
        return answer;
    }
}