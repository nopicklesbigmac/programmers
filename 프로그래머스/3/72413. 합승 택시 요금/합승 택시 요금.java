class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        int INF = 1_000_000_000;
        int[][] dist = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dist[i][j] = i == j ? 0 : INF;
            }
        }

        for (int[] fare : fares) {
            int c = fare[0];
            int d = fare[1];
            int f = fare[2];

            dist[c][d] = f;
            dist[d][c] = f;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][k] == INF || dist[k][j] == INF) {
                        continue;
                    }

                    dist[i][j] = Math.min(
                        dist[i][j],
                        dist[i][k] + dist[k][j]
                    );
                }
            }
        }

        answer = INF;

        for (int k = 1; k <= n; k++) {
            if (dist[s][k] == INF || dist[k][a] == INF || dist[k][b] == INF) {
                continue;
            }

            answer = Math.min(
                answer,
                dist[s][k] + dist[k][a] + dist[k][b]
            );
        }

        return answer;
    }
}