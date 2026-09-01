class Solution {
    static final long MOD = 1_000_000_007L;

    public int solution(int n) {
        long[] dp = new long[n + 1];

        dp[0] = 1;

        if (n >= 1) dp[1] = 1;
        if (n >= 2) dp[2] = 3;
        if (n >= 3) dp[3] = 10;
        if (n >= 4) dp[4] = 23;
        if (n >= 5) dp[5] = 62;
        if (n >= 6) dp[6] = 170;

        for (int i = 7; i <= n; i++) {
            dp[i] = (
                dp[i - 1]
                + 2 * dp[i - 2]
                + 6 * dp[i - 3]
                + dp[i - 4]
                - dp[i - 6]
                + MOD
            ) % MOD;
        }

        return (int) dp[n];
    }
}