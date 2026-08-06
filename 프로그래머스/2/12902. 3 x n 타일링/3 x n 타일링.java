class Solution {
    public int solution(int n) {
        if (n % 2 != 0) {
            return 0;
        }

        long[] dp = new long[n + 1];
        long[] sum = new long[n + 1];
        
        long MOD = 1_000_000_007;

        dp[0] = 1;
        dp[2] = 3;
        sum[0] = dp[0];
        sum[2] = dp[0] + dp[2];

        for (int i = 4; i <= n; i += 2) {
            dp[i] = (dp[i - 2] * 3 + sum[i - 4] * 2) % MOD;
            sum[i] = (sum[i - 2] + dp[i]) % MOD;
        }

        return (int) dp[n];
    }
}