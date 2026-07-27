import java.util.ArrayList;
import java.util.List;
class Solution {
    public double[] solution(int k, int[][] ranges) {
        double[] answer = {};
        List<Integer> collatz = new ArrayList<>();
        collatz.add(k);
        
        while (k > 1) {
            if (k % 2 == 0) {
                k /= 2;
            } else {
                k = k * 3 + 1;
            }
            collatz.add(k);
        }
        
        int n = collatz.size() - 1;
        double[] areas = new double[n];
        
        for (int i = 0; i < n; i++) {
            int y1 = collatz.get(i);
            int y2 = collatz.get(i + 1);
            areas[i] = (double)(y1 + y2) / 2.0;
        }
        answer = new double[ranges.length];
        
        for (int i = 0; i < ranges.length; i++) {
            int a = ranges[i][0];
            int b = n + ranges[i][1];
            
            if (a > b) {
                answer[i] = -1.0;
            } else if (a == b) {
                answer[i] = 0.0;
            } else {
                double sum = 0;
                for (int j = a; j < b; j++) {
                    sum += areas[j];
                }
                answer[i] = sum;
            }
        }
        
        return answer;
    }
}