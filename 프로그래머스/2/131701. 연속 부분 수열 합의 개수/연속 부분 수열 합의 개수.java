import java.util.HashSet;
import java.util.Set;

class Solution {
    public int solution(int[] elements) {
        int answer = 0;
        int n = elements.length;
        Set<Integer> sums = new HashSet<>();

        int[] extended = new int[n * 2];
        for (int i = 0; i < n; i++) {
            extended[i] = elements[i];
            extended[i + n] = elements[i];
        }

        for (int len = 1; len <= n; len++) {
            int currentSum = 0;
            for (int i = 0; i < len; i++) {
                currentSum += extended[i];
            }
            sums.add(currentSum);

            for (int i = 1; i < n; i++) {
                currentSum = currentSum - extended[i - 1] + extended[i + len - 1];
                sums.add(currentSum);
            }
        }

        answer = sums.size();
        return answer;
    }
}