class Solution {
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int MIN = -10;
        int MAX = 40;
        int INF = 1_000_000_000;
        int n = onboard.length;

        int[][] dp = new int[n][51];

        for (int i = 0; i < n; i++) {
            java.util.Arrays.fill(dp[i], INF);
        }

        dp[0][temperature - MIN] = 0;

        for (int time = 0; time < n - 1; time++) {
            for (int temp = MIN; temp <= MAX; temp++) {
                int cur = dp[time][temp - MIN];

                if (cur == INF) {
                    continue;
                }

                if (onboard[time] == 1 && (temp < t1 || temp > t2)) {
                    continue;
                }

                int nextTemp;

                if (temp < temperature) {
                    nextTemp = temp + 1;
                } else if (temp > temperature) {
                    nextTemp = temp - 1;
                } else {
                    nextTemp = temp;
                }

                if (onboard[time + 1] == 0 ||
                        (nextTemp >= t1 && nextTemp <= t2)) {
                    dp[time + 1][nextTemp - MIN] =
                            Math.min(dp[time + 1][nextTemp - MIN], cur);
                }

                for (int target = MIN; target <= MAX; target++) {
                    if (target == temp) {
                        nextTemp = temp;
                    } else if (target > temp) {
                        nextTemp = temp + 1;
                    } else {
                        nextTemp = temp - 1;
                    }

                    if (nextTemp < MIN || nextTemp > MAX) {
                        continue;
                    }

                    if (onboard[time + 1] == 1 &&
                            (nextTemp < t1 || nextTemp > t2)) {
                        continue;
                    }

                    int cost = target == temp ? b : a;

                    dp[time + 1][nextTemp - MIN] =
                            Math.min(
                                    dp[time + 1][nextTemp - MIN],
                                    cur + cost
                            );
                }
            }
        }

        int answer = INF;

        for (int temp = MIN; temp <= MAX; temp++) {
            answer = Math.min(answer, dp[n - 1][temp - MIN]);
        }

        return answer;
    }
}