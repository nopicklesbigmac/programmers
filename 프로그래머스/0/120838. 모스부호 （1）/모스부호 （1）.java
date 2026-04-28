import java.util.HashMap;
import java.util.Map;
class Solution {
    public String solution(String letter) {
        String answer = "";
        
        Map<String, String> morse = new HashMap<>();
        String[] morseArray = {
            ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", 
            "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", 
            "..-", "...-", ".--", "-..-", "-.--", "--.."
        };
        
        for (int i = 0; i < 26; i++) {
            morse.put(morseArray[i], Character.toString((char)('a' + i)));
        }

        String[] letters = letter.split(" ");
        StringBuilder sb = new StringBuilder();

        for (String s : letters) {
            sb.append(morse.get(s));
        }

        answer = sb.toString();
        
        return answer;
    }
}