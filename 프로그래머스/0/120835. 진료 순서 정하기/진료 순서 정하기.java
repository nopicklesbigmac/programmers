import java.util.Arrays;
class Solution {
    public int[] solution(int[] emergency) {
        int[] answer = {};
        int n = emergency.length;
        answer = new int[n];
        
        int[] sorted = emergency.clone();
        Arrays.sort(sorted);
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (emergency[i] == sorted[j]) {
                    answer[i] = n - j;
                    break;
                }
            }
        }
        return answer;
    }
}