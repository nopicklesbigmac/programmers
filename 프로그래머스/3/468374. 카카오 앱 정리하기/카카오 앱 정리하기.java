import java.util.*;

class Solution {
    static int N, M;
    static int[][] board;

    static final int[] DR = {0, 0, 1, 0, -1};
    static final int[] DC = {0, 1, 0, -1, 0};

    public int[][] solution(int[][] board, int[][] commands) {
        N = board.length;
        M = board[0].length;

        Solution.board = new int[N][M];

        for (int r = 0; r < N; r++) {
            this.board[r] = board[r].clone();
        }

        for (int[] command : commands) {
            move(command[0], command[1]);
        }

        return this.board;
    }

    static void move(int id, int dir) {
        boolean[] selected = new boolean[101];
        Queue<Integer> queue = new ArrayDeque<>();

        selected[id] = true;
        queue.offer(id);

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (board[r][c] != cur) {
                        continue;
                    }

                    int nr = (r + DR[dir] + N) % N;
                    int nc = (c + DC[dir] + M) % M;

                    int next = board[nr][nc];

                    if (next != 0 && !selected[next]) {
                        selected[next] = true;
                        queue.offer(next);
                    }
                }
            }
        }

        int[][] nextBoard = new int[N][M];

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (board[r][c] == 0) {
                    continue;
                }

                int cur = board[r][c];

                if (!selected[cur]) {
                    nextBoard[r][c] = cur;
                    continue;
                }

                int nr = (r + DR[dir] + N) % N;
                int nc = (c + DC[dir] + M) % M;

                nextBoard[nr][nc] = cur;
            }
        }

        board = nextBoard;

        while (true) {
            int nextId = findWrappedApp(dir);

            if (nextId == 0) {
                break;
            }

            selected = new boolean[101];
            queue.clear();

            selected[nextId] = true;
            queue.offer(nextId);

            while (!queue.isEmpty()) {
                int cur = queue.poll();

                for (int r = 0; r < N; r++) {
                    for (int c = 0; c < M; c++) {
                        if (board[r][c] != cur) {
                            continue;
                        }

                        int nr = (r + DR[dir] + N) % N;
                        int nc = (c + DC[dir] + M) % M;

                        int next = board[nr][nc];

                        if (next != 0 && !selected[next]) {
                            selected[next] = true;
                            queue.offer(next);
                        }
                    }
                }
            }

            nextBoard = new int[N][M];

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (board[r][c] == 0) {
                        continue;
                    }

                    int cur = board[r][c];

                    if (!selected[cur]) {
                        nextBoard[r][c] = cur;
                        continue;
                    }

                    int nr = (r + DR[dir] + N) % N;
                    int nc = (c + DC[dir] + M) % M;

                    nextBoard[nr][nc] = cur;
                }
            }

            board = nextBoard;
        }
    }

    static int findWrappedApp(int dir) {
        boolean[] found = new boolean[101];

        if (dir == 1 || dir == 3) {
            for (int r = 0; r < N; r++) {
                int left = board[r][0];
                int right = board[r][M - 1];

                if (left == 0 || left != right) {
                    continue;
                }

                boolean existsOther = false;

                for (int c = 1; c < M - 1; c++) {
                    if (board[r][c] != left) {
                        existsOther = true;
                        break;
                    }
                }

                if (existsOther && !found[left]) {
                    return left;
                }
            }
        } else {
            for (int c = 0; c < M; c++) {
                int top = board[0][c];
                int bottom = board[N - 1][c];

                if (top == 0 || top != bottom) {
                    continue;
                }

                boolean existsOther = false;

                for (int r = 1; r < N - 1; r++) {
                    if (board[r][c] != top) {
                        existsOther = true;
                        break;
                    }
                }

                if (existsOther && !found[top]) {
                    return top;
                }
            }
        }

        return 0;
    }
}