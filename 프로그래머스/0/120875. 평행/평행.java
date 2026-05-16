class Solution {
    public int solution(int[][] dots) {
        int answer = 0;
        if ((long)(dots[1][1] - dots[0][1]) * (dots[3][0] - dots[2][0]) == 
            (long)(dots[3][1] - dots[2][1]) * (dots[1][0] - dots[0][0])) {
            answer = 1;
        }
        else if ((long)(dots[2][1] - dots[0][1]) * (dots[3][0] - dots[1][0]) == 
                 (long)(dots[3][1] - dots[1][1]) * (dots[2][0] - dots[0][0])) {
            answer = 1;
        }
        else if ((long)(dots[3][1] - dots[0][1]) * (dots[2][0] - dots[1][0]) == 
                 (long)(dots[2][1] - dots[1][1]) * (dots[3][0] - dots[0][0])) {
            answer = 1;
        }
        return answer;
    }
}