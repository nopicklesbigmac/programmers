import java.util.*;

class Solution {
    ArrayList<Integer>[] graph;
    ArrayList<Integer>[] reverse;
    boolean[] visited;
    int[] order;
    int orderIdx;
    int[] component;
    int componentCount;
    int[] match;
    boolean[] used;

    public int solution(int n, int[][] roads) {
        graph = new ArrayList[n];
        reverse = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            reverse[i] = new ArrayList<>();
        }

        for (int[] road : roads) {
            int a = road[0] - 1;
            int b = road[1] - 1;
            graph[a].add(b);
            reverse[b].add(a);
        }

        visited = new boolean[n];
        order = new int[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs1(i);
            }
        }

        component = new int[n];
        Arrays.fill(component, -1);

        for (int i = n - 1; i >= 0; i--) {
            int v = order[i];

            if (component[v] == -1) {
                dfs2(v, componentCount++);
            }
        }

        boolean[][] reach = new boolean[componentCount][componentCount];

        for (int[] road : roads) {
            int a = component[road[0] - 1];
            int b = component[road[1] - 1];

            if (a != b) {
                reach[a][b] = true;
            }
        }

        for (int k = 0; k < componentCount; k++) {
            for (int i = 0; i < componentCount; i++) {
                if (!reach[i][k]) {
                    continue;
                }

                for (int j = 0; j < componentCount; j++) {
                    if (reach[k][j]) {
                        reach[i][j] = true;
                    }
                }
            }
        }

        match = new int[componentCount];
        Arrays.fill(match, -1);

        int matching = 0;

        for (int i = 0; i < componentCount; i++) {
            used = new boolean[componentCount];

            if (dfsMatch(i, reach)) {
                matching++;
            }
        }

        return componentCount - matching - 1;
    }

    void dfs1(int v) {
        visited[v] = true;

        for (int next : graph[v]) {
            if (!visited[next]) {
                dfs1(next);
            }
        }

        order[orderIdx++] = v;
    }

    void dfs2(int v, int id) {
        component[v] = id;

        for (int next : reverse[v]) {
            if (component[next] == -1) {
                dfs2(next, id);
            }
        }
    }

    boolean dfsMatch(int v, boolean[][] reach) {
        for (int next = 0; next < componentCount; next++) {
            if (!reach[v][next] || used[next]) {
                continue;
            }

            used[next] = true;

            if (match[next] == -1 || dfsMatch(match[next], reach)) {
                match[next] = v;
                return true;
            }
        }

        return false;
    }
}