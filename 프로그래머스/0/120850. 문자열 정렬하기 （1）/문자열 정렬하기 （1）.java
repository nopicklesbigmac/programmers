import java.util.*;
class Solution {
    public int[] solution(String my_string) {
        int[] answer = {};
        List<Integer> list = new ArrayList<>();
        
        for (char c : my_string.toCharArray()) {
            if (Character.isDigit(c)) {
                list.add(c - '0'); 
            }
        }
        Collections.sort(list);
        
        answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}