class Solution {
    public String solution(String s, String skip, int index) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        
        for (char c : s.toCharArray()) {
            int count = 0;
            char current = c;
            
            while (count < index) {
                current++;
                if (current > 'z') {
                    current = 'a';
                }
                
                if (skip.indexOf(current) == -1) {
                    count++;
                }
            }
            sb.append(current);
        }
        
        answer = sb.toString();
        return answer;
    }
}