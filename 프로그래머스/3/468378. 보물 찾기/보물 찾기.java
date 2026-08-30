import java.util.function.Function;

class Solution {
    public int solution(int[] depth, int money, Function<Integer, Integer> excavate) {
        int n = depth.length;
        int[][] dp = new int[n][n];
        int[][] choice = new int[n][n];

        for (int len = 1; len <= n; len++) {
            for (int l = 0; l + len <= n; l++) {
                int r = l + len - 1;
                int best = Integer.MAX_VALUE;
                int bestPos = -1;

                for (int p = l; p <= r; p++) {
                    int left = p == l ? 0 : dp[l][p - 1];
                    int right = p == r ? 0 : dp[p + 1][r];

                    int cost = depth[p] + Math.max(left, right);

                    if (cost < best) {
                        best = cost;
                        bestPos = p;
                    }
                }

                dp[l][r] = best;
                choice[l][r] = bestPos;
            }
        }

        int l = 0;
        int r = n - 1;

        while (l <= r) {
            int p = choice[l][r];
            int result = excavate.apply(p + 1);

            if (result == 0) {
                return p + 1;
            }

            if (result == -1) {
                r = p - 1;
            } else {
                l = p + 1;
            }
        }

        return 0;
    }
}