import java.util.PriorityQueue;
class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
         PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        for (int work : works) {
            pq.offer(work);
        }

        while (n-- > 0 && pq.peek() > 0) {
            int work = pq.poll();
            pq.offer(work - 1);
        }
        while (!pq.isEmpty()) {
            long work = pq.poll();
            answer += work * work;
        }
        return answer;
    }
}