import java.util.*;

class Solution {
    static int m, n;
    static char[][] map;
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    public String solution(int M, int N, String[] board) {
        String answer = "";
        m = M + 2;
        n = N + 2;
        map = new char[m][n];

        for (int r = 0; r < m; r++) {
            Arrays.fill(map[r], '.');
        }

        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                map[r + 1][c + 1] = board[r].charAt(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        int total = 0;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (map[r][c] >= 'A' && map[r][c] <= 'Z') {
                    total++;
                }
            }
        }

        total /= 2;

        while (sb.length() < total) {
            boolean removed = false;

            for (char ch = 'A'; ch <= 'Z'; ch++) {
                int r1 = -1, c1 = -1;
                int r2 = -1, c2 = -1;

                for (int r = 1; r < m - 1; r++) {
                    for (int c = 1; c < n - 1; c++) {
                        if (map[r][c] == ch) {
                            if (r1 == -1) {
                                r1 = r;
                                c1 = c;
                            } else {
                                r2 = r;
                                c2 = c;
                            }
                        }
                    }
                }

                if (r1 == -1) continue;

                if (canConnect(r1, c1, r2, c2)) {
                    map[r1][c1] = '.';
                    map[r2][c2] = '.';
                    sb.append(ch);
                    removed = true;
                    break;
                }
            }

            if (!removed) {
                return "IMPOSSIBLE";
            }
        }

        
        answer = sb.toString();
        return answer;
    }

    static boolean canConnect(int sr, int sc, int er, int ec) {
        int[][][] dist = new int[m][n][4];

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                Arrays.fill(dist[r][c], 2);
            }
        }

        Deque<int[]> queue = new ArrayDeque<>();

        for (int d = 0; d < 4; d++) {
            int nr = sr + dr[d];
            int nc = sc + dc[d];

            if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;

            if (map[nr][nc] == '.' || (nr == er && nc == ec)) {
                dist[nr][nc][d] = 0;
                queue.offer(new int[]{nr, nc, d});
            }
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];
            int dir = cur[2];
            int turn = dist[r][c][dir];

            if (r == er && c == ec) {
                return true;
            }

            for (int nd = 0; nd < 4; nd++) {
                int nextTurn = turn + (dir == nd ? 0 : 1);

                if (nextTurn > 1) continue;

                int nr = r + dr[nd];
                int nc = c + dc[nd];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;

                if (map[nr][nc] != '.' && !(nr == er && nc == ec)) continue;

                if (dist[nr][nc][nd] > nextTurn) {
                    dist[nr][nc][nd] = nextTurn;

                    if (dir == nd) {
                        queue.offerFirst(new int[]{nr, nc, nd});
                    } else {
                        queue.offerLast(new int[]{nr, nc, nd});
                    }
                }
            }
        }

        return false;
    }
}