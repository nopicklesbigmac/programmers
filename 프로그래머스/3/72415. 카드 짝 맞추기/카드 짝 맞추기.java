import java.util.*;

class Solution {
    int answer = Integer.MAX_VALUE;
    int[][] board;

    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};

    public int solution(int[][] board, int r, int c) {
        this.board = board;

        dfs(r, c, 0);

        return answer;
    }

    void dfs(int r, int c, int count) {
        if (count >= answer) return;

        boolean finished = true;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != 0) {
                    finished = false;
                    break;
                }
            }
            if (!finished) break;
        }

        if (finished) {
            answer = Math.min(answer, count);
            return;
        }

        for (int type = 1; type <= 6; type++) {
            int r1 = -1, c1 = -1;
            int r2 = -1, c2 = -1;

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (board[i][j] == type) {
                        if (r1 == -1) {
                            r1 = i;
                            c1 = j;
                        } else {
                            r2 = i;
                            c2 = j;
                        }
                    }
                }
            }

            if (r1 == -1 || r2 == -1) continue;

            int d1 = bfs(r, c, r1, c1);
            int d2 = bfs(r1, c1, r2, c2);

            board[r1][c1] = 0;
            board[r2][c2] = 0;

            dfs(r2, c2, count + d1 + d2 + 2);

            board[r1][c1] = type;
            board[r2][c2] = type;

            d1 = bfs(r, c, r2, c2);
            d2 = bfs(r2, c2, r1, c1);

            board[r1][c1] = 0;
            board[r2][c2] = 0;

            dfs(r1, c1, count + d1 + d2 + 2);

            board[r1][c1] = type;
            board[r2][c2] = type;
        }
    }

    int bfs(int sr, int sc, int er, int ec) {
        int[][] dist = new int[4][4];

        for (int[] row : dist) {
            Arrays.fill(row, -1);
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sr, sc});
        dist[sr][sc] = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int r = cur[0];
            int c = cur[1];

            if (r == er && c == ec) {
                return dist[r][c];
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr >= 0 && nr < 4 && nc >= 0 && nc < 4) {
                    if (dist[nr][nc] == -1) {
                        dist[nr][nc] = dist[r][c] + 1;
                        queue.offer(new int[]{nr, nc});
                    }
                }

                nr = r;
                nc = c;

                while (true) {
                    int tr = nr + dr[d];
                    int tc = nc + dc[d];

                    if (tr < 0 || tr >= 4 || tc < 0 || tc >= 4) {
                        break;
                    }

                    nr = tr;
                    nc = tc;

                    if (board[nr][nc] != 0) {
                        break;
                    }
                }

                if (dist[nr][nc] == -1) {
                    dist[nr][nc] = dist[r][c] + 1;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        return -1;
    }
}