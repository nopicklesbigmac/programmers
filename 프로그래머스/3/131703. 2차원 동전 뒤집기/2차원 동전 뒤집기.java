class Solution {
    public int solution(int[][] beginning, int[][] target) {
        int answer = 0;
        answer = Integer.MAX_VALUE;
        int n = beginning.length;
        int m = beginning[0].length;

        for (int mask = 0; mask < (1 << n); mask++) {
            int count = 0;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    count++;
                }
            }

            boolean possible = true;

            for (int j = 0; j < m; j++) {
                int flip = 0;

                for (int i = 0; i < n; i++) {
                    int value = beginning[i][j];

                    if ((mask & (1 << i)) != 0) {
                        value ^= 1;
                    }

                    if (value != target[i][j]) {
                        flip++;
                    }
                }

                if (flip != 0 && flip != n) {
                    possible = false;
                    break;
                }

                if (flip == n) {
                    count++;
                }
            }

            if (possible) {
                answer = Math.min(answer, count);
            }
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}