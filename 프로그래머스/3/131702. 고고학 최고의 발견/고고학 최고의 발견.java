class Solution {
    int n;
    int answer = Integer.MAX_VALUE;
    int[][] original;
    int[][] board;

    int[] dr = {0, 0, 0, 1, -1};
    int[] dc = {0, 1, -1, 0, 0};

    public int solution(int[][] clockHands) {
        n = clockHands.length;
        original = clockHands;

        int[] first = new int[n];
        dfs(0, 0, first);

        return answer;
    }

    void dfs(int col, int count, int[] first) {
        if (count >= answer) {
            return;
        }

        if (col == n) {
            solve(first, count);
            return;
        }

        for (int i = 0; i < 4; i++) {
            first[col] = i;
            dfs(col + 1, count + i, first);
        }
    }

    void solve(int[] first, int count) {
        board = new int[n][n];

        for (int r = 0; r < n; r++) {
            board[r] = original[r].clone();
        }

        for (int c = 0; c < n; c++) {
            rotate(0, c, first[c]);
        }

        int total = count;

        for (int r = 1; r < n; r++) {
            for (int c = 0; c < n; c++) {
                int times = (4 - board[r - 1][c]) % 4;

                if (times > 0) {
                    rotate(r, c, times);
                    total += times;
                }
            }
        }

        for (int c = 0; c < n; c++) {
            if (board[n - 1][c] != 0) {
                return;
            }
        }

        answer = Math.min(answer, total);
    }

    void rotate(int r, int c, int times) {
        for (int d = 0; d < 5; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                board[nr][nc] = (board[nr][nc] + times) % 4;
            }
        }
    }
}