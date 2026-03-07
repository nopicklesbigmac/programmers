class Solution {
    public String solution(String code) {
        String answer = "";
        
        StringBuilder ret = new StringBuilder();
        int mode = 0;
        for (int i = 0; i < code.length(); i++) {
            char current = code.charAt(i);

            if (current == '1') {
                mode = 1 - mode; 
            } else {
                if (mode == 0) {
                    if (i % 2 == 0) ret.append(current);
                } else {
                    if (i % 2 != 0) ret.append(current);
                }
            }
        }
        answer = ret.length() == 0 ? "EMPTY" : ret.toString();
        return answer;
    }
}