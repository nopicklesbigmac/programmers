import java.util.*;

class Solution {
    public int solution(int[][] land, int height) {
        int n = land.length;
        int size = n * n;
        int[] parent = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }

        List<int[]> edges = new ArrayList<>();

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                int current = r * n + c;

                if (r + 1 < n) {
                    int next = (r + 1) * n + c;
                    int cost = Math.abs(land[r][c] - land[r + 1][c]);
                    edges.add(new int[]{current, next, cost});
                }

                if (c + 1 < n) {
                    int next = r * n + c + 1;
                    int cost = Math.abs(land[r][c] - land[r][c + 1]);
                    edges.add(new int[]{current, next, cost});
                }
            }
        }

        edges.sort((a, b) -> Integer.compare(a[2], b[2]));

        int answer = 0;

        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            int cost = edge[2];

            if (cost <= height) {
                union(parent, a, b);
            } else if (union(parent, a, b)) {
                answer += cost;
            }
        }

        return answer;
    }

    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }

    private boolean union(int[] parent, int a, int b) {
        int rootA = find(parent, a);
        int rootB = find(parent, b);

        if (rootA == rootB) {
            return false;
        }

        parent[rootB] = rootA;
        return true;
    }
}