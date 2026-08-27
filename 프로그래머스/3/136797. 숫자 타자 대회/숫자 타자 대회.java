class Solution {
    public int solution(String numbers) {
        int answer = 0;
        int[][] pos = {
            {3, 1},
            {0, 0},
            {0, 1},
            {0, 2},
            {1, 0},
            {1, 1},
            {1, 2},
            {2, 0},
            {2, 1},
            {2, 2}
        };

        int[][] cost = new int[10][10];

        for (int a = 0; a < 10; a++) {
            for (int b = 0; b < 10; b++) {
                if (a == b) {
                    cost[a][b] = 1;
                } else {
                    int dx = Math.abs(pos[a][0] - pos[b][0]);
                    int dy = Math.abs(pos[a][1] - pos[b][1]);

                    int diagonal = Math.min(dx, dy);
                    int straight = Math.max(dx, dy) - diagonal;

                    cost[a][b] = diagonal * 3 + straight * 2;
                }
            }
        }

        int INF = 1_000_000_000;
        int[][] dp = new int[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                dp[i][j] = INF;
            }
        }

        dp[4][6] = 0;

        for (int i = 0; i < numbers.length(); i++) {
            int target = numbers.charAt(i) - '0';

            int[][] next = new int[10][10];

            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    next[j][k] = INF;
                }
            }

            for (int left = 0; left < 10; left++) {
                for (int right = 0; right < 10; right++) {
                    if (dp[left][right] == INF) {
                        continue;
                    }

                    if (target == left) {
                        next[left][right] = Math.min(
                            next[left][right],
                            dp[left][right] + 1
                        );
                    } else if (target == right) {
                        next[left][right] = Math.min(
                            next[left][right],
                            dp[left][right] + 1
                        );
                    } else {
                        next[target][right] = Math.min(
                            next[target][right],
                            dp[left][right] + cost[left][target]
                        );

                        next[left][target] = Math.min(
                            next[left][target],
                            dp[left][right] + cost[right][target]
                        );
                    }
                }
            }

            dp = next;
        }

        answer = INF;

        for (int left = 0; left < 10; left++) {
            for (int right = 0; right < 10; right++) {
                answer = Math.min(answer, dp[left][right]);
            }
        }

        return answer;
    }
}