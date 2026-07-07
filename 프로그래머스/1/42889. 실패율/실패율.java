import java.util.*;
class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = {};
        double[] failRates = new double[N + 1];
        int[] count = new int[N + 2];
        
        for (int s : stages) {
            count[s]++;
        }
        
        int totalPlayers = stages.length;
        for (int i = 1; i <= N; i++) {
            if (totalPlayers == 0) {
                failRates[i] = 0;
            } else {
                failRates[i] = (double) count[i] / totalPlayers;
                totalPlayers -= count[i];
            }
        }
        
        Integer[] stageNumbers = new Integer[N];
        for (int i = 0; i < N; i++) {
            stageNumbers[i] = i + 1;
        }
        
        Arrays.sort(stageNumbers, (a, b) -> {
            if (failRates[b] != failRates[a]) {
                return Double.compare(failRates[b], failRates[a]);
            }
            return a - b;
        });
        answer = new int[N];
        for (int i = 0; i < N; i++) {
            answer[i] = stageNumbers[i];
        }
        
        return answer;
    }
}