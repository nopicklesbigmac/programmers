class Solution {
    public int solution(int h, String[] grid, int[][] panels, int[][] seqs) {
        int n = grid.length;
        int m = grid[0].length();
        int k = panels.length;

        int er = 0;
        int ec = 0;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (grid[r].charAt(c) == '@') {
                    er = r;
                    ec = c;
                }
            }
        }

        int[] required = new int[k];

        for (int[] seq : seqs) {
            int a = seq[0] - 1;
            int b = seq[1] - 1;
            required[b] |= 1 << a;
        }

        int[][] panelDist = new int[k][];
        for (int i = 0; i < k; i++) {
            int r = panels[i][1] - 1;
            int c = panels[i][2] - 1;
            panelDist[i] = bfs(grid, r, c);
        }

        int[] elevatorDist = bfs(grid, er, ec);

        int[][] move = new int[k][k];

        for (int i = 0; i < k; i++) {
            int floor1 = panels[i][0] - 1;

            for (int j = 0; j < k; j++) {
                int floor2 = panels[j][0] - 1;
                int r2 = panels[j][1] - 1;
                int c2 = panels[j][2] - 1;

                if (floor1 == floor2) {
                    move[i][j] = panelDist[i][r2 * m + c2];
                } else {
                    move[i][j] =
                            panelDist[i][er * m + ec]
                            + Math.abs(floor1 - floor2)
                            + elevatorDist[r2 * m + c2];
                }
            }
        }

        int size = 1 << k;
        int INF = Integer.MAX_VALUE / 4;

        int[][] dp = new int[size][k];

        for (int i = 0; i < size; i++) {
            java.util.Arrays.fill(dp[i], INF);
        }

        dp[0][0] = 0;

        for (int mask = 0; mask < size; mask++) {
            for (int cur = 0; cur < k; cur++) {
                if (dp[mask][cur] == INF) {
                    continue;
                }

                for (int next = 0; next < k; next++) {
                    if ((mask & (1 << next)) != 0) {
                        continue;
                    }

                    if ((required[next] & mask) != required[next]) {
                        continue;
                    }

                    int nextMask = mask | (1 << next);
                    int cost = dp[mask][cur] + move[cur][next];

                    if (cost < dp[nextMask][next]) {
                        dp[nextMask][next] = cost;
                    }
                }
            }
        }

        int answer = INF;
        int full = size - 1;

        for (int i = 0; i < k; i++) {
            answer = Math.min(answer, dp[full][i]);
        }

        return answer;
    }

    private int[] bfs(String[] grid, int sr, int sc) {
        int n = grid.length;
        int m = grid[0].length();

        int[] dist = new int[n * m];
        java.util.Arrays.fill(dist, -1);

        int[] queue = new int[n * m];
        int head = 0;
        int tail = 0;

        int start = sr * m + sc;
        dist[start] = 0;
        queue[tail++] = start;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (head < tail) {
            int cur = queue[head++];

            int r = cur / m;
            int c = cur % m;

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
                    continue;
                }

                if (grid[nr].charAt(nc) == '#') {
                    continue;
                }

                int next = nr * m + nc;

                if (dist[next] != -1) {
                    continue;
                }

                dist[next] = dist[cur] + 1;
                queue[tail++] = next;
            }
        }

        return dist;
    }
}