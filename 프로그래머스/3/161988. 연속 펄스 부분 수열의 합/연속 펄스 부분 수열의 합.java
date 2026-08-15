class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        int n = sequence.length;
        long[] s1 = new long[n];
        long[] s2 = new long[n];

        for (int i = 0; i < n; i++) {
            int sign = (i % 2 == 0) ? 1 : -1;
            s1[i] = (long) sequence[i] * sign;
            s2[i] = (long) sequence[i] * (-sign);
        }

        long max1 = s1[0];
        long min1 = s1[0];
        long currentMax1 = s1[0];
        long currentMin1 = s1[0];

        long max2 = s2[0];
        long currentMax2 = s2[0];

        for (int i = 1; i < n; i++) {
            currentMax1 = Math.max(s1[i], currentMax1 + s1[i]);
            max1 = Math.max(max1, currentMax1);

            currentMax2 = Math.max(s2[i], currentMax2 + s2[i]);
            max2 = Math.max(max2, currentMax2);
        }

        answer = Math.max(max1, max2);
        return answer;
    }
}