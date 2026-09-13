
class Solution {
    public long solution(int[][] cost, int[][] hint) {
        int n = cost.length;
        int m = n - 1;
        long answer = Long.MAX_VALUE;

        for (int mask = 0; mask < (1 << m); mask++) {
            long total = 0;
            int[] count = new int[n];

            for (int i = 0; i < m; i++) {
                if ((mask & (1 << i)) != 0) {
                    total += hint[i][0];

                    for (int j = 1; j < hint[i].length; j++) {
                        int stage = hint[i][j] - 1;
                        count[stage]++;
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                total += cost[i][Math.min(count[i], n - 1)];
            }

            answer = Math.min(answer, total);
        }

        return answer;
    }
}
