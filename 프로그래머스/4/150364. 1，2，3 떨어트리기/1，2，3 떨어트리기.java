import java.util.*;

class Solution {
    public int[] solution(int[][] edges, int[] target) {
        int n = target.length;

        List<Integer>[] tree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            tree[edge[0] - 1].add(edge[1] - 1);
        }

        for (int i = 0; i < n; i++) {
            Collections.sort(tree[i]);
        }

        int[] pointer = new int[n];
        int[] count = new int[n];

        int total = 0;

        while (true) {
            int node = 0;

            while (!tree[node].isEmpty()) {
                int next = tree[node].get(pointer[node]);

                pointer[node]++;
                if (pointer[node] == tree[node].size()) {
                    pointer[node] = 0;
                }

                node = next;
            }

            count[node]++;
            total++;

            if (target[node] == 0 || count[node] > target[node]) {
                return new int[]{-1};
            }

            boolean done = true;

            for (int i = 0; i < n; i++) {
                if (tree[i].isEmpty()) {
                    int minCount = (target[i] + 2) / 3;

                    if (count[i] < minCount) {
                        done = false;
                        break;
                    }
                }
            }

            if (done) {
                break;
            }
        }

        int[] answer = new int[total];

        Arrays.fill(pointer, 0);

        int[] usedCount = new int[n];
        int[] usedSum = new int[n];

        for (int i = 0; i < total; i++) {
            int node = 0;

            while (!tree[node].isEmpty()) {
                int next = tree[node].get(pointer[node]);

                pointer[node]++;
                if (pointer[node] == tree[node].size()) {
                    pointer[node] = 0;
                }

                node = next;
            }

            usedCount[node]++;

            int remainCount = count[node] - usedCount[node];
            int remainTarget = target[node] - usedSum[node];

            for (int value = 1; value <= 3; value++) {
                int nextTarget = remainTarget - value;

                if (nextTarget >= remainCount &&
                    nextTarget <= remainCount * 3) {
                    answer[i] = value;
                    usedSum[node] += value;
                    break;
                }
            }
        }

        return answer;
    }
}