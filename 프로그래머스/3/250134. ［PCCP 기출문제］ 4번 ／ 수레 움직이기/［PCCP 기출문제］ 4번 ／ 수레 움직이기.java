class Solution {
    int n, m;
    int[][] maze;
    int redStart, blueStart, redEnd, blueEnd;
    int answer = Integer.MAX_VALUE;

    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};

    public int solution(int[][] maze) {
        this.maze = maze;
        n = maze.length;
        m = maze[0].length;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                int pos = r * m + c;

                if (maze[r][c] == 1) redStart = pos;
                else if (maze[r][c] == 2) blueStart = pos;
                else if (maze[r][c] == 3) redEnd = pos;
                else if (maze[r][c] == 4) blueEnd = pos;
            }
        }

        dfs(redStart, blueStart, 1 << redStart, 1 << blueStart, 0);

        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

    void dfs(int red, int blue, int redVisited, int blueVisited, int count) {
        if (count >= answer) return;

        if (red == redEnd && blue == blueEnd) {
            answer = count;
            return;
        }

        for (int rd = 0; rd < 4; rd++) {
            int nextRed;

            if (red == redEnd) {
                nextRed = red;
            } else {
                nextRed = move(red, rd);
                if (nextRed == -1 || (redVisited & (1 << nextRed)) != 0) continue;
            }

            for (int bd = 0; bd < 4; bd++) {
                int nextBlue;

                if (blue == blueEnd) {
                    nextBlue = blue;
                } else {
                    nextBlue = move(blue, bd);
                    if (nextBlue == -1 || (blueVisited & (1 << nextBlue)) != 0) continue;
                }

                if (nextRed == nextBlue) continue;
                if (nextRed == blue && nextBlue == red) continue;

                int nextRedVisited = redVisited;
                int nextBlueVisited = blueVisited;

                if (red != redEnd) {
                    nextRedVisited |= 1 << nextRed;
                }

                if (blue != blueEnd) {
                    nextBlueVisited |= 1 << nextBlue;
                }

                dfs(
                    nextRed,
                    nextBlue,
                    nextRedVisited,
                    nextBlueVisited,
                    count + 1
                );
            }
        }
    }

    int move(int pos, int dir) {
        int r = pos / m;
        int c = pos % m;

        int nr = r + dr[dir];
        int nc = c + dc[dir];

        if (nr < 0 || nr >= n || nc < 0 || nc >= m) return -1;
        if (maze[nr][nc] == 5) return -1;

        return nr * m + nc;
    }
}