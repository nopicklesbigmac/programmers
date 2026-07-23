import java.util.*;
class Solution {
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};

    public int solution(String[] maps) {
        int answer = -1;
        int n = maps.length;
        int m = maps[0].length();

        int[] start = null;
        int[] lever = null;
        int[] exit = null;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char c = maps[i].charAt(j);
                if (c == 'S') start = new int[] {i, j};
                else if (c == 'L') lever = new int[] {i, j};
                else if (c == 'E') exit = new int[] {i, j};
            }
        }

        int toLever = bfs(start[0], start[1], 'L', maps, n, m);
        if (toLever == -1) return -1;

        int toExit = bfs(lever[0], lever[1], 'E', maps, n, m);
        if (toExit == -1) return -1;

        answer = toLever + toExit;
        return answer;
    }

    private int bfs(int startR, int startC, char target, String[] maps, int n, int m) {
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[] {startR, startC, 0});
        visited[startR][startC] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            int dist = curr[2];

            if (maps[r].charAt(c) == target) {
                return dist;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr >= 0 && nr < n && nc >= 0 && nc < m) {
                    if (!visited[nr][nc] && maps[nr].charAt(nc) != 'X') {
                        visited[nr][nc] = true;
                        queue.offer(new int[] {nr, nc, dist + 1});
                    }
                }
            }
        }

        return -1;
    }
}