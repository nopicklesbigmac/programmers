class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long answer = -1;
        long left = 0;
        long right = 4_000_000_000_000_000L;

        while (left <= right) {
            long mid = (left + right) / 2;

            long gold = 0;
            long silver = 0;
            long total = 0;

            for (int i = 0; i < g.length; i++) {
                long round = mid / (2L * t[i]);
                long trips = round + (mid % (2L * t[i]) >= t[i] ? 1 : 0);
                long amount = Math.min((long) w[i] * trips, (long) g[i] + s[i]);

                gold += Math.min((long) g[i], amount);
                silver += Math.min((long) s[i], amount);
                total += amount;

                if (gold >= a && silver >= b && total >= (long) a + b) {
                    break;
                }
            }

            if (gold >= a && silver >= b && total >= (long) a + b) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }
}