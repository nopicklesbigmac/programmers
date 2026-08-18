class Solution {
    int answer = 0;
    int[] info;
    int[][] tree;

    public int solution(int[] info, int[][] edges) {
        this.info = info;
        tree = new int[info.length][2];

        for (int[] edge : edges) {
            int parent = edge[0];
            int child = edge[1];

            if (tree[parent][0] == 0 && parent != 0) {
                tree[parent][0] = child;
            } else if (tree[parent][0] == 0) {
                tree[parent][0] = child;
            } else {
                tree[parent][1] = child;
            }
        }

        dfs(0, 0, 0, 1);

        return answer;
    }

    void dfs(int node, int sheep, int wolf, int visited) {
        if (info[node] == 0) {
            sheep++;
        } else {
            wolf++;
        }

        if (sheep <= wolf) {
            return;
        }

        answer = Math.max(answer, sheep);

        for (int i = 0; i < info.length; i++) {
            if ((visited & (1 << i)) != 0) {
                continue;
            }

            if (canVisit(i, visited)) {
                dfs(i, sheep, wolf, visited | (1 << i));
            }
        }
    }

    boolean canVisit(int node, int visited) {
        for (int i = 0; i < info.length; i++) {
            if ((visited & (1 << i)) != 0) {
                if (tree[i][0] == node || tree[i][1] == node) {
                    return true;
                }
            }
        }

        return false;
    }
}