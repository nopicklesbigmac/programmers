class Solution {
    public int solution(String s) {
        int answer = 0;
        answer = s.length();
        
        for (int i = 1; i <= s.length() / 2 + 1; i++) {
            StringBuilder compressed = new StringBuilder();
            String prev = "";
            int count = 1;
            
            for (int j = 0; j < s.length(); j += i) {
                int end = Math.min(j + i, s.length());
                String sub = s.substring(j, end);
                
                if (sub.equals(prev)) {
                    count++;
                } else {
                    if (count > 1) {
                        compressed.append(count);
                    }
                    compressed.append(prev);
                    prev = sub;
                    count = 1;
                }
            }
            
            if (count > 1) {
                compressed.append(count);
            }
            compressed.append(prev);
            
            answer = Math.min(answer, compressed.length());
        }
        
        return answer;
    }
}