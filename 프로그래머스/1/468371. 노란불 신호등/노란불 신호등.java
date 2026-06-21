import java.util.List;
import java.util.ArrayList;

class Solution {
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
    private long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }
    
    public int solution(int[][] signals) {
        int answer = -1; 
        
        int n = signals.length;
        int[] periods = new int[n];
        long maxLimit = 1;
        
        for (int i = 0; i < n; i++) {
            periods[i] = signals[i][0] + signals[i][1] + signals[i][2];
            maxLimit = lcm(maxLimit, periods[i]);
        }
        for (int t = 1; t <= maxLimit; t++) {
            boolean allYellow = true;
            for (int i = 0; i < n; i++) {
                int G = signals[i][0];
                int Y = signals[i][1];
                int T = periods[i];
                int remain = (t - 1) % T;
                if (remain < G || remain >= G + Y) {
                    allYellow = false;
                    break;
                }
            }
            if (allYellow) {
                answer = t;
                break;
            }
        }
        return answer;
    }
}