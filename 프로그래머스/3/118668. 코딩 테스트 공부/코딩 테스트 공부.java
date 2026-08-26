class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int answer = 0;
        int maxAlp = alp;
        int maxCop = cop;

        for (int[] p : problems) {
            maxAlp = Math.max(maxAlp, p[0]);
            maxCop = Math.max(maxCop, p[1]);
        }

        int[][] dp = new int[maxAlp + 1][maxCop + 1];

        for (int i = 0; i <= maxAlp; i++) {
            for (int j = 0; j <= maxCop; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);
        dp[alp][cop] = 0;

        for (int a = alp; a <= maxAlp; a++) {
            for (int c = cop; c <= maxCop; c++) {
                if (dp[a][c] == Integer.MAX_VALUE) continue;

                if (a < maxAlp) {
                    dp[a + 1][c] = Math.min(dp[a + 1][c], dp[a][c] + 1);
                }

                if (c < maxCop) {
                    dp[a][c + 1] = Math.min(dp[a][c + 1], dp[a][c] + 1);
                }

                for (int[] p : problems) {
                    if (a < p[0] || c < p[1]) continue;

                    int nextAlp = Math.min(maxAlp, a + p[2]);
                    int nextCop = Math.min(maxCop, c + p[3]);

                    dp[nextAlp][nextCop] = Math.min(
                        dp[nextAlp][nextCop],
                        dp[a][c] + p[4]
                    );
                }
            }
        }

        answer = dp[maxAlp][maxCop];
        return answer;
    }
}