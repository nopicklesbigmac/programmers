import java.util.*;

class Solution {
    public int solution(int n, int m, int[][] timetable) {
        int[] time = new int[1322];

        for (int[] t : timetable) {
            time[t[0]]++;
            time[t[1] + 1]--;
        }

        int maxPeople = 0;
        int current = 0;

        for (int i = 600; i <= 1320; i++) {
            current += time[i];
            maxPeople = Math.max(maxPeople, current);
        }

        if (maxPeople <= 1) {
            return 0;
        }

        for (int distance = 2 * n - 2; distance >= 1; distance--) {
            for (int sr = 0; sr < n; sr++) {
                for (int sc = 0; sc < n; sc++) {
                    int[][] selected = new int[n * n][2];
                    int count = 1;

                    selected[0][0] = sr;
                    selected[0][1] = sc;

                    for (int r = sr; r < n; r++) {
                        for (int c = 0; c < n; c++) {
                            if (r == sr && c <= sc) {
                                continue;
                            }

                            boolean possible = true;

                            for (int k = 0; k < count; k++) {
                                int d = Math.abs(selected[k][0] - r)
                                      + Math.abs(selected[k][1] - c);

                                if (d < distance) {
                                    possible = false;
                                    break;
                                }
                            }

                            if (possible) {
                                selected[count][0] = r;
                                selected[count][1] = c;
                                count++;

                                if (count == maxPeople) {
                                    return distance;
                                }
                            }
                        }
                    }
                }
            }
        }

        return 0;
    }
}