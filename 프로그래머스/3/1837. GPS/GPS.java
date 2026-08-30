class Solution {
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        boolean[][] connected = new boolean[n + 1][n + 1];

        for (int[] edge : edge_list) {
            connected[edge[0]][edge[1]] = true;
            connected[edge[1]][edge[0]] = true;
        }

        int INF = 1000000;
        int[][] dp = new int[k][n + 1];

        for (int i = 0; i < k; i++) {
            java.util.Arrays.fill(dp[i], INF);
        }

        dp[0][gps_log[0]] = 0;

        for (int time = 1; time < k; time++) {
            for (int cur = 1; cur <= n; cur++) {
                int change = cur == gps_log[time] ? 0 : 1;

                for (int prev = 1; prev <= n; prev++) {
                    if (dp[time - 1][prev] == INF) {
                        continue;
                    }

                    if (prev == cur || connected[prev][cur]) {
                        dp[time][cur] = Math.min(
                            dp[time][cur],
                            dp[time - 1][prev] + change
                        );
                    }
                }
            }
        }

        int result = dp[k - 1][gps_log[k - 1]];

        return result == INF ? -1 : result;
    }
}