import java.util.*;
class Solution {
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        
        Map<Character, Integer> scores = new HashMap<>();
        String types = "RTCFJMAN";
        for (char type : types.toCharArray()) scores.put(type, 0);

        for (int i = 0; i < survey.length; i++) {
            int choice = choices[i];
            if (choice == 4) continue;
            
            char type;
            int score = Math.abs(choice - 4);
            
            if (choice < 4) {
                type = survey[i].charAt(0);
            } else {
                type = survey[i].charAt(1);
            }
            scores.put(type, scores.get(type) + score);
        }

        StringBuilder sb = new StringBuilder();
        char[][] indicators = {{'R', 'T'}, {'C', 'F'}, {'J', 'M'}, {'A', 'N'}};
        
        for (char[] pair : indicators) {
            int score1 = scores.get(pair[0]);
            int score2 = scores.get(pair[1]);
            
            if (score1 >= score2) {
                sb.append(pair[0]);
            } else {
                sb.append(pair[1]);
            }
        }
        
        answer = sb.toString();
        
        return answer;
    }
}