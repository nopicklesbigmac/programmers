class Solution {
    int n, m;
    int[][] board;
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        this.board = board;
        n = board.length;
        m = board[0].length;

        return dfs(aloc[0], aloc[1], bloc[0], bloc[1]).moves;
    }

    Result dfs(int ar, int ac, int br, int bc) {
        if (board[ar][ac] == 0) {
            return new Result(false, 0);
        }

        boolean canWin = false;
        int minWin = Integer.MAX_VALUE;
        int maxLose = 0;

        for (int d = 0; d < 4; d++) {
            int nr = ar + dr[d];
            int nc = ac + dc[d];

            if (nr < 0 || nr >= n || nc < 0 || nc >= m || board[nr][nc] == 0) {
                continue;
            }

            board[ar][ac] = 0;

            Result result = dfs(br, bc, nr, nc);

            board[ar][ac] = 1;

            if (!result.win) {
                canWin = true;
                minWin = Math.min(minWin, result.moves + 1);
            } else if (!canWin) {
                maxLose = Math.max(maxLose, result.moves + 1);
            }
        }

        if (canWin) {
            return new Result(true, minWin);
        }

        return new Result(false, maxLose);
    }

    static class Result {
        boolean win;
        int moves;

        Result(boolean win, int moves) {
            this.win = win;
            this.moves = moves;
        }
    }
}