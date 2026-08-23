import java.util.*;
class Solution {
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int[][] map = new int[102][102];

        for (int[] r : rectangle) {
            for (int x = r[0] * 2; x <= r[2] * 2; x++) {
                for (int y = r[1] * 2; y <= r[3] * 2; y++) {
                    map[x][y] = 1;
                }
            }
        }

        for (int[] r : rectangle) {
            for (int x = r[0] * 2 + 1; x < r[2] * 2; x++) {
                for (int y = r[1] * 2 + 1; y < r[3] * 2; y++) {
                    map[x][y] = 0;
                }
            }
        }

        int[][] dist = new int[102][102];
        for (int[] row : dist) {
            Arrays.fill(row, -1);
        }

        int sx = characterX * 2;
        int sy = characterY * 2;
        int ex = itemX * 2;
        int ey = itemY * 2;

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sx, sy});
        dist[sx][sy] = 0;

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if (cur[0] == ex && cur[1] == ey) {
                return dist[cur[0]][cur[1]] / 2;
            }

            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];

                if (nx < 0 || nx >= 102 || ny < 0 || ny >= 102) {
                    continue;
                }

                if (map[nx][ny] == 1 && dist[nx][ny] == -1) {
                    dist[nx][ny] = dist[cur[0]][cur[1]] + 1;
                    q.offer(new int[]{nx, ny});
                }
            }
        }

        return 0;
    }
}