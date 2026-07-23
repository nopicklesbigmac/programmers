import java.util.*;
class Solution {
    public int solution(int n, int[][] wires) {
        int minDiff = Integer.MAX_VALUE;

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] wire : wires) {
            graph.get(wire[0]).add(wire[1]);
            graph.get(wire[1]).add(wire[0]);
        }

        for (int i = 0; i < wires.length; i++) {
            int v1 = wires[i][0];
            int v2 = wires[i][1];

            boolean[] visited = new boolean[n + 1];
            visited[v2] = true;
            int count = dfs(v1, graph, visited);

            int diff = Math.abs(count - (n - count));
            minDiff = Math.min(minDiff, diff);
        }

        return minDiff;
    }

    private int dfs(int node, List<List<Integer>> graph, boolean[] visited) {
        visited[node] = true;
        int count = 1;

        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                count += dfs(neighbor, graph, visited);
            }
        }

        return count;
    }
}