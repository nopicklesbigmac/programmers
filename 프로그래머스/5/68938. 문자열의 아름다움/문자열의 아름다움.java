import java.util.*;

class Solution {
    public long solution(String s) {
        int n = s.length();

        ArrayList<Integer>[] runs = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            runs[i] = new ArrayList<>();
        }

        long answer = (long) n * (n * (long) n - 1) / 6;

        int i = 0;

        while (i < n) {
            int j = i + 1;

            while (j < n && s.charAt(i) == s.charAt(j)) {
                j++;
            }

            int len = j - i;

            runs[s.charAt(i) - 'a'].add(len);

            answer -= (long) len * (len * (long) len - 1) / 6;

            i = j;
        }

        for (int c = 0; c < 26; c++) {
            runs[c].sort(null);

            long sumA = 0;
            long sumB = 0;

            for (int len : runs[c]) {
                long a = len;

                long s1 = a * (a + 1) / 2;
                long s2 = a * (a + 1) * (2 * a + 1) / 6;
                long extra = (s1 - s2) / 2;

                answer -= a * sumA + sumB;

                sumA += s1;
                sumB += extra;
            }
        }

        return answer;
    }
}