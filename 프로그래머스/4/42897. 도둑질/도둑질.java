class Solution {
    public int solution(int[] money) {
        int answer = 0;
        int n = money.length;

        int prev2 = money[0];
        int prev1 = money[0];

        for (int i = 2; i < n - 1; i++) {
            int current = Math.max(prev1, prev2 + money[i]);
            prev2 = prev1;
            prev1 = current;
        }

        int case1 = prev1;

        prev2 = 0;
        prev1 = money[1];

        for (int i = 2; i < n; i++) {
            int current = Math.max(prev1, prev2 + money[i]);
            prev2 = prev1;
            prev1 = current;
        }

        int case2 = prev1;

        answer = Math.max(case1, case2);
        return answer;
    }
}