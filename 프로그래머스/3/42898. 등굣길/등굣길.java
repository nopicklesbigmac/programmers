class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        
        int MOD = 1000000007;
        int[][] dp = new int[n + 1][m + 1];
        boolean[][] water = new boolean[n + 1][m + 1];

        for (int[] puddle : puddles) {
            water[puddle[1]][puddle[0]] = true;
        }

        dp[1][1] = 1;

        for (int y = 1; y <= n; y++) {
            for (int x = 1; x <= m; x++) {
                if (water[y][x] || (x == 1 && y == 1)) {
                    continue;
                }

                dp[y][x] = (dp[y - 1][x] + dp[y][x - 1]) % MOD;
            }
        }

        answer = dp[n][m];
        return answer;
    }
}