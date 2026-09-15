import java.util.*;

class Solution {
    public int solution(int k, int[] num, int[][] links) {
        int n = num.length;

        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        for (int i = 0; i < n; i++) {
            if (links[i][0] != -1) {
                parent[links[i][0]] = i;
            }
            if (links[i][1] != -1) {
                parent[links[i][1]] = i;
            }
        }

        int root = 0;
        while (parent[root] != -1) {
            root++;
        }

        int[] order = new int[n];
        int[] stack = new int[n];
        int top = 0;
        int idx = 0;

        stack[top++] = root;

        while (top > 0) {
            int cur = stack[--top];
            order[idx++] = cur;

            if (links[cur][0] != -1) {
                stack[top++] = links[cur][0];
            }

            if (links[cur][1] != -1) {
                stack[top++] = links[cur][1];
            }
        }

        long low = 0;
        long high = 0;

        for (int x : num) {
            low = Math.max(low, x);
            high += x;
        }

        long[] dp = new long[n];

        while (low < high) {
            long mid = (low + high) >>> 1;

            int groups = 1;

            for (int i = n - 1; i >= 0; i--) {
                int node = order[i];

                int left = links[node][0];
                int right = links[node][1];

                long l = left == -1 ? 0 : dp[left];
                long r = right == -1 ? 0 : dp[right];

                long sum = num[node] + l + r;

                if (sum <= mid) {
                    dp[node] = sum;
                } else if (num[node] + Math.min(l, r) <= mid) {
                    dp[node] = num[node] + Math.min(l, r);
                    groups++;
                } else {
                    dp[node] = num[node];
                    groups += 2;
                }

                if (groups > k) {
                    break;
                }
            }

            if (groups <= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return (int) low;
    }
}