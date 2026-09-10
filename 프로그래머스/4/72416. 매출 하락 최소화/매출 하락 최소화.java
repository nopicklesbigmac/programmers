import java.util.*;

class Solution {
    List<Integer>[] adj;
    int[] cost;
    int[][] dp;

    public int solution(int[] sales, int[][] links) {
        int n = sales.length;
        cost = sales;
        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] link : links) {
            adj[link[0]].add(link[1]);
        }

        dp = new int[n + 1][2];
        dfs(1);

        return Math.min(dp[1][0], dp[1][1]);
    }

    private void dfs(int u) {
        dp[u][0] = 0;
        dp[u][1] = cost[u - 1];

        boolean hasAttendingChild = false;
        int minExtraCost = Integer.MAX_VALUE;

        for (int v : adj[u]) {
            dfs(v);
            if (dp[v][0] < dp[v][1]) {
                dp[u][0] += dp[v][0];
                dp[u][1] += dp[v][0];
                minExtraCost = Math.min(minExtraCost, dp[v][1] - dp[v][0]);
            } else {
                dp[u][0] += dp[v][1];
                dp[u][1] += dp[v][1];
                hasAttendingChild = true;
            }
        }

        if (!adj[u].isEmpty() && !hasAttendingChild) {
            dp[u][0] += minExtraCost;
        }
    }
}