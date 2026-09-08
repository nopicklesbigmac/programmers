import java.util.*;

class Solution {
    public boolean solution(int n, int[][] path, int[][] order) {
        List<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] p : path) {
            graph[p[0]].add(p[1]);
            graph[p[1]].add(p[0]);
        }

        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        int[] before = new int[n];
        Arrays.fill(before, -1);

        int[] after = new int[n];
        Arrays.fill(after, -1);

        int[] queue = new int[n];
        int head = 0;
        int tail = 0;

        queue[tail++] = 0;
        parent[0] = 0;

        while (head < tail) {
            int cur = queue[head++];

            for (int next : graph[cur]) {
                if (parent[next] != -1) {
                    continue;
                }

                parent[next] = cur;
                queue[tail++] = next;
            }
        }

        for (int[] o : order) {
            int a = o[0];
            int b = o[1];

            if (b == 0) {
                return false;
            }

            before[b] = a;
            after[a] = b;
        }

        boolean[] visited = new boolean[n];
        boolean[] waiting = new boolean[n];

        queue = new int[n];
        head = 0;
        tail = 0;

        queue[tail++] = 0;
        visited[0] = true;

        int count = 1;

        while (head < tail) {
            int cur = queue[head++];

            for (int next : graph[cur]) {
                if (visited[next]) {
                    continue;
                }

                if (before[next] != -1 && !visited[before[next]]) {
                    waiting[next] = true;
                    continue;
                }

                visited[next] = true;
                queue[tail++] = next;
                count++;

                if (after[next] != -1 && waiting[after[next]]) {
                    visited[after[next]] = true;
                    queue[tail++] = after[next];
                    count++;
                    waiting[after[next]] = false;
                }
            }
        }

        return count == n;
    }
}