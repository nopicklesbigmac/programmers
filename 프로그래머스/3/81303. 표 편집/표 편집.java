import java.util.*;
class Solution {
    public String solution(int n, int k, String[] cmd) {
        String answer = "";
        int[] prev = new int[n];
        int[] next = new int[n];
        boolean[] deleted = new boolean[n];
        int[] stack = new int[cmd.length];
        int top = 0;

        for (int i = 0; i < n; i++) {
            prev[i] = i - 1;
            next[i] = i + 1;
        }
        next[n - 1] = -1;

        for (String s : cmd) {
            char c = s.charAt(0);

            if (c == 'U') {
                int x = Integer.parseInt(s.substring(2));
                while (x-- > 0) {
                    k = prev[k];
                }
            } else if (c == 'D') {
                int x = Integer.parseInt(s.substring(2));
                while (x-- > 0) {
                    k = next[k];
                }
            } else if (c == 'C') {
                deleted[k] = true;
                stack[top++] = k;

                int p = prev[k];
                int q = next[k];

                if (p != -1) {
                    next[p] = q;
                }
                if (q != -1) {
                    prev[q] = p;
                    k = q;
                } else {
                    k = p;
                }
            } else {
                int restore = stack[--top];
                deleted[restore] = false;

                int p = prev[restore];
                int q = next[restore];

                if (p != -1) {
                    next[p] = restore;
                }
                if (q != -1) {
                    prev[q] = restore;
                }
            }
        }

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            sb.append(deleted[i] ? 'X' : 'O');
        }

        answer = sb.toString();
        return answer;
    }
}