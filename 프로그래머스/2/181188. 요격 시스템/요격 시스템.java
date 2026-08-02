import java.util.Arrays;
class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, (a, b) -> Integer.compare(a[1], b[1]));
        int answer = 0;
        int lastEnd = -1;

        for (int[] target : targets) {
            int start = target[0];
            int end = target[1];

            if (start >= lastEnd) {
                answer++;
                lastEnd = end;
            }
        }
        return answer;
    }
}