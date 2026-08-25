import java.util.*;
class Solution {
    public long solution(int[] a, int[][] edges) {
        long answer = -2;
        int n = a.length;

        long sum = 0;
        for (int value : a) {
            sum += value;
        }

        if (sum != 0) {
            return -1;
        }

        List<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph[u].add(v);
            graph[v].add(u);
        }

        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        int[] order = new int[n];
        int index = 0;

        int[] stack = new int[n];
        int top = 0;

        stack[top++] = 0;
        parent[0] = 0;

        while (top > 0) {
            int cur = stack[--top];
            order[index++] = cur;

            for (int next : graph[cur]) {
                if (parent[next] != -1) {
                    continue;
                }

                parent[next] = cur;
                stack[top++] = next;
            }
        }

        answer = 0;
        long[] weight = new long[n];

        for (int i = 0; i < n; i++) {
            weight[i] = a[i];
        }

        for (int i = n - 1; i > 0; i--) {
            int cur = order[i];
            int p = parent[cur];

            answer += Math.abs(weight[cur]);
            weight[p] += weight[cur];
        }

        return answer;
    }
}