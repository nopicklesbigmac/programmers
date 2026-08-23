class Solution {
    public int solution(int n, int[] cores) {
        int answer = 0;
        int len = cores.length;

        if (n <= len) {
            answer = n;
            return answer;
        }

        long left = 0;
        long right = 100_000_000L;

        while (left <= right) {
            long mid = (left + right) / 2;
            long count = len;

            for (int core : cores) {
                count += mid / core;
            }

            if (count >= n) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        long time = left - 1;
        long count = len;

        for (int core : cores) {
            count += time / core;
        }

        for (int i = 0; i < len; i++) {
            if (left % cores[i] == 0) {
                count++;

                if (count == n) {
                    answer = i + 1;
                    break;
                }
            }
        }

        return answer;
    }
}