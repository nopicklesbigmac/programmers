import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
class Solution {
    public int solution(int n, int[][] road, int k) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] r : road) {
            int u = r[0];
            int v = r[1];
            int w = r[2];
            graph.get(u).add(new int[]{v, w});
            graph.get(v).add(new int[]{u, w});
        }

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.add(new int[]{1, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int u = current[0];
            int w = current[1];

            if (w > dist[u]) {
                continue;
            }

            for (int[] neighbor : graph.get(u)) {
                int next = neighbor[0];
                int nextWeight = w + neighbor[1];

                if (nextWeight < dist[next]) {
                    dist[next] = nextWeight;
                    pq.add(new int[]{next, nextWeight});
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] <= k) {
                answer++;
            }
        }

        return answer;
    }
}