import java.util.*;

class Solution {
    static int n;
    static int[][] board;
    static int[][] table;
    static boolean[][] visited;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public int solution(int[][] game_board, int[][] table) {
        n = game_board.length;
        board = game_board;
        Solution.table = table;

        List<List<int[]>> blanks = new ArrayList<>();
        List<List<int[]>> pieces = new ArrayList<>();

        visited = new boolean[n][n];

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c] == 0 && !visited[r][c]) {
                    blanks.add(bfs(board, r, c, 0));
                }
            }
        }

        visited = new boolean[n][n];

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (table[r][c] == 1 && !visited[r][c]) {
                    pieces.add(bfs(table, r, c, 1));
                }
            }
        }

        boolean[] used = new boolean[pieces.size()];
        int answer = 0;

        for (List<int[]> blank : blanks) {
            for (int i = 0; i < pieces.size(); i++) {
                if (used[i]) continue;

                if (match(blank, pieces.get(i))) {
                    used[i] = true;
                    answer += blank.size();
                    break;
                }
            }
        }

        return answer;
    }

    static List<int[]> bfs(int[][] map, int sr, int sc, int target) {
        List<int[]> result = new ArrayList<>();
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{sr, sc});
        visited[sr][sc] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];

            result.add(new int[]{r, c});

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                if (visited[nr][nc]) continue;
                if (map[nr][nc] != target) continue;

                visited[nr][nc] = true;
                queue.offer(new int[]{nr, nc});
            }
        }

        normalize(result);
        return result;
    }

    static void normalize(List<int[]> shape) {
        int minR = Integer.MAX_VALUE;
        int minC = Integer.MAX_VALUE;

        for (int[] p : shape) {
            minR = Math.min(minR, p[0]);
            minC = Math.min(minC, p[1]);
        }

        for (int[] p : shape) {
            p[0] -= minR;
            p[1] -= minC;
        }

        shape.sort((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });
    }

    static boolean match(List<int[]> blank, List<int[]> piece) {
        if (blank.size() != piece.size()) return false;

        List<int[]> current = new ArrayList<>();

        for (int[] p : piece) {
            current.add(new int[]{p[0], p[1]});
        }

        for (int rotation = 0; rotation < 4; rotation++) {
            normalize(current);

            if (same(blank, current)) {
                return true;
            }

            current = rotate(current);
        }

        return false;
    }

    static List<int[]> rotate(List<int[]> shape) {
        List<int[]> result = new ArrayList<>();

        int maxR = 0;

        for (int[] p : shape) {
            maxR = Math.max(maxR, p[0]);
        }

        for (int[] p : shape) {
            result.add(new int[]{p[1], maxR - p[0]});
        }

        normalize(result);
        return result;
    }

    static boolean same(List<int[]> a, List<int[]> b) {
        if (a.size() != b.size()) return false;

        for (int i = 0; i < a.size(); i++) {
            if (a.get(i)[0] != b.get(i)[0]) return false;
            if (a.get(i)[1] != b.get(i)[1]) return false;
        }

        return true;
    }
}