import java.util.*;
class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int size : tangerine) {
            map.put(size, map.getOrDefault(size, 0) + 1);
        }
        
        List<Integer> counts = new ArrayList<>(map.values());
        counts.sort(Collections.reverseOrder());
        int countSum = 0;
        for (int count : counts) {
            countSum += count;
            answer++;
            
            if (countSum >= k) {
                break;
            }
        }
        return answer;
    }
}