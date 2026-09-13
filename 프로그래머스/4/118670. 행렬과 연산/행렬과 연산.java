import java.util.*;
class Solution {
    public int[][] solution(int[][] rc, String[] operations) {
        int n = rc.length;
        int m = rc[0].length;
        Deque < Deque < Integer >> rows = new ArrayDeque < > ();
        Deque < Integer > left = new ArrayDeque < > ();
        Deque < Integer > right = new ArrayDeque < > ();
        for (int i = 0; i < n; i++) {
            Deque < Integer > row = new ArrayDeque < > ();
            left.addLast(rc[i][0]);
            right.addLast(rc[i][m - 1]);
            for (int j = 1; j < m - 1; j++) {
                row.addLast(rc[i][j]);
            }
            rows.addLast(row);
        }
        for (String op: operations) {
            if (op.equals("ShiftRow")) {
                rows.addFirst(rows.pollLast());
                left.addFirst(left.pollLast());
                right.addFirst(right.pollLast());
            } else {
                Deque < Integer > first = rows.peekFirst();
                Deque < Integer > last = rows.peekLast();
                first.addFirst(left.pollFirst());
                right.addFirst(first.pollLast());
                last.addLast(right.pollLast());
                left.addLast(last.pollFirst());
            }
        }
        int[][] answer = new int[n][m];
        for (int i = 0; i < n; i++) {
            answer[i][0] = left.pollFirst();
            Deque < Integer > row = rows.pollFirst();
            for (int j = 1; j < m - 1; j++) {
                answer[i][j] = row.pollFirst();
            }
            answer[i][m - 1] = right.pollFirst();
        }
        return answer;
    }
}