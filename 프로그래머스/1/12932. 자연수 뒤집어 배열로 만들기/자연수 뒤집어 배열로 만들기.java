import java.util.*;class Solution {
    public int[] solution(long n) {
        int[] answer = {};
        String s = String.valueOf(n);
        answer = new int[s.length()];
        int index = 0;
        while (n > 0) {
            answer[index] = (int)(n % 10); 
            n /= 10;
            index++;
        }
        return answer;
    }
}