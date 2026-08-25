import java.util.*;

class Solution {
    public int solution(int n, int[][] lighthouse) {
        int answer = 0;
        List<Integer>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : lighthouse) {
            int a = edge[0];
            int b = edge[1];

            graph[a].add(b);
            graph[b].add(a);
        }

        int[] parent = new int[n + 1];
        int[] order = new int[n];
        int[] stack = new int[n];

        int top = 0;
        int index = 0;

        stack[top++] = 1;
        parent[1] = -1;

        while (top > 0) {
            int cur = stack[--top];
            order[index++] = cur;

            for (int next : graph[cur]) {
                if (next == parent[cur]) {
                    continue;
                }

                parent[next] = cur;
                stack[top++] = next;
            }
        }

        int[][] dp = new int[n + 1][2];

        for (int i = n - 1; i >= 0; i--) {
            int cur = order[i];

            dp[cur][0] = 0;
            dp[cur][1] = 1;

            for (int next : graph[cur]) {
                if (next == parent[cur]) {
                    continue;
                }

                dp[cur][0] += dp[next][1];
                dp[cur][1] += Math.min(dp[next][0], dp[next][1]);
            }
        }

        answer = Math.min(dp[1][0], dp[1][1]);
        return answer;
    }
}