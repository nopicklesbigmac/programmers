import java.util.HashMap;
import java.util.Map;
class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = {};
        Map<Character, Integer> minPress = new HashMap<>();

        for (String key : keymap) {
            for (int i = 0; i < key.length(); i++) {
                char ch = key.charAt(i);
                int pressCount = i + 1;
                if (!minPress.containsKey(ch) || minPress.get(ch) > pressCount) {
                    minPress.put(ch, pressCount);
                }
            }
        }
        answer = new int[targets.length];
        for (int i = 0; i < targets.length; i++) {
            int totalPress = 0;
            boolean possible = true;
            for (char ch : targets[i].toCharArray()) {
                if (minPress.containsKey(ch)) {
                    totalPress += minPress.get(ch);
                } else {
                    possible = false;
                    break;
                }
            }
            answer[i] = possible ? totalPress : -1;
        }
        return answer;
    }
}