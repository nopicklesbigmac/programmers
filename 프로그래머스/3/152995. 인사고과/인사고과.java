import java.util.*;
class Solution {
    public int solution(int[][] scores) {
        int answer = 0;
        int wanhoA = scores[0][0];
        int wanhoB = scores[0][1];
        int wanhoSum = wanhoA + wanhoB;

        Arrays.sort(scores, (a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(b[0], a[0]);
        });

        int maxB = -1;
        answer = 1;

        for (int[] score : scores) {
            if (score[1] < maxB) {
                if (score[0] == wanhoA && score[1] == wanhoB) {
                    return -1;
                }
                continue;
            }

            maxB = score[1];

            if (score[0] + score[1] > wanhoSum) {
                answer++;
            }
        }

        return answer;
    }
}