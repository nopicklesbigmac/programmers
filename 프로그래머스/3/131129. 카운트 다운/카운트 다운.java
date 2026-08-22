class Solution {
    public int[] solution(int target) {
        int[] answer = {};
        int[] dp = new int[target + 1];
        int[] singleBull = new int[target + 1];

        for (int i = 1; i <= target; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        for (int score = 1; score <= target; score++) {
            int minDarts = Integer.MAX_VALUE;
            int maxSingleBull = 0;

            for (int i = 1; i <= 20; i++) {
                for (int mul = 1; mul <= 3; mul++) {
                    int value = i * mul;

                    if (score >= value) {
                        int darts = dp[score - value] + 1;
                        int sb = singleBull[score - value] + (mul == 1 ? 1 : 0);

                        if (darts < minDarts) {
                            minDarts = darts;
                            maxSingleBull = sb;
                        } else if (darts == minDarts) {
                            maxSingleBull = Math.max(maxSingleBull, sb);
                        }
                    }
                }
            }

            if (score >= 50) {
                int darts = dp[score - 50] + 1;
                int sb = singleBull[score - 50] + 1;

                if (darts < minDarts) {
                    minDarts = darts;
                    maxSingleBull = sb;
                } else if (darts == minDarts) {
                    maxSingleBull = Math.max(maxSingleBull, sb);
                }
            }

            dp[score] = minDarts;
            singleBull[score] = maxSingleBull;
        }

        answer = new int[]{dp[target], singleBull[target]};
        return answer;
    }
}