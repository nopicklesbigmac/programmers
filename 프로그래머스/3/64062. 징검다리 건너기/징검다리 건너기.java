class Solution {
    public int solution(int[] stones, int k) {
        int left = 1;
        int right = 200000000;
        int answer = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (canCross(stones, k, mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    private boolean canCross(int[] stones, int k, int friends) {
        int zeroCount = 0;

        for (int stone : stones) {
            if (stone - friends < 0) {
                zeroCount++;
                if (zeroCount >= k) {
                    return false;
                }
            } else {
                zeroCount = 0;
            }
        }

        return true;
    }
}