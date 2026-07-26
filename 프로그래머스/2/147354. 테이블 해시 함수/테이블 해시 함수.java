import java.util.*;
class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        Arrays.sort(data, (o1, o2) -> {
            if (o1[col - 1] == o2[col - 1]) {
                return Integer.compare(o2[0], o1[0]);
            }
            return Integer.compare(o1[col - 1], o2[col - 1]);
        });

        for (int i = row_begin; i <= row_end; i++) {
            int s_i = 0;
            for (int val : data[i - 1]) {
                s_i += (val % i);
            }
            answer ^= s_i;
        }

        return answer;
    }
}