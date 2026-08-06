class Solution {
    public int solution(int[][] info, int n, int m) {
        int answer = 0;
        int length = info.length;
        int maxA = 0;
        int maxB = 0;

        for (int i = 0; i < length; i++) {
            maxA += info[i][0];
            maxB += info[i][1];
        }

        boolean[][][] dp = new boolean[length + 1][n][m];
        dp[0][0][0] = true;

        for (int i = 0; i < length; i++) {
            int aTrace = info[i][0];
            int bTrace = info[i][1];

            for (int a = 0; a < n; a++) {
                for (int b = 0; b < m; b++) {
                    if (!dp[i][a][b]) continue;

                    if (a + aTrace < n) {
                        dp[i + 1][a + aTrace][b] = true;
                    }

                    if (b + bTrace < m) {
                        dp[i + 1][a][b + bTrace] = true;
                    }
                }
            }
        }
        answer = -1;
        for (int a = 0; a < n; a++) {
            for (int b = 0; b < m; b++) {
                if (dp[length][a][b]) {
                    if (answer == -1 || a < answer) {
                        answer = a;
                    }
                }
            }
        }

        return answer;
    }
}