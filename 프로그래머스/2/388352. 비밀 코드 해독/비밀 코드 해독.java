class Solution {
    public int solution(int n, int[][] q, int[] ans) {
        int answer = 0;
        answer = dfs(1, 0, new int[5], n, q, ans);
        return answer;
    }
    private int dfs(int start, int depth, int[] current, int n, int[][] q, int[] ans) {
        if (depth == 5) {
            return isValid(current, q, ans) ? 1 : 0;
        }

        int count = 0;
        for (int i = start; i <= n; i++) {
            current[depth] = i;
            count += dfs(i + 1, depth + 1, current, n, q, ans);
        }
        return count;
    }

    private boolean isValid(int[] current, int[][] q, int[] ans) {
        for (int i = 0; i < q.length; i++) {
            int matchCount = 0;
            int p1 = 0, p2 = 0;
            
            while (p1 < 5 && p2 < 5) {
                if (current[p1] == q[i][p2]) {
                    matchCount++;
                    p1++;
                    p2++;
                } else if (current[p1] < q[i][p2]) {
                    p1++;
                } else {
                    p2++;
                }
            }

            if (matchCount != ans[i]) {
                return false;
            }
        }
        return true;
    }
}