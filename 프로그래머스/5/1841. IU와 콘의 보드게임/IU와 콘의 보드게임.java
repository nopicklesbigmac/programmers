import java.util.*;

class Solution {
    static int[] rank(int n, long[] px, long[] py, long ox, long oy, long fx, long fy, long gx, long gy) {
        int s = Long.signum((fx - ox) * (gy - oy) - (fy - oy) * (gx - ox));
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i + 1;
        Arrays.sort(idx, (x, y) -> {
            long cr = (px[x] - ox) * (py[y] - oy) - (py[x] - oy) * (px[y] - ox);
            return -Long.signum(cr) * s;
        });
        int[] r = new int[n + 1];
        for (int i = 0; i < n; i++) r[idx[i]] = i + 1;
        return r;
    }

    public int solution(int n, int[][] triangle, int[][] v) {
        long[] px = new long[n + 1];
        long[] py = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            px[i] = v[i - 1][0];
            py[i] = v[i - 1][1];
        }
        long ax = triangle[0][0], ay = triangle[0][1];
        long bx = triangle[1][0], by = triangle[1][1];
        long cx = triangle[2][0], cy = triangle[2][1];

        int[] rB = rank(n, px, py, bx, by, ax, ay, cx, cy);
        int[] rC = rank(n, px, py, cx, cy, bx, by, ax, ay);
        int[] rA = rank(n, px, py, ax, ay, cx, cy, bx, by);

        long total = 0;
        int inf = n + 1;
        for (int p = 0; p <= n; p++) {
            int tB = p > 0 ? rB[p] : 0;
            for (int q = 0; q <= n; q++) {
                if (p > 0 && q > 0 && (p == q || rB[q] <= tB)) continue;
                int tC = q > 0 ? rC[q] : 0;
                int m1 = inf;
                int m2 = 0;
                for (int x = 1; x <= n; x++) {
                    if (x == p || x == q) continue;
                    boolean a = rB[x] < tB;
                    boolean c = rC[x] < tC;
                    if (a && c) {
                        if (rA[x] < m1) m1 = rA[x];
                    } else if (!a && !c) {
                        if (rA[x] > m2) m2 = rA[x];
                    }
                }
                if (m2 == 0) total++;
                int upper = m1;
                if (p > 0 && rA[p] - 1 < upper) upper = rA[p] - 1;
                for (int r = 1; r <= n; r++) {
                    if (r == p || r == q) continue;
                    if (rC[r] > tC && rA[r] >= m2 && rA[r] <= upper) total++;
                }
            }
        }
        return (int) total;
    }
}