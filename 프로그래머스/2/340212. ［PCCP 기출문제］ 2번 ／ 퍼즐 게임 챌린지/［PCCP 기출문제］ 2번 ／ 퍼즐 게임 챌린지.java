class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        int left = 1;
        int right = 100000;
        answer = right;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canSolve(diffs, times, limit, mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }

    private boolean canSolve(int[] diffs, int[] times, long limit, int level) {
        long totalTime = 0;
        int n = diffs.length;

        for (int i = 0; i < n; i++) {
            int diff = diffs[i];
            int timeCur = times[i];

            if (diff <= level) {
                totalTime += timeCur;
            } else {
                int timePrev = (i > 0) ? times[i - 1] : 0;
                long fails = diff - level;
                totalTime += fails * (timeCur + timePrev) + timeCur;
            }

            if (totalTime > limit) {
                return false;
            }
        }

        return totalTime <= limit;
    }
}