import java.util.*;
class Solution {
    public int solution(int k, int n, int[][] reqs) {
        List<int[]>[] requests = new ArrayList[k];

        for (int i = 0; i < k; i++) {
            requests[i] = new ArrayList<>();
        }

        for (int[] req : reqs) {
            requests[req[2] - 1].add(req);
        }

        int[][] waiting = new int[k][n + 1];

        for (int type = 0; type < k; type++) {
            for (int mentors = 1; mentors <= n; mentors++) {
                PriorityQueue<Integer> pq = new PriorityQueue<>();

                for (int i = 0; i < mentors; i++) {
                    pq.offer(0);
                }

                int sum = 0;

                for (int[] req : requests[type]) {
                    int available = pq.poll();

                    if (available > req[0]) {
                        sum += available - req[0];
                        pq.offer(available + req[1]);
                    } else {
                        pq.offer(req[0] + req[1]);
                    }
                }

                waiting[type][mentors] = sum;
            }
        }

        int INF = Integer.MAX_VALUE / 2;
        int[][] dp = new int[k + 1][n + 1];

        for (int i = 0; i <= k; i++) {
            Arrays.fill(dp[i], INF);
        }

        dp[0][0] = 0;

        for (int type = 0; type < k; type++) {
            for (int used = 0; used <= n; used++) {
                if (dp[type][used] == INF) {
                    continue;
                }

                for (int mentors = 1; used + mentors <= n; mentors++) {
                    int next = used + mentors;

                    if (n - next < k - type - 1) {
                        break;
                    }

                    dp[type + 1][next] = Math.min(
                        dp[type + 1][next],
                        dp[type][used] + waiting[type][mentors]
                    );
                }
            }
        }

        return dp[k][n];
    }
}