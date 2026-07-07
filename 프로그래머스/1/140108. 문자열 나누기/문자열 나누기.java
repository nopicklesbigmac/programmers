class Solution {
    public int solution(String s) {
        int answer = 0;
        int i = 0;
        
        while (i < s.length()) {
            answer++;
            char x = s.charAt(i);
            int same = 0;
            int diff = 0;
            
            while (i < s.length()) {
                if (s.charAt(i) == x) {
                    same++;
                } else {
                    diff++;
                }
                
                i++;
                if (same == diff) {
                    break;
                }
            }
        }
        
        return answer;
    }
}