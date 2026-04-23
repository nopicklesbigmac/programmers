import java.util.*;
class Solution {
    public int solution(int[] array) {
        int answer = 0;
        int maxCount = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int number : array) {
            int count = map.getOrDefault(number, 0) + 1;
            map.put(number, count);
            
            if (count > maxCount) {
                maxCount = count;
                answer = number;
            }
        }

        int repeatCount = 0;
        for (int count : map.values()) {
            if (count == maxCount) {
                repeatCount++;
            }
        }

        answer = repeatCount > 1 ? -1 : answer;
        return answer;
    }
}