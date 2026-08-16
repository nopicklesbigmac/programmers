import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        Arrays.sort(jobs, (a, b) -> {
            if (a[0] == b[0]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[0], b[0]);
        });

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[1] == b[1]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        int time = 0;
        int index = 0;
        int total = 0;

        while (index < jobs.length || !pq.isEmpty()) {
            while (index < jobs.length && jobs[index][0] <= time) {
                pq.offer(jobs[index++]);
            }

            if (pq.isEmpty()) {
                time = jobs[index][0];
                continue;
            }

            int[] job = pq.poll();
            time += job[1];
            total += time - job[0];
        }

        answer = total / jobs.length;
        return answer;
    }
}