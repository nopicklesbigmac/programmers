class Solution {
    public int solution(int n, int[] tops) {
        int answer = 0;
        int MOD = 10007;

        int[] dp = new int[n + 1];

        dp[0] = 1;
        dp[1] = tops[0] == 1 ? 4 : 3;

        for (int i = 2; i <= n; i++) {
            if (tops[i - 1] == 1) {
                dp[i] = (dp[i - 1] * 4 - dp[i - 2]) % MOD;
            } else {
                dp[i] = (dp[i - 1] * 3 - dp[i - 2]) % MOD;
            }

            if (dp[i] < 0) {
                dp[i] += MOD;
            }
        }

        answer = dp[n];
        return answer;
    }
}