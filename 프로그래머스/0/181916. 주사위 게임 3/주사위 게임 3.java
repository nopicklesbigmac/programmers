import java.util.Arrays;

class Solution {
    public int solution(int a, int b, int c, int d) {
        int answer = 0;
        int[] dice = {a, b, c, d};
        Arrays.sort(dice); 
        if (dice[0] == dice[3]) {
            answer = 1111 * dice[0];
            return answer;
        }
        if (dice[0] == dice[2] || dice[1] == dice[3]) {
            int p = (dice[0] == dice[2]) ? dice[0] : dice[3];
            int q = (dice[0] == dice[2]) ? dice[3] : dice[0];
            answer = (int) Math.pow(10 * p + q, 2);
            return answer;
        }
        
        // 3. 주사위가 두 개씩 같은 경우 (2+2)
        if (dice[0] == dice[1] && dice[2] == dice[3]) {
            int p = dice[0];
            int q = dice[2];
            answer = (p + q) * Math.abs(p - q);
            return answer;
        }
        if (dice[0] == dice[1] || dice[1] == dice[2] || dice[2] == dice[3]) {
            if (dice[0] == dice[1]) return dice[2] * dice[3];
            if (dice[1] == dice[2]) return dice[0] * dice[3];
            answer = dice[0] * dice[1];
            return answer;
        }
        answer = dice[0];
        return answer;
    }
}