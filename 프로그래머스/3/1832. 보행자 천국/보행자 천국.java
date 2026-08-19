class Solution {
    int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;
        int[][] right = new int[m][n];
        int[][] down = new int[m][n];

        right[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (cityMap[i][j] == 1) {
                    continue;
                }

                if (i == 0 && j == 0) {
                    continue;
                }

                if (j > 0 && cityMap[i][j - 1] != 1) {
                    if (cityMap[i][j - 1] == 2) {
                        right[i][j] = right[i][j - 1];
                    } else {
                        right[i][j] = (right[i][j - 1] + down[i][j - 1]) % MOD;
                    }
                }

                if (i > 0 && cityMap[i - 1][j] != 1) {
                    if (cityMap[i - 1][j] == 2) {
                        down[i][j] = down[i - 1][j];
                    } else {
                        down[i][j] = (right[i - 1][j] + down[i - 1][j]) % MOD;
                    }
                }
            }
        }
        answer = (right[m - 1][n - 1] + down[m - 1][n - 1]) % MOD;
        return answer;
    }
}