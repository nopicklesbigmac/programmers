import java.util.*;

class Solution {
    int[] lc, rc, pri, sz;
    long[] val, sum;
    int sl, sr;

    void upd(int t) {
        sz[t] = sz[lc[t]] + sz[rc[t]] + 1;
        sum[t] = sum[lc[t]] + sum[rc[t]] + val[t];
    }

    void split(int t, int k) {
        if (t == 0) {
            sl = 0;
            sr = 0;
            return;
        }
        if (sz[lc[t]] >= k) {
            split(lc[t], k);
            lc[t] = sr;
            upd(t);
            sr = t;
        } else {
            split(rc[t], k - sz[lc[t]] - 1);
            rc[t] = sl;
            upd(t);
            sl = t;
        }
    }

    int merge(int a, int b) {
        if (a == 0) return b;
        if (b == 0) return a;
        if (pri[a] > pri[b]) {
            rc[a] = merge(rc[a], b);
            upd(a);
            return a;
        } else {
            lc[b] = merge(a, lc[b]);
            upd(b);
            return b;
        }
    }

    public long[] solution(int[] values, int[][] edges, int[][] queries) {
        int n = values.length;
        int[] first = new int[n + 1];
        Arrays.fill(first, -1);
        int[] nxt = new int[2 * (n - 1)];
        int[] to = new int[2 * (n - 1)];
        int ec = 0;
        for (int[] e : edges) {
            to[ec] = e[1];
            nxt[ec] = first[e[0]];
            first[e[0]] = ec++;
            to[ec] = e[0];
            nxt[ec] = first[e[1]];
            first[e[1]] = ec++;
        }

        int[] parent = new int[n + 1];
        int[] order = new int[n];
        int qh = 0, qt = 0;
        order[qt++] = 1;
        while (qh < qt) {
            int v = order[qh++];
            for (int e = first[v]; e != -1; e = nxt[e]) {
                int c = to[e];
                if (c != parent[v]) {
                    parent[c] = v;
                    order[qt++] = c;
                }
            }
        }

        int[] size = new int[n + 1];
        int[] heavy = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            int v = order[i];
            size[v] += 1;
            int p = parent[v];
            if (p != 0) {
                size[p] += size[v];
                if (heavy[p] == 0 || size[v] > size[heavy[p]]) heavy[p] = v;
            }
        }

        int[] pos = new int[n + 1];
        int[] head = new int[n + 1];
        int[] stack = new int[n + 1];
        int sp = 0;
        int timer = 0;
        head[1] = 1;
        stack[sp++] = 1;
        while (sp > 0) {
            int v = stack[--sp];
            pos[v] = timer++;
            for (int e = first[v]; e != -1; e = nxt[e]) {
                int c = to[e];
                if (c != parent[v] && c != heavy[v]) {
                    head[c] = c;
                    stack[sp++] = c;
                }
            }
            if (heavy[v] != 0) {
                head[heavy[v]] = head[v];
                stack[sp++] = heavy[v];
            }
        }

        lc = new int[n + 1];
        rc = new int[n + 1];
        pri = new int[n + 1];
        sz = new int[n + 1];
        val = new long[n + 1];
        sum = new long[n + 1];
        Random rnd = new Random(12345);
        for (int v = 1; v <= n; v++) {
            int id = pos[v] + 1;
            val[id] = values[v - 1];
            sum[id] = values[v - 1];
            sz[id] = 1;
            pri[id] = rnd.nextInt();
        }
        int root = 0;
        for (int id = 1; id <= n; id++) root = merge(root, id);

        int cntType1 = 0;
        for (int[] q : queries) if (q[1] == -1) cntType1++;
        long[] answer = new long[cntType1];
        int ai = 0;

        int[] segL = new int[64];
        int[] segR = new int[64];

        for (int[] q : queries) {
            int u = q[0];
            int w = q[1];
            if (w == -1) {
                int l = pos[u];
                int len = size[u];
                split(root, l);
                int a = sl;
                int rest = sr;
                split(rest, len);
                int b = sl;
                int c = sr;
                answer[ai++] = sum[b];
                root = merge(a, merge(b, c));
            } else {
                int cnt = 0;
                int x = u;
                while (x != 0) {
                    int h = head[x];
                    if (cnt == segL.length) {
                        segL = Arrays.copyOf(segL, cnt * 2);
                        segR = Arrays.copyOf(segR, cnt * 2);
                    }
                    segL[cnt] = pos[h];
                    segR[cnt] = pos[x];
                    cnt++;
                    x = parent[h];
                }
                long carry = w;
                for (int i = cnt - 1; i >= 0; i--) {
                    int l = segL[i];
                    int len = segR[i] - segL[i] + 1;
                    split(root, l);
                    int a = sl;
                    int rest = sr;
                    split(rest, len);
                    int b = sl;
                    int c = sr;
                    split(b, len - 1);
                    int b1 = sl;
                    int b2 = sr;
                    long old = val[b2];
                    val[b2] = carry;
                    sum[b2] = carry;
                    carry = old;
                    root = merge(merge(a, merge(b2, b1)), c);
                }
            }
        }
        return answer;
    }
}