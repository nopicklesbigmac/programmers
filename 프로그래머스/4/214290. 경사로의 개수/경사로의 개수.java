class Solution {
    static final long MOD = 1000000007L;

    public int solution(int[][] grid, int[] d, int k) {
        int n = grid.length;
        int m = grid[0].length;
        int size = n * m;

        long[][] base = new long[size][size];

        for (int start = 0; start < size; start++) {
            long[] dp = new long[size];
            dp[start] = 1;

            for (int diff : d) {
                long[] next = new long[size];

                for (int cur = 0; cur < size; cur++) {
                    if (dp[cur] == 0) continue;

                    int r = cur / m;
                    int c = cur % m;

                    if (r > 0 && grid[r - 1][c] - grid[r][c] == diff) {
                        int nextPos = cur - m;
                        next[nextPos] = (next[nextPos] + dp[cur]) % MOD;
                    }

                    if (r + 1 < n && grid[r + 1][c] - grid[r][c] == diff) {
                        int nextPos = cur + m;
                        next[nextPos] = (next[nextPos] + dp[cur]) % MOD;
                    }

                    if (c > 0 && grid[r][c - 1] - grid[r][c] == diff) {
                        int nextPos = cur - 1;
                        next[nextPos] = (next[nextPos] + dp[cur]) % MOD;
                    }

                    if (c + 1 < m && grid[r][c + 1] - grid[r][c] == diff) {
                        int nextPos = cur + 1;
                        next[nextPos] = (next[nextPos] + dp[cur]) % MOD;
                    }
                }

                dp = next;
            }

            for (int end = 0; end < size; end++) {
                base[start][end] = dp[end];
            }
        }

        long[][] result = new long[size][size];

        for (int i = 0; i < size; i++) {
            result[i][i] = 1;
        }

        while (k > 0) {
            if ((k & 1) == 1) {
                result = multiply(result, base);
            }

            base = multiply(base, base);
            k >>= 1;
        }

        long answer = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                answer += result[i][j];
                answer %= MOD;
            }
        }

        return (int) answer;
    }

    private long[][] multiply(long[][] a, long[][] b) {
        int size = a.length;
        long[][] result = new long[size][size];

        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                if (a[i][k] == 0) continue;

                for (int j = 0; j < size; j++) {
                    if (b[k][j] == 0) continue;

                    result[i][j] = (result[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }

        return result;
    }
}