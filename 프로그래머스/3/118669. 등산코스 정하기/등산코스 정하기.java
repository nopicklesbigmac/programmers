import java.util.*;
class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {};
        List<int[]>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        boolean[] gate = new boolean[n + 1];
        boolean[] summit = new boolean[n + 1];

        for (int g : gates) {
            gate[g] = true;
        }

        for (int s : summits) {
            summit[s] = true;
        }

        for (int[] path : paths) {
            int a = path[0];
            int b = path[1];
            int w = path[2];

            if (!summit[a] && !gate[b]) {
                graph[a].add(new int[]{b, w});
            }

            if (!summit[b] && !gate[a]) {
                graph[b].add(new int[]{a, w});
            }
        }

        long[] dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);

        PriorityQueue<long[]> pq = new PriorityQueue<>(
            Comparator.comparingLong(x -> x[1])
        );

        for (int g : gates) {
            dist[g] = 0;
            pq.offer(new long[]{g, 0});
        }

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();

            int now = (int) cur[0];
            long intensity = cur[1];

            if (dist[now] != intensity) {
                continue;
            }

            if (summit[now]) {
                continue;
            }

            for (int[] next : graph[now]) {
                int nextNode = next[0];
                long nextIntensity = Math.max(intensity, next[1]);

                if (nextIntensity < dist[nextNode]) {
                    dist[nextNode] = nextIntensity;
                    pq.offer(new long[]{nextNode, nextIntensity});
                }
            }
        }

        Arrays.sort(summits);

        int answerSummit = 0;
        long answerIntensity = Long.MAX_VALUE;

        for (int s : summits) {
            if (dist[s] < answerIntensity) {
                answerIntensity = dist[s];
                answerSummit = s;
            }
        }

        answer = new int[]{answerSummit, (int) answerIntensity};
        return answer;
    }
}