import java.util.*;
class Solution {
    public int solution(int n, int[][] data) {
        Arrays.sort(data, (a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        int answer = 0;

        for (int i = 0; i < n; ) {
            int startX = data[i][0];
            int groupEnd = i;

            while (groupEnd < n && data[groupEnd][0] == startX) {
                groupEnd++;
            }

            for (int left = i; left < groupEnd; left++) {
                int y1 = data[left][1];

                int lowerMax = Integer.MIN_VALUE;
                int upperMin = Integer.MAX_VALUE;

                int j = groupEnd;

                while (j < n) {
                    int x = data[j][0];
                    int next = j;

                    while (next < n && data[next][0] == x) {
                        next++;
                    }

                    for (int k = j; k < next; k++) {
                        int y2 = data[k][1];

                        if (y2 == y1) continue;

                        if (y2 > y1) {
                            if (y2 <= upperMin) {
                                answer++;
                            }
                        } else {
                            if (y2 >= lowerMax) {
                                answer++;
                            }
                        }
                    }

                    for (int k = j; k < next; k++) {
                        int y = data[k][1];

                        if (y < y1) {
                            lowerMax = Math.max(lowerMax, y);
                        } else if (y > y1) {
                            upperMin = Math.min(upperMin, y);
                        }
                    }

                    j = next;
                }
            }

            i = groupEnd;
        }

        return answer;
    }
}