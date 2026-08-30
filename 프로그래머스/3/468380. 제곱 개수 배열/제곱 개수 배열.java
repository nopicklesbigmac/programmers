class Solution {
    public long[] solution(int[] arr, long l, long r) {
        int n = arr.length;

        long[] pos = new long[n];
        long[] sum = new long[n];

        long p = 0;
        long s = 0;

        for (int i = 0; i < n; i++) {
            p += arr[i];
            s += (long) arr[i] * arr[i];
            pos[i] = p;
            sum[i] = s;
        }

        long total = p;
        long totalSum = s;

        long K = prefix(r, arr, pos, sum, total, totalSum)
                - prefix(l - 1, arr, pos, sum, total, totalSum);

        long len = r - l + 1;
        long maxStart = total - len;

        long[] points = new long[2 * n + 2];
        int cnt = 0;

        points[cnt++] = 0;
        points[cnt++] = maxStart + 1;

        for (int i = 0; i < n; i++) {
            if (pos[i] >= 0 && pos[i] <= maxStart) {
                points[cnt++] = pos[i];
            }

            long x = pos[i] - len;

            if (x >= 0 && x <= maxStart) {
                points[cnt++] = x;
            }
        }

        java.util.Arrays.sort(points, 0, cnt);

        int unique = 0;

        for (int i = 0; i < cnt; i++) {
            if (i == 0 || points[i] != points[i - 1]) {
                points[unique++] = points[i];
            }
        }

        long answer = 0;

        for (int i = 0; i + 1 < unique; i++) {
            long left = points[i];
            long right = points[i + 1] - 1;

            if (left > maxStart || left > right) {
                continue;
            }

            if (right > maxStart) {
                right = maxStart;
            }

            long window = prefix(left + len, arr, pos, sum, total, totalSum)
                    - prefix(left, arr, pos, sum, total, totalSum);

            long nextWindow = prefix(left + len + 1, arr, pos, sum, total, totalSum)
                    - prefix(left + 1, arr, pos, sum, total, totalSum);

            long slope = nextWindow - window;

            if (slope == 0) {
                if (window == K) {
                    answer += right - left + 1;
                }
            } else {
                long diff = K - window;

                if (diff % slope == 0) {
                    long x = left + diff / slope;

                    if (x >= left && x <= right) {
                        answer++;
                    }
                }
            }
        }

        return new long[]{K, answer};
    }

    private long prefix(
            long x,
            int[] arr,
            long[] pos,
            long[] sum,
            long total,
            long totalSum
    ) {
        if (x <= 0) {
            return 0;
        }

        if (x >= total) {
            return totalSum;
        }

        int left = 0;
        int right = pos.length;

        while (left < right) {
            int mid = (left + right) >>> 1;

            if (pos[mid] <= x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        int idx = left;

        long beforePos = idx == 0 ? 0 : pos[idx - 1];
        long beforeSum = idx == 0 ? 0 : sum[idx - 1];

        return beforeSum + (x - beforePos) * arr[idx];
    }
}