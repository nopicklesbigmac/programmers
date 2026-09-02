class Solution {
    static int n, m, answer;
    static int[][] visited;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public int solution(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        answer = 0;

        visited = new int[n][m];
        visited[0][0] = 1;

        dfs(grid, 0, 0, 1);

        return answer;
    }

    static void dfs(int[][] grid, int r, int c, int dir) {
        if (r == n - 1 && c == m - 1) {
            if (turn(grid[r][c], dir) != -1 && valid(grid)) {
                answer++;
            }
            return;
        }

        int nr = r + dr[dir];
        int nc = c + dc[dir];

        if (nr < 0 || nr >= n || nc < 0 || nc >= m) return;
        if (grid[nr][nc] == -1) return;

        if (visited[nr][nc] > 1 && grid[nr][nc] != 3) return;

        boolean empty = grid[nr][nc] == 0;

        int[] tracks;

        if (empty) {
            tracks = placeable(dir);
        } else {
            tracks = new int[]{grid[nr][nc]};
        }

        for (int track : tracks) {
            int nextDir = turn(track, dir);

            if (nextDir == -1) continue;

            if (empty) {
                grid[nr][nc] = track;
            }

            visited[nr][nc]++;

            dfs(grid, nr, nc, nextDir);

            visited[nr][nc]--;

            if (empty) {
                grid[nr][nc] = 0;
            }
        }
    }

    static int[] placeable(int dir) {
        if (dir == 0) return new int[]{2, 3, 6, 7};
        if (dir == 1) return new int[]{1, 3, 4, 7};
        if (dir == 2) return new int[]{2, 3, 4, 5};
        return new int[]{1, 3, 5, 6};
    }

    static int turn(int track, int dir) {
        if (track == 1) {
            if (dir == 1 || dir == 3) return dir;
        }

        if (track == 2) {
            if (dir == 0 || dir == 2) return dir;
        }

        if (track == 3) {
            return dir;
        }

        if (track == 4) {
            if (dir == 1) return 0;
            if (dir == 2) return 3;
        }

        if (track == 5) {
            if (dir == 2) return 1;
            if (dir == 3) return 0;
        }

        if (track == 6) {
            if (dir == 0) return 1;
            if (dir == 3) return 2;
        }

        if (track == 7) {
            if (dir == 0) return 3;
            if (dir == 1) return 2;
        }

        return -1;
    }

    static boolean valid(int[][] grid) {
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (grid[r][c] > 0 && visited[r][c] == 0) {
                    return false;
                }

                if (grid[r][c] == 3 && visited[r][c] != 2) {
                    return false;
                }
            }
        }

        return true;
    }
}