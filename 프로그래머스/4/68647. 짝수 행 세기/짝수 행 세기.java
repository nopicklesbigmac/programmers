import java.util.*;

class Solution {
    static final long MOD = 10000019L;

    public int solution(int[][] a) {
        int n = a.length;
        int m = a[0].length;

        int[] colSum = new int[m];
        int total = 0;

        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                colSum[j] += a[i][j];
            }
            total += colSum[j];
        }

        if (total % 2 == 1) {
            return 0;
        }

        long[] inv = new long[n + 1];
        inv[1] = 1;

        for (int i = 2; i <= n; i++) {
            inv[i] = MOD - (MOD / i) * inv[(int)(MOD % i)] % MOD;
        }

        long[] comb = new long[n + 1];
        comb[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = i; j >= 1; j--) {
                comb[j] = (comb[j] + comb[j - 1]) % MOD;
            }
        }

        long sum = 0;

        for (int t = 0; t <= n; t++) {
            long[] kraw = new long[n + 1];

            kraw[0] = 1;

            if (n >= 1) {
                kraw[1] = n - 2L * t;
                kraw[1] %= MOD;
                if (kraw[1] < 0) {
                    kraw[1] += MOD;
                }
            }

            for (int k = 1; k < n; k++) {
                long value = (n - 2L * t) * kraw[k];
                value -= (n - k + 1L) * kraw[k - 1];
                value %= MOD;
                if (value < 0) {
                    value += MOD;
                }

                kraw[k + 1] = value * inv[k + 1] % MOD;
            }

            long ways = 1;

            for (int j = 0; j < m; j++) {
                ways = ways * kraw[colSum[j]] % MOD;
            }

            sum = (sum + comb[t] * ways) % MOD;
        }

        long invPow2 = modPow(modPow(2, n), MOD - 2);

        return (int)(sum * invPow2 % MOD);
    }

    private long modPow(long a, long b) {
        long result = 1;

        while (b > 0) {
            if ((b & 1) == 1) {
                result = result * a % MOD;
            }

            a = a * a % MOD;
            b >>= 1;
        }

        return result;
    }
}