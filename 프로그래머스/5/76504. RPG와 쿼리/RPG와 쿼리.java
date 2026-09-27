import java.util.*;

class Solution {
    public long[] solution(int n, int z, int[][] roads, long[] queries) {
        int limit = z * z;
        int INF = 1000000000;

        int[][] dp = new int[n][limit + 1];
        int[] best = new int[limit + 1];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], INF);
        }

        Arrays.fill(best, INF);

        dp[0][0] = 0;
        best[0] = 0;

        for (int money = 1; money <= limit; money++) {
            int minTurn = INF;

            for (int[] road : roads) {
                int u = road[0];
                int v = road[1];
                int w = road[2];

                if (money < w) {
                    continue;
                }

                int prevMoney = money - w;
                int turn = INF;

                if (dp[u][prevMoney] != INF) {
                    turn = dp[u][prevMoney] + 1;
                }

                if (best[prevMoney] != INF) {
                    turn = Math.min(turn, best[prevMoney] + 2);
                }

                if (turn < dp[v][money]) {
                    dp[v][money] = turn;
                }

                minTurn = Math.min(minTurn, dp[v][money]);
            }

            best[money] = minTurn;
        }

        long[] answer = new long[queries.length];

        for (int i = 0; i < queries.length; i++) {
            long c = queries[i];

            if (c == 0) {
                answer[i] = 0;
                continue;
            }

            long result = Long.MAX_VALUE;

            int remain = (int)(c % z);
            long maxBase = Math.min(c, limit);

            for (long base = remain; base <= maxBase; base += z) {
                int b = (int)base;

                if (best[b] == INF) {
                    continue;
                }

                long turn = best[b] + (c - base) / z;

                result = Math.min(result, turn);
            }

            answer[i] = result == Long.MAX_VALUE ? -1 : result;
        }

        return answer;
    }
}