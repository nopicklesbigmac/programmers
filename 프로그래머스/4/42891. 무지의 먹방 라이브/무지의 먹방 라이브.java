import java.util.*;

class Solution {
    public int solution(int[] food_times, long k) {
        int n = food_times.length;

        long total = 0;
        for (int time : food_times) {
            total += time;
        }

        if (total <= k) {
            return -1;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );

        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{food_times[i], i + 1});
        }

        long prev = 0;
        int remaining = n;

        while (!pq.isEmpty()) {
            int current = pq.peek()[0];
            long cost = (long)(current - prev) * remaining;

            if (k < cost) {
                break;
            }

            k -= cost;
            prev = current;

            while (!pq.isEmpty() && pq.peek()[0] == current) {
                pq.poll();
                remaining--;
            }
        }

        List<int[]> foods = new ArrayList<>(pq);

        foods.sort((a, b) -> Integer.compare(a[1], b[1]));

        return foods.get((int)(k % remaining))[1];
    }
}