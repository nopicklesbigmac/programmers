import java.util.Arrays;
class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);
        long left = 1;
        long right = (long) times[times.length - 1] * n;
        answer = right;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            long total = 0;

            for (int time : times) {
                total += mid / time;
            }

            if (total >= n) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }
}