import java.util.LinkedList;
import java.util.Queue;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        long sum1 = 0;
        long sum2 = 0;
        long totalSum = 0;

        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        for (int v : queue1) {
            sum1 += v;
            q1.add(v);
        }

        for (int v : queue2) {
            sum2 += v;
            q2.add(v);
        }

        totalSum = sum1 + sum2;

        if (totalSum % 2 != 0) {
            return -1;
        }

        long target = totalSum / 2;
        int limit = (queue1.length + queue2.length) * 2;

        while (sum1 != target) {
            if (answer > limit) {
                return -1;
            }

            if (sum1 > target) {
                int val = q1.poll();
                sum1 -= val;
                sum2 += val;
                q2.add(val);
            } else {
                int val = q2.poll();
                sum2 -= val;
                sum1 += val;
                q1.add(val);
            }
            answer++;
        }

        return answer;
    }
}