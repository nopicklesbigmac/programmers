import java.util.*;

class Solution {
    public int[][] solution(int n, int m, int[] a, int[] b, int k, int m1, int m2, int[] e1, int[] e2) {
        boolean[] inM0 = new boolean[m + 1];
        boolean[] inMt = new boolean[m + 1];
        for (int e : e1) inM0[e] = true;
        for (int e : e2) inMt[e] = true;
        int[] m0Of = new int[n + 1];
        int[] mtOf = new int[n + 1];
        for (int e : e1) {
            if (inMt[e]) continue;
            m0Of[a[e - 1]] = e;
            m0Of[b[e - 1]] = e;
        }
        for (int e : e2) {
            if (inM0[e]) continue;
            mtOf[a[e - 1]] = e;
            mtOf[b[e - 1]] = e;
        }
        boolean[] used = new boolean[m + 1];
        List<int[][]> comps = new ArrayList<>();
        List<Integer> gains = new ArrayList<>();

        for (int v = 1; v <= n; v++) {
            int deg = (m0Of[v] != 0 ? 1 : 0) + (mtOf[v] != 0 ? 1 : 0);
            if (deg != 1) continue;
            int e = m0Of[v] != 0 ? m0Of[v] : mtOf[v];
            if (used[e]) continue;
            List<Integer> path = new ArrayList<>();
            int cur = v;
            while (e != 0 && !used[e]) {
                used[e] = true;
                path.add(e);
                int w = a[e - 1] == cur ? b[e - 1] : a[e - 1];
                boolean isM0 = inM0[e];
                e = isM0 ? mtOf[w] : m0Of[w];
                cur = w;
            }
            int r = path.size();
            boolean[] removed = new boolean[r];
            List<int[]> ops = new ArrayList<>();
            int gain = 0;
            for (int i = 0; i < r; i++) {
                int id = path.get(i);
                if (inM0[id]) {
                    gain--;
                    if (!removed[i]) {
                        removed[i] = true;
                        ops.add(new int[]{0, id});
                    }
                } else {
                    gain++;
                    if (i + 1 < r && !removed[i + 1]) {
                        removed[i + 1] = true;
                        ops.add(new int[]{0, path.get(i + 1)});
                    }
                    ops.add(new int[]{1, id});
                }
            }
            comps.add(ops.toArray(new int[0][]));
            gains.add(gain);
        }

        for (int e0 : e1) {
            if (inMt[e0] || used[e0]) continue;
            List<Integer> cyc = new ArrayList<>();
            int e = e0;
            int cur = a[e0 - 1];
            while (!used[e]) {
                used[e] = true;
                cyc.add(e);
                int w = a[e - 1] == cur ? b[e - 1] : a[e - 1];
                boolean isM0 = inM0[e];
                e = isM0 ? mtOf[w] : m0Of[w];
                cur = w;
            }
            int len = cyc.size();
            int l = len / 2;
            List<int[]> ops = new ArrayList<>();
            int[] am = new int[l];
            int[] bm = new int[l];
            for (int i = 0; i < l; i++) {
                am[i] = cyc.get(2 * i);
                bm[i] = cyc.get(2 * i + 1);
            }
            ops.add(new int[]{0, am[0]});
            ops.add(new int[]{0, am[l - 1]});
            ops.add(new int[]{1, bm[l - 1]});
            for (int i = l - 2; i >= 1; i--) {
                ops.add(new int[]{0, am[i]});
                ops.add(new int[]{1, bm[i]});
            }
            ops.add(new int[]{1, bm[0]});
            comps.add(ops.toArray(new int[0][]));
            gains.add(0);
        }

        Integer[] order = new Integer[comps.size()];
        for (int i = 0; i < order.length; i++) order[i] = i;
        Arrays.sort(order, (x, y) -> gains.get(y) - gains.get(x));

        List<int[]> result = new ArrayList<>();
        for (int idx : order) {
            for (int[] op : comps.get(idx)) result.add(op);
        }
        return result.toArray(new int[0][]);
    }
}