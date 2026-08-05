class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int deliverRemain = 0;
        int pickupRemain = 0;

        for (int i = n - 1; i >= 0; i--) {
            if (deliveries[i] > 0 || pickups[i] > 0) {
                int cnt = 0;
                while (deliverRemain < deliveries[i] || pickupRemain < pickups[i]) {
                    deliverRemain += cap;
                    pickupRemain += cap;
                    cnt++;
                }
                deliverRemain -= deliveries[i];
                pickupRemain -= pickups[i];
                answer += (long) (i + 1) * 2 * cnt;
            }
        }

        return answer;
    }
}