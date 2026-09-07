class Solution {
    public int solution(String[] strs, String t) {
        int n = t.length();
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            if (dp[i] == Integer.MAX_VALUE) {
                continue;
            }

            for (String str : strs) {
                int len = str.length();

                if (i + len <= n && t.startsWith(str, i)) {
                    dp[i + len] = Math.min(dp[i + len], dp[i] + 1);
                }
            }
        }

        return dp[n] == Integer.MAX_VALUE ? -1 : dp[n];
    }
}