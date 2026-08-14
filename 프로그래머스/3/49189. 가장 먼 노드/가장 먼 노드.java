import java.util.*;
class Solution {
    public int solution(int n, int[][] edge) {
        List<Integer>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edge) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        dist[1] = 0;

        int max = 0;
        int answer = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next : graph[current]) {
                if (dist[next] != -1) {
                    continue;
                }

                dist[next] = dist[current] + 1;
                queue.offer(next);

                if (dist[next] > max) {
                    max = dist[next];
                    answer = 1;
                } else if (dist[next] == max) {
                    answer++;
                }
            }
        }

        return answer;
    }
}