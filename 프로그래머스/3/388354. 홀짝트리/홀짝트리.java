class Solution {
    public int[] solution(int[] nodes, int[][] edges) {
        int max = 1000001;

        int[] parent = new int[max];
        int[] degree = new int[max];

        for (int node : nodes) {
            parent[node] = node;
        }

        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];

            degree[a]++;
            degree[b]++;

            union(parent, a, b);
        }

        int[] normal = new int[max];
        int[] reverse = new int[max];

        for (int node : nodes) {
            int root = find(parent, node);

            if ((node & 1) == (degree[node] & 1)) {
                normal[root]++;
            } else {
                reverse[root]++;
            }
        }

        int normalCount = 0;
        int reverseCount = 0;

        for (int node : nodes) {
            if (parent[node] != node) continue;

            if (normal[node] == 1) {
                normalCount++;
            }

            if (reverse[node] == 1) {
                reverseCount++;
            }
        }

        return new int[]{normalCount, reverseCount};
    }

    private int find(int[] parent, int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    private void union(int[] parent, int a, int b) {
        a = find(parent, a);
        b = find(parent, b);

        if (a != b) {
            parent[b] = a;
        }
    }
}