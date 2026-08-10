import java.util.Arrays;

class Solution {
    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        int[] answer = {};
        int total = drops.length;
        int[] wet = new int[m * n];
        int[] prefix = new int[(m + 1) * (n + 1)];

        int lo = 0;
        int hi = total;

        while (lo < hi) {
            int mid = (lo + hi + 1) / 2;

            Arrays.fill(wet, 0);
            for (int i = 0; i < mid; i++) {
                int r = drops[i][0];
                int c = drops[i][1];
                wet[r * n + c] = 1;
            }

            buildPrefix(m, n, wet, prefix);

            if (canPlace(m, n, h, w, prefix)) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }

        Arrays.fill(wet, 0);
        for (int i = 0; i < lo; i++) {
            int r = drops[i][0];
            int c = drops[i][1];
            wet[r * n + c] = 1;
        }

        buildPrefix(m, n, wet, prefix);

        for (int r = 0; r <= m - h; r++) {
            for (int c = 0; c <= n - w; c++) {
                int r2 = r + h;
                int c2 = c + w;
                int count = prefix[r2 * (n + 1) + c2]
                        - prefix[r * (n + 1) + c2]
                        - prefix[r2 * (n + 1) + c]
                        + prefix[r * (n + 1) + c];

                if (count == 0) {
                    return new int[]{r, c};
                }
            }
        }

        answer = new int[]{0, 0};
        return answer;
    }
    private void buildPrefix(int m, int n, int[] wet, int[] prefix) {
        int width = n + 1;

        Arrays.fill(prefix, 0);

        for (int r = 1; r <= m; r++) {
            int rowSum = 0;
            int base = (r - 1) * n;
            int cur = r * width;
            int prev = (r - 1) * width;

            for (int c = 1; c <= n; c++) {
                rowSum += wet[base + c - 1];
                prefix[cur + c] = prefix[prev + c] + rowSum;
            }
        }
    }

    private boolean canPlace(int m, int n, int h, int w, int[] prefix) {
        int width = n + 1;

        for (int r = 0; r <= m - h; r++) {
            int r2 = r + h;

            for (int c = 0; c <= n - w; c++) {
                int c2 = c + w;

                int count = prefix[r2 * width + c2]
                        - prefix[r * width + c2]
                        - prefix[r2 * width + c]
                        + prefix[r * width + c];

                if (count == 0) {
                    return true;
                }
            }
        }

        return false;
}
}