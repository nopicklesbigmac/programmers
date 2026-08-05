import java.util.*;
class Solution {
    public int solution(int n, int infection, int[][] edges, int k) {
        List<int[]>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int type = edge[2];
            graph[u].add(new int[]{v, type});
            graph[v].add(new int[]{u, type});
        }

        Set<Integer> infected = new HashSet<>();
        infected.add(infection);

        dfs(infected, graph, k);

        return maxInfected;
    }
private int maxInfected = 0;

    private void dfs(Set<Integer> infected, List<int[]>[] graph, int kLeft) {
        maxInfected = Math.max(maxInfected, infected.size());
        if (kLeft == 0) {
            return;
        }

        for (int type = 1; type <= 3; type++) {
            Set<Integer> nextInfected = new HashSet<>(infected);
            Queue<Integer> queue = new LinkedList<>(infected);
            boolean[] visited = new boolean[graph.length];
            for (int node : infected) {
                visited[node] = true;
            }

            while (!queue.isEmpty()) {
                int curr = queue.poll();
                for (int[] neighbor : graph[curr]) {
                    int nextNode = neighbor[0];
                    int pipeType = neighbor[1];
                    if (pipeType == type && !visited[nextNode]) {
                        visited[nextNode] = true;
                        nextInfected.add(nextNode);
                        queue.add(nextNode);
                    }
                }
            }

            if (nextInfected.size() > infected.size()) {
                dfs(nextInfected, graph, kLeft - 1);
            }
        }
    }
}