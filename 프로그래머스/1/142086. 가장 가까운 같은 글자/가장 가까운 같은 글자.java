import java.util.HashMap;
import java.util.Map;
class Solution {
    public int[] solution(String s) {
        int[] answer = {};
        answer = new int[s.length()];
        Map<Character, Integer> lastSeen = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (!lastSeen.containsKey(c)) {
                answer[i] = -1;
            } else {
                answer[i] = i - lastSeen.get(c);
            }
            
            lastSeen.put(c, i);
        }
        return answer;
    }
}