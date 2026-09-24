import java.util.*;

class Solution {
    ArrayList<Integer>[] tree1;
    ArrayList<Integer>[] tree2;
    int[][] dp;

    public int solution(int n1, int[][] g1, int n2, int[][] g2) {
        tree1 = new ArrayList[n1 + 1];
        tree2 = new ArrayList[n2 + 1];

        for (int i = 1; i <= n1; i++) tree1[i] = new ArrayList<>();
        for (int i = 1; i <= n2; i++) tree2[i] = new ArrayList<>();

        buildTree(g1, tree1);
        buildTree(g2, tree2);

        dp = new int[n1 + 1][n2 + 1];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return solve(1, 1);
    }

    void buildTree(int[][] edges, ArrayList<Integer>[] tree) {
        ArrayList<Integer>[] graph = new ArrayList[tree.length];

        for (int i = 1; i < tree.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        int[] parent = new int[tree.length];
        Arrays.fill(parent, -1);

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        parent[1] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : graph[cur]) {
                if (next == parent[cur]) continue;

                parent[next] = cur;
                tree[cur].add(next);
                queue.offer(next);
            }
        }
    }

    int solve(int u, int v) {
        if (dp[u][v] != -1) {
            return dp[u][v];
        }

        ArrayList<Integer> a = tree1[u];
        ArrayList<Integer> b = tree2[v];

        int n = a.size();
        int m = b.size();

        if (n == 0 || m == 0) {
            return dp[u][v] = 1;
        }

        int size = Math.max(n, m);
        int[][] weight = new int[size][size];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                weight[i][j] = solve(a.get(i), b.get(j));
            }
        }

        int matching = hungarian(weight, size);

        return dp[u][v] = 1 + matching;
    }

    int hungarian(int[][] weight, int n) {
        int maxWeight = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maxWeight = Math.max(maxWeight, weight[i][j]);
            }
        }

        int[] u = new int[n + 1];
        int[] v = new int[n + 1];
        int[] p = new int[n + 1];
        int[] way = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            p[0] = i;

            int j0 = 0;
            int[] minv = new int[n + 1];
            boolean[] used = new boolean[n + 1];

            Arrays.fill(minv, Integer.MAX_VALUE);

            do {
                used[j0] = true;

                int i0 = p[j0];
                int delta = Integer.MAX_VALUE;
                int j1 = 0;

                for (int j = 1; j <= n; j++) {
                    if (used[j]) continue;

                    int cur = maxWeight - weight[i0 - 1][j - 1] - u[i0] - v[j];

                    if (cur < minv[j]) {
                        minv[j] = cur;
                        way[j] = j0;
                    }

                    if (minv[j] < delta) {
                        delta = minv[j];
                        j1 = j;
                    }
                }

                for (int j = 0; j <= n; j++) {
                    if (used[j]) {
                        u[p[j]] += delta;
                        v[j] -= delta;
                    } else {
                        minv[j] -= delta;
                    }
                }

                j0 = j1;
            } while (p[j0] != 0);

            do {
                int j1 = way[j0];
                p[j0] = p[j1];
                j0 = j1;
            } while (j0 != 0);
        }

        int result = 0;

        for (int j = 1; j <= n; j++) {
            result += weight[p[j] - 1][j - 1];
        }

        return result;
    }
}