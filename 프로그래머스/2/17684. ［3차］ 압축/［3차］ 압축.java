import java.util.*;
class Solution {
    public int[] solution(String msg) {
        int[] answer = {};
        Map<String, Integer> dictionary = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            dictionary.put(String.valueOf((char) ('A' + i)), i + 1);
        }

        List<Integer> result = new ArrayList<>();
        int dictSize = 27;
        int len = msg.length();
        
        int i = 0;
        while (i < len) {
            String w = "";
            int j = i;
            while (j < len) {
                String nextW = w + msg.charAt(j);
                if (dictionary.containsKey(nextW)) {
                    w = nextW;
                    j++;
                } else {
                    break;
                }
            }
            result.add(dictionary.get(w));
            if (j < len) {
                char c = msg.charAt(j);
                dictionary.put(w + c, dictSize++);
            }
            i = j;
        }
        answer = result.stream().mapToInt(i_val -> i_val).toArray();
        return answer;
    }
}