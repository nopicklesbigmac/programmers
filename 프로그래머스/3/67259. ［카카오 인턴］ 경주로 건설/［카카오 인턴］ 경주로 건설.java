import java.util.*;
class Solution {
    public int solution(int[][] board) {
        int answer = 0;
        int n = board.length;
        int[][][] cost = new int[n][n][4];
        for (int[][] a : cost) {
            for (int[] b : a) {
                Arrays.fill(b, Integer.MAX_VALUE);
            }
        }

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        Queue<int[]> queue = new LinkedList<>();

        for (int d = 0; d < 4; d++) {
            cost[0][0][d] = 0;
        }

        queue.offer(new int[]{0, 0, -1, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int r = cur[0];
            int c = cur[1];
            int dir = cur[2];
            int money = cur[3];

            for (int nd = 0; nd < 4; nd++) {
                int nr = r + dr[nd];
                int nc = c + dc[nd];

                if (nr < 0 || nr >= n || nc < 0 || nc >= n || board[nr][nc] == 1) {
                    continue;
                }

                int nextCost = money + 100;

                if (dir != -1 && dir != nd) {
                    nextCost += 500;
                }

                if (cost[nr][nc][nd] > nextCost) {
                    cost[nr][nc][nd] = nextCost;
                    queue.offer(new int[]{nr, nc, nd, nextCost});
                }
            }
        }

        answer = Integer.MAX_VALUE;

        for (int d = 0; d < 4; d++) {
            answer = Math.min(answer, cost[n - 1][n - 1][d]);
        }

        return answer;
    }
}