class Solution {
    boolean solution(String s) {
        boolean answer = true;
        System.out.println("Hello Java");

        int count = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                count++;
            } else {
                count--;
            }
            
            if (count < 0) {
                return false;
            }
        }
        
        answer = count == 0;
        
        return answer;
    }
}