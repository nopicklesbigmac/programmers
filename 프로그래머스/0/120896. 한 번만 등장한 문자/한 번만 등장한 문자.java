import java.util.Arrays;
class Solution {
    public String solution(String s) {
        String answer = "";
        int[] count = new int[26];
        
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (count[i] == 1) {
                sb.append((char) (i + 'a'));
            }
        }
        
        answer = sb.toString();
        return answer;
    }
}