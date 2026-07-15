import java.util.HashSet;
import java.util.Set;
class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = {};
        System.out.println("Hello Java");
        
        Set<String> usedWords = new HashSet<>();
        
        usedWords.add(words[0]);
        
        for (int i = 1; i < words.length; i++) {
            String prev = words[i - 1];
            String curr = words[i];
            
            char lastChar = prev.charAt(prev.length() - 1);
            char firstChar = curr.charAt(0);
            
            if (lastChar != firstChar || usedWords.contains(curr)) {
                int person = (i % n) + 1;
                int turn = (i / n) + 1;
                return new int[]{person, turn};
            }
            
            usedWords.add(curr);
        }
        
        answer = new int[]{0, 0};

        return answer;
    }
}