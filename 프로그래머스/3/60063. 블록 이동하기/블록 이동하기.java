import java.util.*;
class Solution {
    public int solution(int[][] board) {
        int n = board.length;

        boolean[][][] visited = new boolean[n][n][2];
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{0, 0, 0, 0});
        visited[0][0][0] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int r = cur[0];
            int c = cur[1];
            int dir = cur[2];
            int time = cur[3];

            int r2 = r + (dir == 1 ? 1 : 0);
            int c2 = c + (dir == 0 ? 1 : 0);

            if ((r == n - 1 && c == n - 1) ||
                (r2 == n - 1 && c2 == n - 1)) {
                return time;
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                int nr2 = r2 + dr[d];
                int nc2 = c2 + dc[d];

                if (nr < 0 || nr >= n || nc < 0 || nc >= n ||
                    nr2 < 0 || nr2 >= n || nc2 < 0 || nc2 >= n) {
                    continue;
                }

                if (board[nr][nc] == 1 || board[nr2][nc2] == 1) {
                    continue;
                }

                if (!visited[nr][nc][dir]) {
                    visited[nr][nc][dir] = true;
                    queue.offer(new int[]{nr, nc, dir, time + 1});
                }
            }

            if (dir == 0) {
                if (r - 1 >= 0 &&
                    board[r - 1][c] == 0 &&
                    board[r - 1][c + 1] == 0) {

                    if (!visited[r - 1][c][1]) {
                        visited[r - 1][c][1] = true;
                        queue.offer(new int[]{r - 1, c, 1, time + 1});
                    }

                    if (!visited[r - 1][c + 1][1]) {
                        visited[r - 1][c + 1][1] = true;
                        queue.offer(new int[]{r - 1, c + 1, 1, time + 1});
                    }
                }

                if (r + 1 < n &&
                    board[r + 1][c] == 0 &&
                    board[r + 1][c + 1] == 0) {

                    if (!visited[r][c][1]) {
                        visited[r][c][1] = true;
                        queue.offer(new int[]{r, c, 1, time + 1});
                    }

                    if (!visited[r][c + 1][1]) {
                        visited[r][c + 1][1] = true;
                        queue.offer(new int[]{r, c + 1, 1, time + 1});
                    }
                }
            } else {
                if (c - 1 >= 0 &&
                    board[r][c - 1] == 0 &&
                    board[r + 1][c - 1] == 0) {

                    if (!visited[r][c - 1][0]) {
                        visited[r][c - 1][0] = true;
                        queue.offer(new int[]{r, c - 1, 0, time + 1});
                    }

                    if (!visited[r + 1][c - 1][0]) {
                        visited[r + 1][c - 1][0] = true;
                        queue.offer(new int[]{r + 1, c - 1, 0, time + 1});
                    }
                }

                if (c + 1 < n &&
                    board[r][c + 1] == 0 &&
                    board[r + 1][c + 1] == 0) {

                    if (!visited[r][c][0]) {
                        visited[r][c][0] = true;
                        queue.offer(new int[]{r, c, 0, time + 1});
                    }

                    if (!visited[r + 1][c][0]) {
                        visited[r + 1][c][0] = true;
                        queue.offer(new int[]{r + 1, c, 0, time + 1});
                    }
                }
            }
        }

        return -1;
    }
}