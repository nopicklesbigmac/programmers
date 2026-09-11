class Solution {
    public int solution(int n) {
        return dfs(n, 0);
    }

    private int dfs(long n, int plus) {
        long min = 1;

        for (int i = 0; i < plus / 2; i++) {
            min *= 3;
            if (min > n) {
                return 0;
            }
        }

        if (n == 3) {
            return plus == 2 ? 1 : 0;
        }

        if (n < 3) {
            return 0;
        }

        int answer = dfs(n - 1, plus + 1);

        if (plus >= 2 && n % 3 == 0) {
            answer += dfs(n / 3, plus - 2);
        }

        return answer;
    }
}